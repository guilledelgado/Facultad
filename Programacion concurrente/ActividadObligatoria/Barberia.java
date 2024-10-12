package ActividadObligatoria;
//SOLUCION DE TIAGO

import java.util.concurrent.Semaphore;

public class Barberia {
    private static Semaphore semSillon = new Semaphore(1); // No puede iniciar
    private static Semaphore semBarbero = new Semaphore(0);
    private static Semaphore semCliente = new Semaphore(0);
    private static Semaphore semSillasEspera = new Semaphore(10);// generico

    public static void main(String[] args) {
        Thread jose = new Thread(new Barbero());
        Thread[] colClientes = new Thread[12];
        for (int i = 0; i < 12; i++) {
            colClientes[i] = new Thread(new Cliente());
        }
        for (int i = 0; i < 12; i++) {
            colClientes[i].start();
        }
        jose.start();// Inicia el barbero
    }

    static class Cliente implements Runnable {
        @Override
        public void run() {
            try {
                if (semSillasEspera.tryAcquire()) {// Se mete a la barbería si tiene lugar.
                    semSillon.acquire(); // Se sienta en la silla libre.
                    semSillasEspera.release();// Libera un espacio de las sillas de espera.
                    semBarbero.release(); // Despierta al barbero.
                    semCliente.acquire(); // El cliente espera mientras le cortan el pelo. como inicia en 0, solo
                    // continuara cuando el sleep termine porque tiene un release
                    semSillon.release(); // El cliente se retira del establecimiento bailable.
                } else {
                    System.out.println("Cliente se fue por no tener lugar.");
                }
            } catch (Exception e) {
            }
        }
    }

    static class Barbero implements Runnable {
        @Override
        public void run() {
            while (true) {
                try {
                    int i = (int) (Math.random() * 5) + 1;
                    semBarbero.acquire(); // El barbero es notificado que tiene que empezar
                    System.out.println("Cortando el pelo.");
                    Thread.sleep(i * 1000);
                } catch (Exception e) {
                }
                System.out.println("Corte de pelo terminado.");
                semCliente.release(); // Se le avisa al cliente que terminaron el corte.
                // El barbero queda bloqueado de base nuevamente.
            }
 }
}

}
