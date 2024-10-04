package TrabajoPractico3;

public class Main3 {
    public static void main(String[] args) {
        Hamaca hamaca = new Hamaca();
        PlatoComida platoComida = new PlatoComida();
        Rueda rueda = new Rueda();
        Thread[] hilos = new Thread[5];
        for (int i = 0; i < hilos.length; i++) {
            Hamster hamster = new Hamster(hamaca, platoComida, rueda, ("Alan "+(i+1)));
            hilos[i] = new Thread(hamster);
        }
        for (int i = 0; i < hilos.length; i++) {
            hilos[i].start();
        }
    }
}
