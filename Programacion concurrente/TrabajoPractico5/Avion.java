package TrabajoPractico5;

public class Avion implements Runnable{
    private TorreControl torre;
    private boolean aterriza;

    public Avion(TorreControl torre, boolean aterriza) {
        this.torre = torre;
        this.aterriza = aterriza;
    }

    public void run() {
        try{
            if(aterriza){
                torre.listoAterrizaje();
                torre.usarPista();
                torre.sumarAterrizaje();
                Thread.sleep((long) ((Math.random()*5)*1000));
                torre.dejarPista();
            } else {
                torre.despegar();
                Thread.sleep((long) ((Math.random()*5)*1000));
                torre.dejarPista();
            }
        } catch (InterruptedException e){
            e.printStackTrace();
        }
    }
}
