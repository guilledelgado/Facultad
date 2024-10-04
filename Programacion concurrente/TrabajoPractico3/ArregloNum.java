package TrabajoPractico3;

import java.util.Random;

public class ArregloNum {
    private int[] num = new int[50000];
    private int sumaTotal;

    public ArregloNum() {
        for (int i = 0; i < num.length; i++) {
            num[i] = new Random().nextInt(1,10);
        }
        sumaTotal = 0;
    }

    public synchronized void sumar(int comienzo, int fin) {
        int sumaParcial = 0;
        for (int i = comienzo; i < fin; i++) {
            sumaParcial += num[i];
        }
        System.out.println("La suma de la pos "+comienzo+" a la pos "+fin+" es: "+sumaParcial);
        sumaTotal += sumaParcial;
    }

    public int getSumaTotal() { return sumaTotal; }
}
