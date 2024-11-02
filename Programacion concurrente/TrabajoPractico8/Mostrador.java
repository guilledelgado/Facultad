package TrabajoPractico8;

import java.util.Random;
import java.util.concurrent.locks.*;

public class Mostrador {
    private int pesoA;
    private int pesoB;
    private int pesoC;
    private int cantPastelesA;
    private int cantPastelesB;
    private int cantPastelesC;
    private int cantidadMaxPasteles;
    private int cantidadActualPasteles;
    private int pesoMaxCaja;
    private int pesoActualCaja;
    private int empaquetadorasEsperando;
    private boolean hayCaja;
    private Lock mutex = new ReentrantLock();
    private Condition esperaHornos;
    private Condition esperaEmpaquetadoras;
    private Condition esperaBrazo;

    public Mostrador(int pesoa, int pesob, int pesoc, int cantMax, int pesoMaxCaja) {
        this.pesoA = pesoa;
        this.pesoB = pesob;
        this.pesoC = pesoc;
        this.cantPastelesA = 0;
        this.cantPastelesB = 0;
        this.cantPastelesC = 0;
        this.cantidadMaxPasteles = cantMax;
        this.cantidadActualPasteles = 0;
        this.hayCaja = true;
        this.pesoMaxCaja = pesoMaxCaja;
        this.pesoActualCaja = 0;
        this.empaquetadorasEsperando = 0;
        this.esperaHornos = mutex.newCondition();
        this.esperaEmpaquetadoras = mutex.newCondition();
        this.esperaBrazo = mutex.newCondition();
    }

    public void ponerPastel(char tipoPastel) throws InterruptedException {
        mutex.lock();
        while (cantidadActualPasteles > cantidadMaxPasteles) {
            //Los hornos tienen una cantidad maxima diaria
            esperaHornos.await();
        }
        if (tipoPastel == 'A') {
            cantPastelesA++;
        } else if (tipoPastel == 'B') {
            cantPastelesB++;
        } else if (tipoPastel == 'C') {
            cantPastelesC++;
        }
        cantidadActualPasteles++;
        System.out.println("Nuevo pastel. Cantidad: "+cantidadActualPasteles);
        esperaEmpaquetadoras.signal();
        mutex.unlock();
    }

    public int tomarPastel() throws InterruptedException {
        mutex.lock();
        int pesoPastel = 0;
        while (!hayCaja || (cantPastelesA == 0 && cantPastelesB == 0 && cantPastelesC == 0)) {
            esperaEmpaquetadoras.await();
        }
        int pastel = (int) (Math.random() * 2);
        int i = 0;
        while (i < 2) {
            if (pastel == 0) {
                if (cantPastelesA > 0) {
                    System.out.println(Thread.currentThread().getName()+" Toma pastel 'A'");
                    pesoPastel = pesoA;
                    cantPastelesA--;
                    i = 2;
                } else {
                    pastel = 1;
                }
            }
            if (pastel == 1) {
                if (cantPastelesB > 0) {
                    System.out.println(Thread.currentThread().getName()+" Toma pastel 'B'");
                    pesoPastel = pesoB;
                    cantPastelesB--;
                    i = 2;
                } else {
                    pastel = 2;
                }
            }
            if (pastel == 2) {
                if (cantPastelesC > 0) {
                    System.out.println(Thread.currentThread().getName()+" Toma pastel 'C'");
                    pesoPastel = pesoC;
                    cantPastelesC--;
                    i = 2;
                } else {
                    pastel = 0;
                }
            }
            i++;
        }
        System.out.println("Cantidad Pasteles A: "+cantPastelesA);
        System.out.println("Cantidad Pasteles B: "+cantPastelesB);
        System.out.println("Cantidad Pasteles C: "+cantPastelesC);
        mutex.unlock();
        return pesoPastel;
    }

    public void soltarPastel(int peso) throws InterruptedException {
        mutex.lock();
        empaquetadorasEsperando++;
        while (!hayCaja || (peso+pesoActualCaja)>pesoMaxCaja) {
            if(empaquetadorasEsperando==2){
                esperaBrazo.signal();
            }
            esperaEmpaquetadoras.await();
        }
        System.out.println(Thread.currentThread().getName()+" Suelta un pastel a la caja");
        empaquetadorasEsperando--;
        pesoActualCaja += peso;
        mutex.unlock();
    }

    public void retirarCaja() throws InterruptedException{
        mutex.lock();
        System.out.println("El brazo retira una caja");
        hayCaja = false;
        pesoActualCaja = 0;
        mutex.unlock();
    }

    public void reponerCaja() throws InterruptedException {
       mutex.lock();
       System.out.println("El brazo repone una caja");
       hayCaja = true;
       esperaEmpaquetadoras.signalAll();
       esperaBrazo.await();
       mutex.unlock();
    }
}
