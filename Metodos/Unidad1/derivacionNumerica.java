package Unidad1;

import java.util.Scanner;
public class derivacionNumerica {
    public static void main(String[] args) {
        Scanner sc  = new Scanner(System.in);
        int opcion;
        double h, funcionA, funcionB, resultado;
        System.out.println("1. Derivacion hacia adelante \n 2. Derivacion central \n 3. Derivacion hacia atras");
        opcion = sc.nextInt();
        System.out.println("Ingrese h");
        h = sc.nextDouble();
        if (opcion == 1) {
            System.out.println("Ingrese f(x + h)");
            funcionA = sc.nextDouble();
            System.out.println("Ingrese f(x)");
            funcionB = sc.nextDouble();
            resultado = (funcionA - funcionB)/h;
        } else if (opcion == 2){
            System.out.println("Ingrese f(x + h)");
            funcionA = sc.nextDouble();
            System.out.println("Ingrese f(x - h)");
            funcionB = sc.nextDouble();
            resultado = (funcionA - funcionB)/(2*h);
        } else if (opcion == 3){
            System.out.println("Ingrese f(x)");
            funcionA = sc.nextDouble();
            System.out.println("Ingrese f(x - h)");
            funcionB = sc.nextDouble();
            resultado = (funcionA - funcionB)/h;
        }
    }
}
