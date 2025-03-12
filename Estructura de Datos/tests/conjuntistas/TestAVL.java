package tests.conjuntistas;

import conjuntistas.ArbolAVL;
import lineales.dinamicas.Lista;

public class TestAVL {
    public static void main(String[] args) {
        ArbolAVL arbol = new ArbolAVL();
        arbol.insertar(45);
        System.out.println(arbol.toString());
        arbol.insertar(34);
        System.out.println(arbol.toString());
        arbol.insertar(65);
        System.out.println(arbol.toString());
        arbol.insertar(13);
        System.out.println(arbol.toString());
        arbol.insertar(55);
        System.out.println(arbol.toString());
        arbol.insertar(73);
        System.out.println(arbol.toString());
        arbol.insertar(96);
        System.out.println(arbol.toString());
        arbol.eliminar(45);
        System.out.println(arbol.toString());
    }
}
