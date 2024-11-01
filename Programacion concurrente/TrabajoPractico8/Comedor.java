package TrabajoPractico8;

import java.util.concurrent.Semaphore;

public class Comedor {
    private Semaphore destapadores;
    private Semaphore[] mostradorAlmuerzo;
    private Semaphore[] mostradorPostre;
    public Comedor(int cantDestapadores, int cantMostradorAlmuerzo, int cantMostradorPostre) {
        destapadores = new Semaphore(cantDestapadores);
        mostradorAlmuerzo = new Semaphore[cantMostradorAlmuerzo];
        for (int i = 0; i < mostradorAlmuerzo.length; i++) {
            mostradorAlmuerzo[i] = new Semaphore(1);
        }
        mostradorPostre = new Semaphore[cantMostradorPostre];
        for (int i = 0; i < mostradorPostre.length; i++) {
            mostradorPostre[i] = new Semaphore(1);
        }
    }

    public void obtenerBandeja(int mostrador) throws InterruptedException {
        mostradorAlmuerzo[mostrador].acquire();
        System.out.println(Thread.currentThread().getName()+" Tomó la bandeja "+(mostrador+1));
    }

    public void dejarBandeja(int mostrador){
        System.out.println(Thread.currentThread().getName()+" Dejó la bandeja "+(mostrador+1));
        mostradorAlmuerzo[mostrador].release();
    }

    public void dejarFilaPostre(int mostrador){
        System.out.println(Thread.currentThread().getName()+" deja la fila del postre "+(mostrador));
        mostradorPostre[mostrador].release();
    }

    public void obtenerPostre(int mostrador) throws InterruptedException {
        mostradorPostre[mostrador].acquire();
        System.out.println(Thread.currentThread().getName()+" Tomá el postre "+(mostrador));
    }

    public void usarDestapador() throws InterruptedException {
        destapadores.acquire();
        System.out.println(Thread.currentThread().getName()+ " Toma un destapador");
    }

    public void dejarDestapador(){
        System.out.println(Thread.currentThread().getName()+" deja un destapador");
        destapadores.release();
    }
}
