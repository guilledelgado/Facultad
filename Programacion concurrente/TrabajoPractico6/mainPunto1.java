package TrabajoPractico6;

public class mainPunto1 {
    public static void main(String[] args) {
        GestorPuente gestor = new GestorPuente(10);
        String[] sentidos = new String[2];
        sentidos[0] = "norte";
        sentidos[1] = "sur";
        for (int i = 0; i < 25; i++) {
            String sentido = sentidos[(int) (Math.random() * sentidos.length)];
            Auto auto = new Auto(gestor, sentido, "auto "+(i+1));
            Thread thread = new Thread(auto);
            thread.start();
        }
    }
}
