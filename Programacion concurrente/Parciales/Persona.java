package Parciales;

public class Persona implements Runnable {
    private ParqueVecinal parqueVecinal;
    private boolean esResidente;
    public Persona(ParqueVecinal parqueVecinal, boolean esResidente) {
        this.parqueVecinal = parqueVecinal;
        this.esResidente = esResidente;
    }
    public void run() {
        try {
            if(esResidente) {
                parqueVecinal.entraResidente();
            } else {
                parqueVecinal.entraNoResidente();
            }
            Thread.sleep((int)(((Math.random()*6)+1)*1000));
            parqueVecinal.salePersona();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
