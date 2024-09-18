package TrabajoPractico3;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Entidad implements Runnable{
    private Energia energia;
    private int gastoEnergia;

    public Entidad(int gastoEnergia, Energia energia) {
        this.gastoEnergia = gastoEnergia;
        this.energia = energia;
    }

    private synchronized void modificarEnergia(){
        if(energia.getEnergia() >= gastoEnergia){
            System.out.println(Thread.currentThread().getName()+" modificó "+gastoEnergia+" de energia");
            energia.modificarEnergia(gastoEnergia);
            System.out.println("La energia actual es de "+energia.getEnergia());
        } else {
            System.out.println("Sin energia");
        }
    }

    public void run() {
        for (int i = 0; i < 10; i++) {
            try{
                this.modificarEnergia();
                if(energia.getEnergia()<=0){
                    System.out.println("Sin energia");
                }
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                Logger.getLogger(Entidad.class.getName()).log(Level.SEVERE,null,e);
            }
        }
    }
}
