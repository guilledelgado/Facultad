package TrabajoPractico7;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class GestorSala {
    private int temperaturaActual;
    private int tUmbral;
    private int cantMaximaPersonas;
    private int cantActualPersonas;
    private int cantJubilados;
    private final Lock mutex = new ReentrantLock();
    private final Condition hayLugar;

    public GestorSala(int temperaturaActual) {
        this.temperaturaActual = temperaturaActual;
        this.tUmbral = 30;
        this.cantMaximaPersonas = 50;
        this.cantActualPersonas = 0;
        this.cantJubilados = 0;
        this.hayLugar = mutex.newCondition();
    }

    public void entrarSala() throws InterruptedException {
        mutex.lock();
        while (cantActualPersonas > cantMaximaPersonas || cantJubilados > 0){
            hayLugar.await();
        }
        System.out.println(Thread.currentThread().getName() + " entró a la sala");
        cantActualPersonas++;
        mutex.unlock();
    }

    public void entrarSalaJubilado() throws InterruptedException {
        mutex.lock();
        cantJubilados++;
        while(cantActualPersonas > cantMaximaPersonas){
            hayLugar.await();
        }
        System.out.println(Thread.currentThread().getName() + " entró a la sala");
        cantJubilados--;
        cantActualPersonas++;
        mutex.unlock();

    }

    public void salirSala(){
        mutex.lock();
        System.out.println(Thread.currentThread().getName() + " sale de la sala");
        cantActualPersonas--;
        hayLugar.signalAll();
        mutex.unlock();
    }

    public void notificarTemperatura(int temperatura){
        mutex.lock();
        System.out.println("NUEVA TEMPERATURA ACTUAL: "+temperatura+"° C");
        temperaturaActual = temperatura;
        if(temperaturaActual > tUmbral){
            cantMaximaPersonas = 35;
        } else {
            cantMaximaPersonas = 50;
        }
        System.out.println("CANTIDAD MAXIMA DE PERSONAS: "+cantMaximaPersonas);
        mutex.unlock();
    }

}
