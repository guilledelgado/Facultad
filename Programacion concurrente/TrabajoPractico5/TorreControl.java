package TrabajoPractico5;

import java.util.concurrent.Semaphore;

public class TorreControl {
    private Semaphore mutex;
    private Semaphore usarPista;
    private Semaphore listosParaAterrizar;
    private Semaphore listosParaDespegar;
    private int cuantosAterrizaron;

    public TorreControl() {
        mutex = new Semaphore(1);
        listosParaDespegar = new Semaphore(0);
        usarPista = new Semaphore(1);
        listosParaAterrizar = new Semaphore(10);
        cuantosAterrizaron = 0;
    }

    public void listoAterrizaje() throws InterruptedException {
        listosParaAterrizar.acquire();
    }

    public void despegar() throws InterruptedException {
        listosParaDespegar.acquire();
        usarPista.acquire();
        listosParaDespegar.release(10);

    }

    public void sumarAterrizaje() throws InterruptedException {
        mutex.acquire();
        cuantosAterrizaron++;
        if(cuantosAterrizaron == 10) {
            listosParaAterrizar.release();
            cuantosAterrizaron = 0;
        }
        mutex.release();
    }

    public void usarPista() throws InterruptedException {
        usarPista.acquire();
    }

    public void dejarPista(){
        usarPista.release();
    }
}
