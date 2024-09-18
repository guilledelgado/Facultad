package TrabajoPractico3;

import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Visitante implements Runnable {
    private int[] areasReservadas = new int[10];
    private ParqueTematico parqueTematico;

    public Visitante(ParqueTematico pt) {
        parqueTematico = pt;
    }

    private synchronized boolean reservarArea(int area) {
        boolean exito = false;
        if (areasReservadas[area] == 0 && !parqueTematico.estaLleno(area)) {
            parqueTematico.reservarLugar(area);
            areasReservadas[area] = 1;
            exito = true;
        }
        return exito;
    }

    public void run() {
        for (int i = 0; i < 5; i++) {
            try {
                int areaElegida = new Random().nextInt(10);
                boolean exito = reservarArea(areaElegida);
                if (exito) {
                    System.out.println("El " + Thread.currentThread().getName() + " reservó el area " + (areaElegida + 1) + " con éxito");
                } else {
                    System.out.println("El " + Thread.currentThread().getName() + " falló al intentar reservar el area " + (areaElegida + 1));
                }
                Thread.sleep(1000);

            } catch (InterruptedException ex) {
                Logger.getLogger(VerificarCuenta.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
