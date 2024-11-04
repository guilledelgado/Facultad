package Parciales;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Almacen {
    private int cantidadBotellasAguaSaborizada;
    private int cantidadBotellasVino;
    private int cantidadCajas; //si se llegan a 10 cajas, se llama al transporte
    private boolean hayCajaVino;
    private boolean hayCajaAguaSaborizada;
    private Lock mutex = new ReentrantLock();
    private Condition esperaEmbotelladorVino;
    private Condition esperaEmbotelladorAguaSaborizada;
    private Condition esperaEmpaquetador;
    private Condition esperaTransporte;

    public Almacen() {
        cantidadBotellasAguaSaborizada = 0;
        cantidadBotellasVino = 0;
        cantidadCajas = 0;
        hayCajaVino = true;
        hayCajaAguaSaborizada = true;
        esperaEmbotelladorVino = mutex.newCondition();
        esperaEmbotelladorAguaSaborizada = mutex.newCondition();
        esperaEmpaquetador = mutex.newCondition();
        esperaTransporte = mutex.newCondition();
    }

    public void ponerBotellaVino() throws InterruptedException {
        mutex.lock();
        while (cantidadBotellasVino > 10 || !hayCajaVino) {
            esperaEmbotelladorVino.await();
        }
        cantidadBotellasVino++;
        if(cantidadBotellasVino == 10) {
            esperaEmpaquetador.signal();
        }
        mutex.unlock();
    }

    public void ponerBotellaAguaSaborizada() throws InterruptedException {
        mutex.lock();
        while (cantidadBotellasAguaSaborizada > 10 || !hayCajaAguaSaborizada) {
            esperaEmbotelladorAguaSaborizada.await();
        }
        cantidadBotellasAguaSaborizada++;
        if(cantidadBotellasAguaSaborizada == 10) {
            esperaEmpaquetador.signal();
        }
        mutex.unlock();
    }

    public void guardarCaja() throws InterruptedException {
        mutex.lock();
        while(cantidadBotellasAguaSaborizada < 10 && cantidadBotellasVino < 10){
            esperaEmpaquetador.await();
        }
        if(cantidadBotellasVino == 10){
            hayCajaVino = false;
        }
        if (cantidadBotellasAguaSaborizada == 10) {
            hayCajaAguaSaborizada = false;
        }
        mutex.unlock();
    }

    public void reponerCaja() {
        mutex.lock();
        cantidadCajas++;
        if(cantidadCajas == 10) {
            esperaTransporte.signal();
        }
        if(cantidadBotellasAguaSaborizada == 10){
            cantidadBotellasAguaSaborizada = 0;
            hayCajaAguaSaborizada = true;
            esperaEmbotelladorAguaSaborizada.signalAll();
        }
        if(cantidadBotellasVino == 10){
            cantidadBotellasVino = 0;
            hayCajaVino = true;
            esperaEmbotelladorVino.signalAll();
        }
        mutex.unlock();
    }

    public void llevarseCajas() throws InterruptedException {
        mutex.lock();
        while(cantidadCajas<10){
            esperaTransporte.await();
        }
        cantidadCajas = 0;
        mutex.unlock();
    }
}
