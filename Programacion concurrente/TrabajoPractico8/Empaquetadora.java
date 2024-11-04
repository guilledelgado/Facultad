package TrabajoPractico8;

public class Empaquetadora implements Runnable{
    private Mostrador mostrador;
    private int pesoPastel;

    public Empaquetadora(Mostrador mostrador) {
        this.mostrador = mostrador;
        pesoPastel = 0;
    }

    public void run() {
        try {
            while (true) {
                pesoPastel = mostrador.tomarPastel();
                mostrador.soltarPastel(pesoPastel);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
