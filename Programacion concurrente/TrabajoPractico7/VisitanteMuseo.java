package TrabajoPractico7;
import java.util.Random;
public class VisitanteMuseo implements Runnable{
    private GestorSala sala;
    private boolean esJubilado;
    public VisitanteMuseo(GestorSala sala, boolean esJubilado) {
        this.sala = sala;
        this.esJubilado = esJubilado;
    }
    public void run() {
        try{
            if(esJubilado){
                sala.entrarSalaJubilado();
            } else {
                sala.entrarSala();
            }
            Thread.sleep(new Random().nextInt(2,5) * 1000L);
            sala.salirSala();
        } catch(InterruptedException ie){
            ie.printStackTrace();
        }
    }
}
