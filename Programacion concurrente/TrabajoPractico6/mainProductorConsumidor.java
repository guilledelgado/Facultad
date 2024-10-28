package TrabajoPractico6;

public class mainProductorConsumidor {
    public static void main(String[] args) {
        Almacen almacen = new Almacen(5);
        Productor productor = new Productor(almacen);
        Consumidor consumidor = new Consumidor(almacen);
        Thread productorThread = new Thread(productor);
        Thread consumidorThread = new Thread(consumidor);
        productorThread.start();
        consumidorThread.start();
    }
}
