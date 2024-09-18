package TrabajoPractico3;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Jaula implements Runnable {
    private Hamaca hamaca;
    private PlatoComida platoComida;
    private Rueda rueda;

    public Jaula(Hamaca h, PlatoComida pc, Rueda r) {
        hamaca = h;
        platoComida = pc;
        rueda = r;
    }

    private synchronized void usarHamaca() throws InterruptedException {
        System.out.println(Thread.currentThread().getName() + " esta usando la hamaca.");
        hamaca.usarHamaca();
        Thread.sleep(3000);
        System.out.println(Thread.currentThread().getName() + " deja de usar la hamaca.");
        hamaca.dejarHamaca();
    }

    private synchronized void usarPlatoComida() throws InterruptedException {
        System.out.println(Thread.currentThread().getName() + " esta usando la platoComida.");
        platoComida.usarPlato();
        Thread.sleep(3000);
        System.out.println(Thread.currentThread().getName() + " deja de usar la platoComida.");
        platoComida.dejarPlato();
    }

    private synchronized void usarRueda() throws InterruptedException {
        System.out.println(Thread.currentThread().getName() + " esta usando la rueda.");
        rueda.usarRueda();
        Thread.sleep(3000);
        System.out.println(Thread.currentThread().getName() + " deja de usar la rueda.");
        rueda.dejarRueda();
    }

    public void run() {
        try{
            this.usarPlatoComida();
            this.usarHamaca();
            this.usarRueda();
            System.out.println(Thread.currentThread().getName() + " Ya usó todos los elementos de la jaula");
        } catch(InterruptedException e) {
            Logger.getLogger(Entidad.class.getName()).log(Level.SEVERE,null,e);
        }
    }
}
