package Parciales;
import java.util.concurrent.locks.*;
public class ParqueVecinal {
    private int aforoMaxSinEscuelas;
    private int aforoMaxEscuela;
    private int aforo;
    private int cantPersonasActualmente=0;
    private int colaEscuela=0;
    private int colaResidentes=0;
    private int colaNoResidentes=0;
    private int cantEscuelasAdentro = 0;
    private ReentrantLock mutex = new ReentrantLock();
    private Condition escuelaEsperando = mutex.newCondition();
    private Condition residenteEsperando = mutex.newCondition();
    private Condition noResidenteEsperando = mutex.newCondition();

    public ParqueVecinal(int aforoMax, int aforoMaxEscuela) {
        this.aforoMaxSinEscuelas = aforoMax;
        this.aforoMaxEscuela = aforoMaxEscuela;
        this.aforo = aforoMax;
    }

    public void entraEscuela(int cantAlumnos) throws InterruptedException {
        mutex.lock();
        colaEscuela++;
        while (cantAlumnos + cantPersonasActualmente > aforo) {
            escuelaEsperando.await();
        }
        System.out.println(Thread.currentThread().getName() + " entró al parque");
        colaEscuela--;
        cantEscuelasAdentro++;
        cantPersonasActualmente+=cantAlumnos;
        if(aforo != aforoMaxEscuela){
            aforo = aforoMaxEscuela;
        }
        mutex.unlock();
    }

    public void entraResidente() throws InterruptedException {
        mutex.lock();
        colaResidentes++;
        while(cantPersonasActualmente > aforo || colaEscuela > 0) {
            residenteEsperando.await();
        }
        System.out.println(Thread.currentThread().getName()+" entró al parque");
        colaResidentes--;
        cantPersonasActualmente++;
        mutex.unlock();
    }

    public void entraNoResidente() throws InterruptedException {
        mutex.lock();
        colaNoResidentes++;
        while(cantPersonasActualmente > aforo || (colaEscuela > 0 && colaResidentes > 0)) {
            noResidenteEsperando.await();
        }
        System.out.println(Thread.currentThread().getName()+" entró al parque");
        colaNoResidentes--;
        cantPersonasActualmente++;
        mutex.unlock();
    }

    public void saleEscuela(int cantAlumnos) throws InterruptedException {
        mutex.lock();
        System.out.println(Thread.currentThread().getName()+" sale del parque");
        cantPersonasActualmente-=cantAlumnos;
        cantEscuelasAdentro--;
        if(cantEscuelasAdentro == 0){
            aforo = aforoMaxSinEscuelas;
        }
        this.hacerPasar();
        mutex.unlock();
    }

    public void salePersona() throws InterruptedException {
        mutex.lock();
        System.out.println(Thread.currentThread().getName()+" sale del parque");
        cantPersonasActualmente--;
        this.hacerPasar();
        mutex.unlock();
    }

    private void hacerPasar(){
        if(colaEscuela > 0){
            escuelaEsperando.signal();
        } else if(colaResidentes > 0){
            residenteEsperando.signal();
        } else if(colaNoResidentes > 0){
            noResidenteEsperando.signal();
        }
    }
}
