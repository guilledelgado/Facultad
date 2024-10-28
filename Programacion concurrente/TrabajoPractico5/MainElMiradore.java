package TrabajoPractico5;

public class MainElMiradore {
    public static void main(String[] args) {
        ElMirador mirador = new ElMirador();
        Encargado encargado = new Encargado(mirador);
        Thread t1 = new Thread(encargado);
        t1.start();
        for (int i = 0; i < 7; i++) {
            VisitanteMirador visitante = new VisitanteMirador(mirador);
            Thread t2 = new Thread(visitante, "visitante "+(i+1));
            t2.start();
        }
    }
}
