package TrabajoPractico7;
import java.util.Random;
public class mainPunto1 {
    public static void main(String[] args) {
        GestorSala salaMuseo = new GestorSala(20);
        ControlTemperatura controlTemperatura = new ControlTemperatura(salaMuseo);
        Thread hiloTemp = new Thread(controlTemperatura);
        for (int i = 0; i < 100; i++) {
            boolean jubilado = new Random().nextBoolean();
            VisitanteMuseo visitante = new VisitanteMuseo(salaMuseo, jubilado);
            if (jubilado) {
                Thread thread = new Thread(visitante, "Jubilado "+i);
                thread.start();
            } else {
                Thread thread = new Thread(visitante, "Visitante "+i);
                thread.start();
            }
        }
    }
}
