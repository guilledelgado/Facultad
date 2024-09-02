import java.util.Scanner;
public class metodoSimpson {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n;
        Double x, y, h, resultado;
        double[][] tabla;
        System.out.println("Ingrese la cantidad de valores");
        n = sc.nextInt();
        if(0 == n%2){
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
            resultado = (h/3)*(tabla[0][1] + tabla[n][1] + 4*(sumatoriaDosEnDos(tabla,1))+2*sumatoriaDosEnDos(tabla, 2));//formula de simpson 1/3
            System.out.println(resultado);
        } else {
            System.out.println("No es posible aplicar Simpson 1/3");
        }
    }

    public static double sumatoriaDosEnDos(double[][] tabla, int n){
        double resultado = 0;
        for (int i = n; i < tabla.length-n; i = i + 2) {
            resultado = resultado + tabla[i][1];
        }
        return resultado;
    }
}
