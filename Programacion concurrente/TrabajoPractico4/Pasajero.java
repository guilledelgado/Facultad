package TrabajoPractico4;

public class Pasajero implements Runnable{
    private Taxi tacho;
    public Pasajero(Taxi tacho) {
        this.tacho = tacho;
    }

    private void buscarTaxi() throws InterruptedException {
        System.out.println("Pasajero buscando taxi...");
        Thread.sleep(5000);
        System.out.println("Encuentra taxi");
    }

    public void run() {
        try {
            while (true) {
                this.buscarTaxi();
                tacho.subirse();
                tacho.viajar();
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
