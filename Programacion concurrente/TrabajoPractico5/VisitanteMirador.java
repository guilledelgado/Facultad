package TrabajoPractico5;

import java.util.Random;

public class VisitanteMirador implements Runnable{
    ElMirador mirador;
    public VisitanteMirador(ElMirador mirador) {
        this.mirador = mirador;
    }
    public void run() {
        try{
            mirador.subirEscalera();
            Thread.sleep(5000);
            mirador.subirTobogan();
            mirador.dejarEscalera();
            System.out.println(Thread.currentThread().getName() + " está usando el tobogan");
            Thread.sleep((new Random().nextInt(1,5))*1000);
            System.out.println(Thread.currentThread().getName() + " dejo de usar el tobogan");
            System.out.println();
            mirador.terminaElTobogan();
        }catch (InterruptedException e){
            System.out.println(e);
        }
    }
}
