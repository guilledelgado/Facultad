package TrabajoPractico6;

public class Consumidor implements Runnable{
    private Almacen almacen;
    public Consumidor(Almacen almacen){
        this.almacen = almacen;
    }

    public void run(){
        try{
            int i = 0;
            while(i<=100){
                almacen.retirar();
                Thread.sleep((long)Math.random()*1500);
                i++;
            }
        }catch (InterruptedException ie){
            ie.printStackTrace();
        }
    }
}
