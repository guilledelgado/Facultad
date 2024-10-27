package TrabajoPractico5;

public class Gato implements Runnable {
    private Comedero comedero;
    private boolean comio;

    public Gato(Comedero comedero) {
        this.comedero = comedero;
        comio = false;
    }

    public void run() {
        try{
            while(!comio){
                comio = comedero.comerGato();
                if(comio){
                    Thread.sleep((int)Math.floor(Math.random()*5+1)*1000);
                    comedero.terminaComerGato();
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
