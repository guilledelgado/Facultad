package Parciales;

import java.util.concurrent.Semaphore;

public class FormacionAgua {
    private Semaphore semOxigeno;
    private Semaphore semHidrogeno;
    private Semaphore oxigenoProceda;
    private Semaphore aguaLista;
    private Semaphore mutex;
    private int capacidadTotalRecipiente;
    private int capacidadActualRecipiente;

    public FormacionAgua(int capacidadTotalRecipiente) {
        semOxigeno = new Semaphore(1);
        semHidrogeno = new Semaphore(2);
        aguaLista = new Semaphore(0);
        oxigenoProceda = new Semaphore(0);
        mutex = new Semaphore(1);
        this.capacidadTotalRecipiente = capacidadTotalRecipiente;
        this.capacidadActualRecipiente = 0;
    }

    public void oListo() throws InterruptedException {
        //Entra uno de oxigeno y espera a que hayan 2 de hidrogeno
        semOxigeno.acquire();
        System.out.println(Thread.currentThread().getName() + ": Listo");
    }

    public void hListo() throws InterruptedException {
        //Entran uno de hidrogeno y libera el semaforo para que el oxigeno siga
        semHidrogeno.acquire();
        oxigenoProceda.release();
        System.out.println(Thread.currentThread().getName() + ": Listo");
    }

    public void esperaHidrogeno() throws InterruptedException {
        //El oxigeno espera a que lleguen los dos de hidrogeno
        oxigenoProceda.acquire(2);
    }

    public void esperaAguaLista() throws InterruptedException {
        //El hidrogeno espera a que el agua este lista para finalizar su funcion
        aguaLista.acquire();
    }

    public void aguaLista() throws InterruptedException {
        mutex.acquire();
        System.out.println(Thread.currentThread().getName() + ": Forma el agua");
        capacidadActualRecipiente++;
        System.out.println("Cantidad de agua Recipiente: " + capacidadActualRecipiente);
        if(capacidadActualRecipiente == capacidadTotalRecipiente){
            System.out.println("Se cambia el recipiente");
            //Si se llega a la capacidad total, simula que el recipiente pasa a distribucion y se coloca uno nuevo
            capacidadActualRecipiente = 0;
        }
        aguaLista.release(2);
        semHidrogeno.release(2);
        semOxigeno.release();
        mutex.release();
    }
}
