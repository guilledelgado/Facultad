package TrabajoPractico3;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Hamster implements Runnable {
    private Hamaca hamaca;
    private PlatoComida platoComida;
    private Rueda rueda;
    private String nombre;

    public Hamster(Hamaca h, PlatoComida pc, Rueda r, String nombre) {
        this.hamaca = h;
        this.platoComida = pc;
        this.rueda = r;
        this.nombre = nombre;
    }

    private void usarHamaca() throws InterruptedException{
        synchronized (hamaca){
            System.out.println("Hamaca en uso por "+ nombre);
            hamaca.usarHamaca();
            Thread.sleep(3000);
            hamaca.dejarHamaca();
            System.out.println("Hamaca disponible");
        }

    }

    private void usarPlatoComida() throws InterruptedException{
        synchronized (platoComida){
            System.out.println("Plato comida en uso por "+ nombre);
            platoComida.usarPlato();
            Thread.sleep(3000);
            platoComida.dejarPlato();
            System.out.println("Plato comida disponible");
        }

    }

    private void usarRueda() throws InterruptedException{
        synchronized (rueda){
            System.out.println("Rueda en uso por "+ nombre);
            rueda.usarRueda();
            Thread.sleep(3000);
            rueda.dejarRueda();
            System.out.println("Rueda disponible");
        }


    }
    public void run(){
        try {
            this.usarRueda();
            this.usarPlatoComida();
            this.usarHamaca();
        } catch (InterruptedException e) {
            Logger.getLogger(Hamster.class.getName()).log(Level.SEVERE,null,e);
        }

    }
}
