package TrabajoPractico3;

public class Main2 {
    public static void main(String[] args) {
        Energia energia = new Energia();
        Entidad entidad1 = new Entidad((-3), energia);
        Entidad entidad2 = new Entidad(3, energia);
        Thread e1 = new Thread(entidad1, "Criatura Oscura");
        Thread e2 = new Thread(entidad2, "Sanador");
        e1.start();
        e2.start();
    }
}
