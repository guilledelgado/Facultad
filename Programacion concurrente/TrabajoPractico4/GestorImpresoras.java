package TrabajoPractico4;

import java.util.concurrent.Semaphore;

public class GestorImpresoras {
    private Semaphore[] impresoras;
    String ANSI_RESET = "\u001B[0m";
    String ANSI_RED = "\u001B[31m";
    String ANSI_GREEN = "\u001B[32m";


    public GestorImpresoras(int cantImpresoras) {
        impresoras = new Semaphore[cantImpresoras];
        for (int i = 0; i < cantImpresoras; i++) {
            impresoras[i] = new Semaphore(1);
        }
    }

    public void intentarImprimir(){
        try{
            boolean ingreso = false;
            while(!ingreso){
                int i = 0;
                while(!ingreso && i < impresoras.length){
                    if(impresoras[i].tryAcquire()){
                        ingreso = true;
                        this.imprimir(i);
                    }
                    i++;
                }
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void imprimir(int i) throws InterruptedException {
        System.out.println(ANSI_GREEN + Thread.currentThread().getName()+ " utiliza la impresora "+i + ANSI_RESET);
        Thread.sleep(3000);
        System.out.println(ANSI_RED+Thread.currentThread().getName()+ " deja la impresora "+i+ANSI_RESET);
        impresoras[i].release();
    }
}
