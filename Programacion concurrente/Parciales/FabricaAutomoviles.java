package Parciales;
import java.util.concurrent.locks.*;
public class FabricaAutomoviles {
    private int cantMaxRuedas;
    private int cantActualRuedas = 0;
    private int cantMaxPuertas;
    private int cantActualPuertas = 0;
    private int cantMaxCarrocerias;
    private int cantActualCarrocerias = 0;
    private int cantAutosProducidos = 0;
    private Lock mutex = new ReentrantLock();
    private Condition esperaRuedas = mutex.newCondition();
    private Condition esperaPuertas = mutex.newCondition();
    private Condition esperaCarrocerias = mutex.newCondition();
    private Condition esperaEnsamblador = mutex.newCondition();

    public FabricaAutomoviles(int capacidadRuedas, int capacidadPuertas, int capacidadCarrocerias) {
        this.cantMaxRuedas = capacidadRuedas;
        this.cantMaxPuertas = capacidadPuertas;
        this.cantMaxCarrocerias = capacidadCarrocerias;
    }

    public void producirRuedas() throws InterruptedException {
        mutex.lock();
        while(cantActualRuedas > cantMaxRuedas) {
            esperaRuedas.await();
        }
        cantActualRuedas++;
        if(cantActualRuedas >= 4) {
            esperaEnsamblador.signal();
        }
        mutex.unlock();
    }

    public void producirPuertas() throws InterruptedException {
        mutex.lock();
        while(cantActualPuertas > cantMaxPuertas) {
            esperaPuertas.await();
        }
        cantActualPuertas++;
        if(cantActualPuertas >= 2) {
            esperaEnsamblador.signal();
        }
        mutex.unlock();
    }
    public void producirCarrocerias() throws InterruptedException {
        mutex.lock();
        while(cantActualCarrocerias > cantMaxCarrocerias) {
            esperaCarrocerias.await();
        }
        cantActualCarrocerias++;
        if(cantActualCarrocerias >= 1) {
            esperaEnsamblador.signal();
        }
    }

    public void producirAutomovil() throws InterruptedException {
        mutex.lock();
        while(cantActualRuedas < 4 || cantActualPuertas < 2 || cantActualCarrocerias < 1) {
            esperaEnsamblador.await();
        }
        cantActualRuedas-=4;
        cantActualPuertas-=2;
        cantActualCarrocerias--;
        cantAutosProducidos++;
        if(cantAutosProducidos ==5) {
            System.out.println("Se empaquetan 5 autos listos para distribuir");
            cantAutosProducidos = 0;
        }
        esperaRuedas.signalAll();
        esperaPuertas.signalAll();
        esperaCarrocerias.signalAll();
        mutex.unlock();
    }
}
