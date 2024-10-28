package TrabajoPractico5;

public class Encargado implements Runnable {
    private ElMirador laburo;
    public Encargado(ElMirador laburo) {
        this.laburo = laburo;
    }
    public void run() {
        try{
            while(true){
                laburo.hacerPasarTobogan();
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
