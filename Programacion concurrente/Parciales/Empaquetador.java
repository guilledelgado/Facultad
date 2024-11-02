package Parciales;

public class Empaquetador implements Runnable{
    private Almacen almacen;
    public Empaquetador(Almacen almacen){
        this.almacen = almacen;
    }
    public void run(){
        try{
            while(true){
                almacen.guardarCaja();
                Thread.sleep((((int)(Math.random()*8))+1)*1000);
                almacen.reponerCaja();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
