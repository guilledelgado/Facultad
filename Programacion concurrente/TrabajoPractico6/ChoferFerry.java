package TrabajoPractico6;

public class ChoferFerry implements Runnable {
    private Ferry ferry;

    public ChoferFerry(Ferry f) {
        ferry = f;
    }

    public void run() {
        try{
            while(true){
                ferry.esperaFerry();
                Thread.sleep(6000);
                ferry.llegaFerry();
                Thread.sleep(6000);
                ferry.ferryEnPuerto();
            }
        } catch (InterruptedException e){
            e.printStackTrace();
        }
    }
}
