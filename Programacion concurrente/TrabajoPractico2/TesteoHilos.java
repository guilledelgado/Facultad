package TrabajoPractico2;

public class TesteoHilos {
    public static void main (String[] args){
        Thread miHilo= new MiEjecucion();
        miHilo.start();
        System.out.println("En el main");
        }
}
