package TrabajoPractico7;

public class mainPunto2 {
    public static void main(String[] args) {
        proyectoSoftware proyecto = new proyectoSoftware();
        for (int i = 0; i < 10; i++) {
            Programador programador = new Programador(proyecto);
            Thread thread = new Thread(programador, "Programador" +(i+1));
            thread.start();
        }
    }
}
