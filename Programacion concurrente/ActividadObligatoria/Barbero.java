package ActividadObligatoria;

public class Barbero implements Runnable {
    private Sillon sillon;

    public Barbero(Sillon sillon) {
        this.sillon = sillon;
    }

    private void cortarPelo() throws InterruptedException {
        System.out.println("Cortando el pelo");
        Thread.sleep(5000);
        System.out.println("Termino");
    }

    public void run() {
        System.out.println("Durmiendo...");
        try{
            while (true) {
                sillon.esperarCliente();
                this.cortarPelo();
                sillon.terminarCorte();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
