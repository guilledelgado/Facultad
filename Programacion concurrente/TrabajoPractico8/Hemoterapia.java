package TrabajoPractico8;

import java.util.concurrent.locks.*;

public class Hemoterapia {
    private int camillas;
    private int camillasOcupadas = 0;
    private int revistas;
    private int revistasOcupadas = 0;
    private ReentrantLock mutex = new ReentrantLock(true);
    private Condition esperandoCamilla = mutex.newCondition();
    private Condition esperandoRevista = mutex.newCondition();

    public Hemoterapia(int camillas, int revistas) {
        this.camillas = camillas;
        this.revistas = revistas;
    }

    public void comenzarExtraccion() {
        mutex.lock();
        try {
            while (camillasOcupadas >= camillas) {
                while (revistasOcupadas >= revistas) {
                    System.out.println(Thread.currentThread().getName() + " se pone a mirar la tele");
                    esperandoRevista.await();
                }
                revistasOcupadas++;
                System.out.printf(Thread.currentThread().getName() + " se pone a leer una revista");
                esperandoCamilla.await();
            }
            if (revistasOcupadas > 0) {
                revistasOcupadas--;
                esperandoRevista.signal();
            }
            System.out.println(Thread.currentThread().getName() + " pasa a la sala de tratamiento");
            camillasOcupadas++;
        } catch (InterruptedException ie) {
            System.out.println(ie.getMessage());
        } finally {
            mutex.unlock();
        }
    }

    private void terminarExtraccion() {
        mutex.lock();
        camillasOcupadas--;
        esperandoCamilla.signal();
        mutex.unlock();
    }
}
