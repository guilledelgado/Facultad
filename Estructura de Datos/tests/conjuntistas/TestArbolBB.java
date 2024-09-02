package tests.conjuntistas;

import conjuntistas.ArbolBB;

public class TestArbolBB {
    static String sOk = "Ok!", sErr = "ERROR";

    public static void main(String[] args) {
        ArbolBB arbol = new ArbolBB();
        System.out.println("Prueba de insertar 'f': " + (arbol.insertar('f') ? sOk : sErr));
        System.out.println("Prueba de insertar 'b': " + (arbol.insertar('b') ? sOk : sErr));
        System.out.println("Prueba de insertar 'g': " + (arbol.insertar('g') ? sOk : sErr));
        System.out.println("Prueba de insertar 'a': " + (arbol.insertar('a') ? sOk : sErr));
        System.out.println("Prueba de insertar 'd': " + (arbol.insertar('d') ? sOk : sErr));
        System.out.println("Prueba de insertar 'c': " + (arbol.insertar('c') ? sOk : sErr));
        System.out.println("Prueba de insertar 'e': " + (arbol.insertar('e') ? sOk : sErr));
        System.out.println("Prueba de insertar 'i': " + (arbol.insertar('i') ? sOk : sErr));
        System.out.println("Prueba de insertar 'h': " + (arbol.insertar('h') ? sOk : sErr));
        System.out.println(arbol.toString());
        System.out.println("Prueba pertenece");
        System.out.println("Pertenece 'i' al Arbol? "+arbol.pertenece('i'));
        System.out.println("Pertenece 'c' al Arbol? "+arbol.pertenece('c'));
        System.out.println("Pertenece 'a' al Arbol? "+arbol.pertenece('a'));
        System.out.println("Pertenece 'k' al Arbol? "+arbol.pertenece('k'));
        System.out.println("Prueba de eliminar");
        System.out.println("Prueba de eliminar el elemento 'a' (Caso 1)"+(arbol.eliminar('a') ? sOk : sErr));
        System.out.println(arbol.toString());
        System.out.println("Prueba de eliminar el elemento 'i' (Caso 2)"+(arbol.eliminar('i') ? sOk : sErr));
        System.out.println(arbol.toString());
        System.out.println("Prueba de eliminar el elemento 'f' (Caso 3)"+(arbol.eliminar('f') ? sOk : sErr));
        System.out.println(arbol.toString());
        arbol.eliminar('h');
        arbol.insertar('m');
        arbol.insertar('n');
        arbol.insertar('i');
        arbol.insertar('l');
        System.out.println("Nuevo arbol: ");
        System.out.println(arbol.toString());
        System.out.println("Prueba de eliminar el elemento 'i' (Caso 3)"+(arbol.eliminar('i') ? sOk : sErr));
        System.out.println(arbol.toString());
        System.out.println("Maximo elemento del arbol? (Se espera el 'n'): "+arbol.maximoElemento());
        System.out.println("Minimo elemento del arbol? (Se espera el 'b'): "+arbol.minimoElemento());
        System.out.println("Listar ordenado: "+arbol.listar().toString());
        System.out.println("Listar por rango [a, g]"+arbol.listarRango('a', 'g').toString());
        System.out.println("Listar por rango [k, z]"+arbol.listarRango('k', 'z').toString());
        System.out.println("Listar por rango [p, z]"+arbol.listarRango('p', 'z').toString());
        System.out.println(arbol.listarMayoresIguales('e').toString());
        System.out.println(arbol.listarMenores('m').toString());
        arbol.vaciar();
    }
}
