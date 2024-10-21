package ActividadObligatoria;

public class Barbero implements Runnable {
    private Barberia barberia;

    public Barbero(Barberia bar) {
        this.barberia = bar;
    }

    private void cortarPelo() throws InterruptedException {
        int tiempo = (int) (Math.random()*5 + 1);
        System.out.println("Cortando el pelo");
        Thread.sleep(tiempo * 1000);
        System.out.println("Termino");
    }

    public void run() {
        System.out.println("Durmiendo...");
        try{
            while (true) {
                barberia.aMimir();
                this.cortarPelo();
                barberia.elBarberoYaHizoSuMagia();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
