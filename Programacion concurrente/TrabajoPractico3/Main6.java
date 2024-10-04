package TrabajoPractico3;

import java.util.Random;

public class Main6 {
    public static void main(String[] args) {
        ArregloNum arreglo = new ArregloNum();
        SumadorArreglo suma1 = new SumadorArreglo(arreglo,0,10000);
        SumadorArreglo suma2 = new SumadorArreglo(arreglo,10001,20000);
        SumadorArreglo suma3 = new SumadorArreglo(arreglo,20001,30000);
        SumadorArreglo suma4 = new SumadorArreglo(arreglo,30001,40000);
        SumadorArreglo suma5 = new SumadorArreglo(arreglo,40001,50000);
        Thread s1 = new Thread(suma1);
        Thread s2 = new Thread(suma2);
        Thread s3 = new Thread(suma3);
        Thread s4 = new Thread(suma4);
        Thread s5 = new Thread(suma5);
        s1.start();
        s2.start();
        s3.start();
        s4.start();
        s5.start();
        try {
            s1.join();
            s2.join();
            s3.join();
            s4.join();
            s5.join();
            System.out.println("El resultado de la suma es: " +arreglo.getSumaTotal());

        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }
}
