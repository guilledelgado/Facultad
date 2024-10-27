package TrabajoPractico5;

public class GestorComedero implements Runnable {
    private Comedero comedero;

    public GestorComedero(Comedero comedero) {
        this.comedero = comedero;
    }

    public void run() {
        try {
            while (true) {
                Thread.sleep(10000);
                comedero.cambiarPrioridad();
            }

        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
