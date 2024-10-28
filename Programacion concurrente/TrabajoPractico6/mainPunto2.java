package TrabajoPractico6;

public class mainPunto2 {
    public static void main(String[] args) {
        SalaEstudio sala = new SalaEstudio(10);
        for (int i = 0; i < 50; i++) {
            Estudiante est = new Estudiante(sala);
            Thread thread = new Thread(est, "Estudiante "+(i+1));
            thread.start();
        }
    }
}
