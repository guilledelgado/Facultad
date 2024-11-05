package Unidad4;
import java.util.Scanner;
public class MetodoTrapecioIntegrales {
    public static void main(String[] args) {
        int m,n;
        double h, k, a,b,c,d, resultado;
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
        h =  (b - a) /m;
        k =  (d - c) /n;
        double[][] matriz = new double[m+1][n+1];
        double[] resultados = new double[n+1];
        for (int i = 0; i <= n  ; i++) {
            for (int j = 0; j <= m; j++) {
                System.out.println("Ingrese f("+(a+(j*h))+", "+(c+(i*k))+")");
                matriz[j][i]=sc.nextDouble();
            }
        }
        for (int i = 0; i <= n; i++) {
            resultados[i]=(h/2)*(matriz[0][i]+matriz[m][i]+2*(sumatoria(matriz,i,m)));
            System.out.println("EL RESULTADO DE I"+i+" es: "+resultados[i]);
        }
        resultado = resultados[0]+resultados[n];
        for (int i = 1; i < resultados.length-1; i++) {
            resultado += 2*resultados[i];
        }
        resultado = (k/2)*resultado;
        System.out.println("Resultado de la integral: "+resultado);
    }
    public static double sumatoria(double[][] matriz, int n, int m) {
        double suma = 0;
        for (int i = 1; i < m; i++) {
            suma+=matriz[i][n];
        }
        return suma;
    }
}
