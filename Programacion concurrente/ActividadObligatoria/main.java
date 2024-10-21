package ActividadObligatoria;

public class main {
    public static void main(String[] args) {
        Barberia barberia = new Barberia();
        Thread jose = new Thread(new Barbero(barberia));
        Thread[] colClientes = new Thread[13];
        jose.start();
        for (int i = 0; i < 13; i++) {
            colClientes[i] = new Thread(new ClienteBarbero(barberia));
        }
        for (int i = 0; i < 13; i++) {
            colClientes[i].start();
        }

    }

}
