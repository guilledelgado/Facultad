package Unidad5;

import java.util.Scanner;

public class MetodoEuler {
    public static void main(String[] args) {
        double a, b, h, x0, y0;
        int n;
        Scanner sc = new Scanner(System.in);
        System.out.println("Ingrese el valor de a: ");
        a = sc.nextDouble();
        System.out.println("Ingrese el valor de b: ");
        b = sc.nextDouble();
        System.out.println("Ingrese el valor de h: ");
        h = sc.nextDouble();
        System.out.println("Ingrese el valor de x: ");
        x0 = sc.nextDouble();
        System.out.println("Ingrese el valor de y: ");
        y0 = sc.nextDouble();
        n = (int) ((b - a) / h);
        double[][] matriz = new double[n+1][2];
        matriz[0][0] = x0;
        matriz[0][1] = y0;
        for (int i = 1; i < matriz.length; i++) {
            x0 = x0 + h;
            matriz[i][0] = x0;
            matriz[i][1] = ecuacion(matriz[i-1][0],matriz[i-1][1],h);
        }
        for (int i = 0; i < matriz.length; i++) {
            System.out.println("Para x"+i+" = "+matriz[i][0]+", y"+i+" = "+matriz[i][1]);
        }
    }

    public static double ecuacion(double x,double y,double h){
        return y+h*f(x,y);
    }

    public static double f(double x,double y){
        //Cambio la funcion manualmente
        return x+(1/(5*y));
    }
}
