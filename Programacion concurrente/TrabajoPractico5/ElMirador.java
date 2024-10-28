package TrabajoPractico5;
import java.util.concurrent.Semaphore;
public class ElMirador {
    private Semaphore escalera;
    private Semaphore toboganes;
    private Semaphore mutex;
    private int cantToboganes = 2;
    private int toboganesDisponibles = 2;

    public ElMirador(){
        escalera = new Semaphore(5);
        toboganes = new Semaphore(0);
        mutex = new Semaphore(1);
    }

    public void subirEscalera() throws InterruptedException {
        escalera.acquire();
        System.out.println(Thread.currentThread().getName() + " sube la escalera");
    }

    public void dejarEscalera(){
        escalera.release();
    }

    public void subirTobogan() throws InterruptedException {
        toboganes.acquire();
    }

    public void hacerPasarTobogan() throws InterruptedException {
        mutex.acquire();
        if(toboganesDisponibles <= cantToboganes && toboganesDisponibles > 0) {
            toboganes.release();
            toboganesDisponibles--;
        }
        mutex.release();
    }

    public void terminaElTobogan() throws InterruptedException {
        mutex.acquire();
        toboganesDisponibles++;
        mutex.release();
    }
}
