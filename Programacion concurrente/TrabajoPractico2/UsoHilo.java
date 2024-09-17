package TrabajoPractico2;

public class UsoHilo {
    public static void main(String[] args) {
        System.out.println("Hilo principal iniciando.");
        // Primero, construimos los hilos
        UnHilo mh = new UnHilo("#1");
        UnHilo mh2 = new UnHilo("#2");
        UnHilo mh3 = new UnHilo("#3");
        
        // Finalmente, comienza la ejecución de los hilos.
        mh.start();
        mh2.start();
        mh3.start();

        for (int i = 0; i < 50; i++) {
            System.out.print(" .");
        }
        try {
            Thread.sleep(100);
        } catch (InterruptedException exc) {
            System.out.println("Hilo principal interrumpido.");
        }
        System.out.println("Hilo principal finalizado.");
    }
}
