package TrabajoPractico8;

public class Brazo implements Runnable {
    private Mostrador mostrador;
    public Brazo(Mostrador mostrador) {
        this.mostrador = mostrador;
    }
    public void run() {
        try {
            while (true) {
                mostrador.retirarCaja();
                mostrador.reponerCaja();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
