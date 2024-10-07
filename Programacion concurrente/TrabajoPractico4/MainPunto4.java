package TrabajoPractico4;

public class MainPunto4 {
    public static void main(String[] args) {
        GestorImpresoras gestorImpresoras = new GestorImpresoras(3);
        Cliente[] clientes = new Cliente[10];
        Thread[] threads = new Thread[10];
        for (int i = 0; i < clientes.length; i++) {
            clientes[i] = new Cliente(gestorImpresoras);
        }
        for (int i = 0; i < clientes.length; i++) {
            threads[i] = new Thread(clientes[i], "Cliente " + i);
        }
        for (int i = 0; i < clientes.length; i++) {
            threads[i].start();
        }
    }
}
