package TrabajoPractico5;

import java.util.concurrent.Semaphore;

public class GestorPiscina {
    private Semaphore piscina;

    public GestorPiscina() {
        piscina = new Semaphore(10);
    }

    public void entraPiscina() throws InterruptedException {
        piscina.acquire();
    }

    public void salirPiscina(){
        piscina.release();
    }
}
