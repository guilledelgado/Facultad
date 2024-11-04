package Parciales;

public class Transporte implements Runnable{
    private Almacen almacen;
    public Transporte(Almacen almacen){
        this.almacen = almacen;
    }
    public void run(){
        try{
            while(true){
                almacen.llevarseCajas();
                Thread.sleep(((int)(Math.random()*8)+1)*1000);
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
