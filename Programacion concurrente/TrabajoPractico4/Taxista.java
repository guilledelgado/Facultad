package TrabajoPractico4;

public class Taxista implements Runnable{
    private Taxi tacho;

    public Taxista(Taxi taxi) {
        tacho = taxi;
    }

    private void llevarPasajero() throws InterruptedException{
        System.out.println("LLevando pasajero...");
        Thread.sleep(5000);
        System.out.println("En destino");
    }

    public void run() {
        try {
            while (true) {
                System.out.println("Taxista durmiendo");
                tacho.esperarViaje();
                this.llevarPasajero();
                tacho.enDestino();
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
