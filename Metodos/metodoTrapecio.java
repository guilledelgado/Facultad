import java.util.Scanner;

public class metodoTrapecio {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n;
        Double x, y, h, resultado;
        double[][] tabla;
        System.out.println("Ingresar la cantidad de valores");
        n = sc.nextInt();
        tabla = new double[n+1][2];
        for (int i = 0; i < tabla.length; i++) {
            System.out.println("Ingrese x");
            x = sc.nextDouble();
            tabla[i][0] = x;
            System.out.println("Ingrese f(x)");
            y = sc.nextDouble();
            tabla[i][1] = y;
        }
        h = (tabla[n][0] - tabla[0][0])/n;//(b-a)/n
        resultado = (h/2)*(tabla[0][1] + tabla[n][1] + 2*(sumatoria(tabla)));//formula del trapecio
        System.out.println(resultado);
    }

    public static double sumatoria(double[][] tabla){
        double resultado = 0;
        for (int i = 1; i < tabla.length-1; i++) {
            resultado = resultado + tabla[i][1];
        }
        return resultado;
    }
}
