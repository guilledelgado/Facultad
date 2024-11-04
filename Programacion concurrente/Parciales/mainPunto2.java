package Parciales;

public class mainPunto2 {
    public static void main(String[] args) {
        FormacionAgua formacion = new FormacionAgua(10);
        for (int i = 0; i < 10; i++) {
            Oxigeno oxigeno = new Oxigeno(formacion);
            Thread oThread = new Thread(oxigeno, "Oxigeno "+(i+1));
            oThread.start();
        }
        for (int i = 0; i < 21; i++) {
            Hidrogeno hidrogeno = new Hidrogeno(formacion);
            Thread hThread = new Thread(hidrogeno, "Hidrogeno "+(i+1));
            hThread.start();
        }

    }
}
