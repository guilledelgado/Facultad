package TrabajoPractico3;

public class Main5 {
    public static void main(String[] args) {
        Surtidor surtidor = new Surtidor(1000);
        Auto auto1 = new Auto("Renault", "Clio", 0, "JOS749", 50, surtidor);
        Auto auto2 = new Auto("Toyota", "Corolla", 0, "MLO105", 50, surtidor);
        Auto auto3 = new Auto("Toyota", "Hilux", 0, "AD754WE", 100, surtidor);
        Auto auto4 = new Auto("Hyundai", "Tucson", 0, "GHO781", 100, surtidor);
        Auto auto5 = new Auto("Renault", "Fluence", 0, "BAH614", 50, surtidor);
        Thread thread1 = new Thread(auto1);
        Thread thread2 = new Thread(auto2);
        Thread thread3 = new Thread(auto3);
        Thread thread4 = new Thread(auto4);
        Thread thread5 = new Thread(auto5);
        thread1.start();
        thread2.start();
        thread3.start();
        thread4.start();
        thread5.start();
    }
}
