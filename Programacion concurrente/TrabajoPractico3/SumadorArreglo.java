package TrabajoPractico3;

public class SumadorArreglo implements Runnable{
    private ArregloNum arreglo;
    private int comienzo;
    private int fin;

    public SumadorArreglo(ArregloNum arreglo, int comienzo, int fin) {
        this.arreglo = arreglo;
        this.comienzo = comienzo;
        this.fin = fin;
    }

    public void run() {
        try{
            arreglo.sumar(comienzo, fin);
        }catch(Exception e){
            System.out.println(e);
        }
    }
}
