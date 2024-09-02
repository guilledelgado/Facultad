package tests.lineales;

import lineales.dinamicas.Lista;

public class TestLista {
    static String sOk = "OK!", sErr = "ERROR";

    public static void main(String[] args) {
        Lista l1 = new Lista();

        System.out.println("\t\t\t\t\t Comienzo del Test Lista");
        System.out.println(l1.toString());
        System.out.print("Ingreso el elemento 1 en la posicion 1:\t\t" + (l1.insertar(1, 1)? sOk: sErr));
        System.out.println(l1.toString());
        System.out.print("Ingreso el elemento 2 en la posicion 2:\t\t" + (l1.insertar(2, 2)? sOk: sErr));
        System.out.println(l1.toString());
        System.out.print("Ingreso el elemento 3 en la posicion 3:\t\t" + (l1.insertar(3, 3)? sOk: sErr));
        System.out.println(l1.toString());
        System.out.print("Ingreso el elemento 4 en la posicion 4:\t\t" + (l1.insertar(4, 4)? sOk: sErr));
        System.out.println(l1.toString());
        System.out.print("Ingreso el elemento 9 en la posicion 1:\t\t" + (l1.insertar(9, 1)? sOk: sErr));
        System.out.println(l1.toString());
        System.out.print("Ingreso el elemento 7 en la posicion 3:\t\t" + (l1.insertar(7, 3)? sOk: sErr));
        System.out.println(l1.toString());
        System.out.print("Ingreso el elemento 10 en la posicion -1:\t\t" + (l1.insertar(10, -1)? sOk: sErr));
        System.out.println(l1.toString());
        System.out.print("Ingreso el elemento 11 en la posicion 8:\t\t" + (l1.insertar(8, 8)? sOk: sErr));
        System.out.println(l1.toString());
        System.out.println("");

        Lista lisMul = new Lista();
        lisMul = l1.obtenerMultiplos(4);
        System.out.println(lisMul.toString());
        System.out.println("");
        l1.insertar(9, 7);
        l1.insertar(9, 4);
        System.out.println(l1.toString());
        l1.eliminarApariciones(9);
        System.out.println(l1.toString());
        System.out.println("");

        System.out.print("Saco el elemento en la posicion 4:\t\t" + (l1.eliminar(4)? sOk: sErr));
        System.out.println(l1.toString());
        System.out.print("Saco el elemento en la posicion 1:\t\t" + (l1.eliminar(1)? sOk: sErr));
        System.out.println(l1.toString());
        System.out.print("Saco el elemento en la ultima posicion:\t\t" + (l1.eliminar(l1.longitud())? sOk: sErr));
        System.out.println(l1.toString());
        System.out.print("Saco el elemento en la posicion -1:\t\t" + (l1.eliminar(-1)? sOk: sErr));
        System.out.println(l1.toString());
        System.out.print("Saco el elemento en la posicion 4:\t\t" + (l1.eliminar(4)? sOk: sErr));
        System.out.println(l1.toString());
        System.out.println("");

        System.out.println("Recupero el elemento en la posicion 1:\t\t" + l1.recuperar(1));
        System.out.println("Recupero el elemento en la posicion 2:\t\t" + l1.recuperar(2));
        System.out.println("Recupero el elemento en la posicion 3:\t\t" + l1.recuperar(3));
        System.out.println("Recupero el elemento en la posicion 4:\t\t" + l1.recuperar(4));
        System.out.println("Recupero el elemento en la posicion -1:\t\t" + l1.recuperar(-1));
        System.out.println("");

        System.out.println("Localizo el elemento 1: \t\t" + l1.localizar(1));
        System.out.println("Localizo el elemento 7: \t\t" + l1.localizar(7));
        System.out.println("Localizo el elemento 3: \t\t" + l1.localizar(3));
        System.out.println("Localizo el elemento 20: \t\t" + l1.localizar(20));
        System.out.println("");

        System.out.println("Cuantos elementos tiene la lista?\t\t" + l1.longitud());
        System.out.println("Clono la lista");
        Lista clonLista = l1.clone();
        System.out.println("Lista original:\t\t" + l1.toString());
        System.out.println("Lista clon: \t\t" + clonLista.toString());
        System.out.println("Vacio la lista original");
        l1.vaciar();
        System.out.println("Lista original:\t\t" + l1.toString());
        System.out.println("Lista clon: \t\t" + clonLista.toString());
        System.out.print("Ingreso el elemento 9 en la posicion 3:\t\t" + (clonLista.insertar(9, 3)? sOk: sErr));
        System.out.println(clonLista.toString());

        System.out.println("La lista original es vacia?\t\t\t\t" + l1.esVacia());
        System.out.println("Intento eliminar un elemento en la lista original:\t" + (l1.eliminar(1)? sOk : sErr));
        System.out.println("Intento localizar un elemento en la lista original:\t" + l1.localizar(1));
        System.out.println("Intento recuperar un elemento en la lista original:\t" + l1.recuperar(1));
        System.out.println("Cual es la longitud de la lista original?\t\t"+l1.longitud());
    }
}
