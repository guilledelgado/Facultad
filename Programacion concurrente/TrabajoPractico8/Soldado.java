package TrabajoPractico8;
import java.util.Random;
public class Soldado implements Runnable{
    private int queAlmuerzoQuiere;
    private int quePostreQuiere;
    private boolean quiereGaseosa;
    private boolean quierePostre;
    private Comedor comedor;
    public Soldado(Comedor comedero, int cantMostradores, int cantPostres){
        this.queAlmuerzoQuiere = (int) (Math.random()*cantMostradores);
        this.quierePostre = new Random().nextBoolean();
        this.quePostreQuiere = (int) (Math.random()*cantPostres);
        this.quiereGaseosa = new Random().nextBoolean();
        this.comedor = comedero;
    }
    public void run(){
        try{
            comedor.obtenerBandeja(queAlmuerzoQuiere);
            if(quiereGaseosa){
                comedor.usarDestapador();
                Thread.sleep(new Random().nextInt(1,5) * 1000);
                comedor.dejarDestapador();
            }
            if(quierePostre){
                comedor.obtenerPostre(quePostreQuiere);
                Thread.sleep(new Random().nextInt(1,5) * 1000);
                comedor.dejarFilaPostre(quePostreQuiere);
            }
            Thread.sleep(new Random().nextInt(1,7) * 1000);
            comedor.dejarBandeja(queAlmuerzoQuiere);
        }catch(InterruptedException e){
            e.printStackTrace();
        }
    }
}
