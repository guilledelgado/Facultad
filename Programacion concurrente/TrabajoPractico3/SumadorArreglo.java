package TrabajoPractico3;

public class SumadorArreglo implements Runnable{
    private int[] arreglo;
    int comienzo;
    int fin;
    int resultado;

    public SumadorArreglo(int[] arreglo, int comienzo, int fin) {
        this.arreglo = arreglo;
        this.comienzo = comienzo;
        this.fin = fin;
        resultado = 0;
    }

    private synchronized void sumar() {
        int i = comienzo;
        while(i < fin){
            resultado = resultado + arreglo[i];
            i++;
        }
    }
    public void run() {
        try{
            this.sumar();
            System.out.println("El resultado parcial ("+comienzo+", "+fin+") es de: " + resultado);
        }catch(Exception e){
            System.out.println(e);
        }
    }

    public int getResultado() {
        return resultado;
    }
}
