package TrabajoPractico6;

public class Productor implements Runnable {
    private Almacen almacen;
    public Productor(Almacen almacen) {
        this.almacen = almacen;
    }

    public void run() {
        try{
            int i = 0;
            while(i<=100){
                almacen.reponer();
                Thread.sleep((long) Math.random()*1500);
                i++;
            }
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }
}
