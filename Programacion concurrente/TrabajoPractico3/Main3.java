package TrabajoPractico3;

public class Main3 {
    public static void main(String[] args) {
        Hamaca hamaca = new Hamaca();
        PlatoComida platoComida = new PlatoComida();
        Rueda rueda = new Rueda();
        Jaula jaula = new Jaula(hamaca, platoComida, rueda);
        Thread h1 = new Thread(jaula, "Hamster 1");
        Thread h2 = new Thread(jaula, "Hamster 2");
        Thread h3 = new Thread(jaula, "Hamster 3");
        Thread h4 = new Thread(jaula, "Hamster 4");
        Thread h5 = new Thread(jaula, "Hamster 5");

        h1.start();
        h2.start();
        h3.start();
        h4.start();
        h5.start();
    }
}
