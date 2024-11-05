package Unidad4;

import java.util.Scanner;

public class MetodoSimpsonIntegralesDobles {
    public static void main(String[] args) {
        int m, n;
        double h, k, a, b, c, d, resultado;
        Scanner sc = new Scanner(System.in);
        System.out.println("Ingrese el valor de a: ");
        a = sc.nextDouble();
        System.out.println("Ingrese el valor de b: ");
        b = sc.nextDouble();
        System.out.println("Ingrese el valor de c: ");
        c = sc.nextDouble();
        System.out.println("Ingrese el valor de d: ");
        d = sc.nextDouble();
        System.out.println("Ingrese el valor de m: ");
        m = sc.nextInt();
        System.out.println("Ingrese el valor de n: ");
        n = sc.nextInt();
        h = (b - a) / m;
        k = (d - c) / n;
        double[][] matriz = new double[m + 1][n + 1];
        double[] resultados = new double[n + 1];
        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= m; j++) {
                System.out.println("Ingrese f(" + (a + (j * h)) + ", " + (c + (i * k)) + ")");
                matriz[j][i] = sc.nextDouble();
            }
        }
        for (int i = 0; i <= n; i++) {
            resultados[i] = (h / 3) * (matriz[0][i] + matriz[m][i] + 4 * (sumatoriaDosenDos(matriz, i, m, 1) + 2*(sumatoriaDosenDos(matriz, i, m, 2))));
            System.out.println("EL RESULTADO DE I" + i + " es: " + resultados[i]);
        }
        resultado = resultados[0] + resultados[n];
        for (int i = 1; i < resultados.length - 1; i++) {
            if(i%2==0){
                resultado += resultados[i]*2;
            } else {
                resultado += resultados[i]*4;
            }
        }
        resultado = (k / 3) * resultado;
        System.out.println("Resultado de la integral: " + resultado);
    }

    public static double sumatoriaDosenDos(double[][] matriz, int n, int m, int comienzo) {
        double suma = 0;
        for (int i = comienzo; i < m; i += 2) {
            suma += matriz[i][n];
        }
        return suma;
    }
}

