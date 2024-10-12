package ActividadObligatoria;

public class main {
    public static void main(String[] args) {
        Sillon sillon = new Sillon();
        Barbero barbero = new Barbero(sillon);
        ClienteBarbero cliente = new ClienteBarbero(sillon);
        Thread clienteH = new Thread(cliente);
        Thread barberoH = new Thread(barbero, "Barbero");
        barberoH.start();
        clienteH.start();
    }
}
