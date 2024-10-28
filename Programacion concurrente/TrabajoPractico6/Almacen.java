package TrabajoPractico6;

public class Almacen {
    private int productosActuales = 0;
    private int maximaCantidadProductos;

    public Almacen(int max) {
        this.maximaCantidadProductos = max;
    }

    public synchronized void reponer() throws InterruptedException {
        while (productosActuales >= maximaCantidadProductos) {
            this.wait();
        }
        productosActuales++;
        System.out.println("Productor repuso");
        System.out.println("Cantidad de productos: " + productosActuales);
        if (productosActuales == 1){
            System.out.println("HAY PRODUCTOS");
            this.notify();
        }
    }

    public synchronized void retirar() throws InterruptedException {
        while (productosActuales == 0){
            this.wait();
        }
        productosActuales--;
        System.out.println("Consumidor retiro");
        System.out.println("Cantidad de productos: " + productosActuales);
        if (productosActuales == maximaCantidadProductos-1){
            System.out.println("SE PUEDE REPONER");
            this.notify();
        }
    }
}
