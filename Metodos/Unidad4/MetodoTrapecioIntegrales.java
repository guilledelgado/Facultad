package Unidad4;
import java.util.Scanner;
public class MetodoTrapecioIntegrales {
    public static void main(String[] args) {
        int a,b,c,d,m,n;
        double h, k;
        Scanner sc = new Scanner(System.in);
        System.out.println("Ingrese el valor de a: ");
        a = sc.nextInt();
        System.out.println("Ingrese el valor de b: ");
        b = sc.nextInt();
        System.out.println("Ingrese el valor de c: ");
        c = sc.nextInt();
        System.out.println("Ingrese el valor de d: ");
        d = sc.nextInt();
        System.out.println("Ingrese el valor de m: ");
        m = sc.nextInt();
        System.out.println("Ingrese el valor de n: ");
        n = sc.nextInt();
        h = (double) (b - a) /m;
        k = (double) (d - c) /n;

    }
}
