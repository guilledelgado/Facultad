package TrabajoPractico6;

import java.util.Random;

public class Auto implements Runnable{
    private GestorPuente puente;
    private String sentido;
    private String nombre;

    public Auto(GestorPuente puente, String sentido, String n) {
        this.puente = puente;
        this.sentido = sentido;
        this.nombre = n;
    }
    private void pasarNorte() throws InterruptedException {
        puente.EntrarColaNorte(nombre);
        puente.EntrarCocheDelNorte(nombre);
        Thread.sleep(new Random().nextInt(2,7)* 1000L);
        puente.SalirCocheDelNorte(nombre);
    }

    private void pasarSur() throws InterruptedException {
        puente.EntrarColaSur(nombre);
        puente.EntrarCocheDelSur(nombre);
        Thread.sleep(new Random().nextInt(2,7)* 1000L);
        puente.SalirCocheDelSur(nombre);
    }

    public void run() {
        try {
            if (sentido.equalsIgnoreCase("norte")) {
                this.pasarNorte();
            } else if (sentido.equalsIgnoreCase("sur")) {
                this.pasarSur();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
