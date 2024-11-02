package Parciales;

public class Embotellador implements Runnable{
    private Almacen almacen;
    private char tipo;
    public Embotellador(Almacen almacen, char tipo) {
        this.almacen = almacen;
        this.tipo = tipo;
    }
    public void run() {
        try{
            while(true){
                if(tipo=='V'){
                    almacen.ponerBotellaVino();
                } else if(tipo=='A'){
                    almacen.ponerBotellaAguaSaborizada();
                }
                Thread.sleep((((int)(Math.random()*8))+1)*1000);
            }
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }
    }
}
