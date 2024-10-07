package TrabajoPractico4;

public class MainPunto6 {
    public static void main(String[] args) {
        Taxi taxi = new Taxi();
        Pasajero pasajero = new Pasajero(taxi);
        Taxista tachero = new Taxista(taxi);
        Thread hiloPasajero = new Thread(pasajero);
        Thread hiloTachero = new Thread(tachero);
        hiloPasajero.start();
        hiloTachero.start();
    }
}
