package TrabajoPractico7;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Cocina {
    private int cantidadCarne;
    private int cantidadVerdura;
    private int cantidadPasta;
    private Lock mutex = new ReentrantLock();
    private Condition esperaCocineroCarne;
    private Condition esperaCocineroPasta;
    private Condition esperaCocineroVerdura;

    public Cocina() {
        cantidadCarne = 10;
        cantidadVerdura = 10;
        cantidadPasta = 10;
        esperaCocineroCarne = mutex.newCondition();
        esperaCocineroPasta = mutex.newCondition();
    }

    public void colocarVerduras{
        mutex.lock();

        mutex.unlock();
    }
}
