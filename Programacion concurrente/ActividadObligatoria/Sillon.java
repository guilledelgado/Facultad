package ActividadObligatoria;

import java.util.concurrent.Semaphore;

public class Sillon {
    private Semaphore estado;
    private Semaphore corte;

    public Sillon() {
        estado = new Semaphore(0);
        corte = new Semaphore(0);
    }

    public void esperarCliente() throws InterruptedException {
        estado.acquire();
    }

    public void sentarCliente(){
        estado.release();
    }

    public void solicitarCorte() throws InterruptedException {
        corte.acquire();
    }

    public void terminarCorte(){
        corte.release();
    }
}
