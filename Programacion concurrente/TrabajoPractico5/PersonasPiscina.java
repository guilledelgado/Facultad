package TrabajoPractico5;

import java.util.Random;

public class PersonasPiscina implements Runnable {
    private int tiempoEnPiscina;
    private GestorPiscina gestorPiscina;

    public PersonasPiscina(GestorPiscina gestorPiscina) {
        this.gestorPiscina = gestorPiscina;
        this.tiempoEnPiscina = new Random().nextInt(5);
    }

    public void run() {
        try{
            gestorPiscina.entraPiscina();
            Thread.sleep(tiempoEnPiscina*1000);
            gestorPiscina.salirPiscina();
        } catch (InterruptedException ex) {

        }
    }
}
