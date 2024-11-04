package Unidad4;
import java.util.Scanner;
public class MetodoDeRiemann {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int m, n, areaCuadrado;
        double resultado = 0, resultadoParcial;
        System.out.println("Introduzca m:");
        m = sc.nextInt();
        System.out.println("Introduzca n:");
        n = sc.nextInt();
        areaCuadrado=m/n;
        int[][] matriz = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                System.out.println("Ingrese el valor de f("+(i+1)+","+(j+1)+")");
                matriz[i][j] = sc.nextInt();
            }
        }
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                resultadoParcial = areaCuadrado*matriz[i][j];
                resultado += resultadoParcial;
            }
        }
        System.out.println("El resultado es "+resultado);
    }
}
