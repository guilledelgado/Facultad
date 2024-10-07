package TrabajoPractico4;

import java.util.concurrent.Semaphore;

public class Taxi {
    private Semaphore estado;
    private Semaphore enDestino;

    public Taxi() {
        estado = new Semaphore(0);
        enDestino = new Semaphore(0);
    }

    public void esperarViaje() throws InterruptedException {
        estado.acquire();
    }
    public void subirse(){
        estado.release();
    }

    public void viajar() throws InterruptedException {
        enDestino.acquire();
    }

    public void enDestino(){
        enDestino.release();
    }
}
