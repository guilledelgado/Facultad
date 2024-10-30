package TrabajoPractico7;
import java.util.Random;
public class ControlTemperatura implements Runnable{
    private GestorSala sala;

    public ControlTemperatura(GestorSala sala) {
        this.sala = sala;
    }

    public void run() {
        try {
            while (true) {
                int temperatura = new Random().nextInt(19,41);
                sala.notificarTemperatura(temperatura);
                Thread.sleep(10000);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
