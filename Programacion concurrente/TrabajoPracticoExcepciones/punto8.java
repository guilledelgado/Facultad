package TrabajoPracticoExcepciones;

import java.util.Scanner;

public class punto8 {
    public static boolean verificaEdad(int edad) throws PruebaExcep {
        if (edad < 18) {
            throw new PruebaExcep("ES MENOR");
        }
        return edad >= 18;
    }

    public static void jugarRuleta(int num) throws PruebaExcep{
        int numeroRuleta = (int)(Math.random() / 10);
        if(numeroRuleta!=num){
            throw new PruebaExcep("NO GANASTE");
        } else {
            System.out.println("GANASTE!!!!!!!!!!!!!!!");
        }
    }

    public static void cargarArreglo() throws PruebaExcep{
        Scanner sc = new Scanner(System.in);
        int[] arreglo = new int[5];
        int aux;
        for (int i = 0; i < arreglo.length; i++) {
            System.out.println("Ingrese numero");
            arreglo[i] = aux=sc.nextInt();
        }
        try {
            for (int i = 0; i < 7; i++) {
                System.out.println(arreglo[i]);
            }
        } catch (ArrayIndexOutOfBoundsException aioobe) {
            throw new PruebaExcep("Te pasaste");
        } catch (Exception e){
            
        }
    }
}
