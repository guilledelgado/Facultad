package TrabajoPractico5;

import java.util.concurrent.Semaphore;

public class Comedero {
    private Semaphore mutex;
    private Semaphore comederos;
    private int limiteAnimales;
    private int cuantosComieron;
    private boolean comePerro;
    private boolean comeGato;

    public Comedero() {
        mutex = new Semaphore(1);
        comederos = new Semaphore(3);
        limiteAnimales = 4;
        cuantosComieron = 0;
        comePerro = true;
        comeGato = false;
    }

    public boolean comerPerro() throws InterruptedException {
        boolean comio = false;
        mutex.acquire();
        if(comePerro && cuantosComieron < limiteAnimales){
            comederos.acquire(2);
            System.out.println("\033[0;32m"+Thread.currentThread().getName()+" entró a comer\033[0m");
            cuantosComieron++;
            comio = true;
        }
        if(cuantosComieron == limiteAnimales){
            System.out.println("comieron "+(cuantosComieron)+" perros");
            comePerro = false;
            comeGato = true;
            cuantosComieron = 0;
        }
        mutex.release();
        return comio;
    }

    public void terminaComerPerro(){
        System.out.println("\033[0;31m"+Thread.currentThread().getName()+" dejó de comer\033[0m");
        comederos.release(2);
    }

    public boolean comerGato() throws InterruptedException {
        boolean comio = false;
        mutex.acquire();
        if(comeGato && cuantosComieron <= limiteAnimales){
            comederos.acquire();
            System.out.println("\033[0;32m"+Thread.currentThread().getName()+" entró a comer\033[0m");
            cuantosComieron++;
            comio = true;
        }
        if(cuantosComieron == limiteAnimales){
            System.out.println("comieron "+(cuantosComieron)+" gatos");
            comeGato = false;
            comePerro = true;
            cuantosComieron = 0;
        }
        mutex.release();
        return comio;
    }

    public void terminaComerGato(){
        System.out.println("\033[0;31m"+Thread.currentThread().getName()+" dejó de comer\033[0m");
        comederos.release();
    }

    public void cambiarPrioridad(){
        comePerro = !comePerro;
        comeGato = !comeGato;
    }
}
