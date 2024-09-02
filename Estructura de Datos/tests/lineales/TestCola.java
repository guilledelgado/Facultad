package tests.lineales;

//import lineales.estaticas.Cola;
import lineales.dinamicas.Cola;

public class TestCola {
    static String sOk = "OK!", sErr = "ERROR";
    public static void main(String[] args) {
        Cola c1 = new Cola();
        Cola cClon = new Cola();
        System.out.println("\t\t\t\t\t\t COMIENZO DEL TESTEO DE COLA");

        System.out.println("Imprimo la cola vacia: "+c1.toString());
        System.out.print("Apilo 'a' debe retornar [a]:" + (c1.poner('a')? sOk : sErr));
        System.out.println("\t\t\t"+c1.toString());
        System.out.print("Apilo 'b' debe retornar [a b]:" + (c1.poner('b')? sOk : sErr));
        System.out.println("\t\t"+c1.toString());
        System.out.print("Apilo 'c' debe retornar [a b c]:" + (c1.poner('c')? sOk : sErr));
        System.out.println("\t\t"+c1.toString());
        System.out.print("Apilo 'd' debe retornar [a b c d]:" + (c1.poner('d')? sOk : sErr));
        System.out.println("\t\t"+c1.toString());
        System.out.print("Apilo 'e' debe retornar [a b c d e]:" + (c1.poner('e')? sOk : sErr));
        System.out.println("\t\t"+c1.toString());
        System.out.print("Apilo 'f' debe retornar [a b c d e f]:" + (c1.poner('f')? sOk : sErr));
        System.out.println("\t"+c1.toString());
        System.out.print("Apilo 'g' debe retornar [a b c d e f g]:" + (c1.poner('g')? sOk : sErr));
        System.out.println("\t"+c1.toString());
        System.out.print("Apilo 'h' debe retornar [a b c d e f g h]:" + (c1.poner('h')? sOk : sErr));
        System.out.println("\t"+c1.toString());
        System.out.print("Apilo 'i' debe retornar [a b c d e f g h i]:" + (c1.poner('i')? sOk : sErr));
        System.out.println("\t"+c1.toString());
        System.out.println("Apilo 'j' debe retornar false en estatica, true en dinamica:" + c1.poner('j'));
        System.out.println("Obtengo el elemento frente (a): "+ c1.obtenerFrente());
        
        System.out.println("");

        System.out.print("Desapilo, debe retornar [b c d e f g h i]:"+(c1.sacar()? sOk : sErr));
        System.out.println("\t"+c1.toString());
        System.out.print("Desapilo, debe retornar [c d e f g h i]:"+(c1.sacar()? sOk : sErr));
        System.out.println("\t"+c1.toString());
        System.out.print("Apilo 'a' debe retornar [c d e f g h i a]:" + (c1.poner('a')? sOk : sErr));
        System.out.println("\t" + c1.toString());
        System.out.println("Obtengo el nuevo elemento frente (c): "+c1.obtenerFrente());

        System.out.println("");
        System.out.println("Clono el elemento original");
        cClon = c1.clone();
        System.out.println("Cola original:\t\t\t" + c1.toString());
        System.out.println("Cola copia:\t\t\t" + cClon.toString());

        System.out.println("");
        System.out.println("La cola original es vacia? Debe retornar No:\t" + (c1.esVacia()? "Si" : "No"));
        System.out.println("Vacio la cola original");
        c1.vaciar();
        System.out.println("La cola original es vacia? Debe retornar Si:\t" +(c1.esVacia()? "Si" : "No"));
        System.out.println("La cola copia es vacia? Debe retornar No:\t"+ (cClon.esVacia()? "Si" : "No"));

        System.out.println("Le quito un elemento a la cola original, debe retornar falso:\t"+c1.sacar());

    }
}
