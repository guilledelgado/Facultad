package TrabajoPractico5;

public class Perro implements Runnable{
    private Comedero comedero;
    private boolean comio;

    public Perro(Comedero comedero) {
        this.comedero = comedero;
        comio = false;
    }

    public void run(){
        try{
            while(!comio){
                comio = comedero.comerPerro();
                if(comio){
                    Thread.sleep((int)Math.floor(Math.random()*5+1)*1000);
                    comedero.terminaComerPerro();
                }
            }

        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
