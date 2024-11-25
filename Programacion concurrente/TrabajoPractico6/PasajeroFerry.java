package TrabajoPractico6;

public class PasajeroFerry implements Runnable {
    private Ferry ferry;
    private boolean esPersona;

    public PasajeroFerry(Ferry ferry, boolean esPersona) {
        this.ferry = ferry;
        this.esPersona = esPersona;
    }

    public void run() {
        try{
            if(esPersona) {
                ferry.subePersonaFerry();
                ferry.bajaPersonaFerry();
            } else {
                //Si no es persona, es un auto
                ferry.subeAutoFerry();
                ferry.bajaAutoFerry();
            }
        } catch (InterruptedException e){
            e.printStackTrace();
        }
    }
}
