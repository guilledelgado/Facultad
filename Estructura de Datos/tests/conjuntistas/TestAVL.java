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
        arbol.insertar(40);
        System.out.println(arbol.toString());
        arbol.insertar(1);
        System.out.println(arbol.toString());
        arbol.insertar(20);
        System.out.println(arbol.toString());
        arbol.insertar(36);
        System.out.println(arbol.toString());
        arbol.insertar(42);
        System.out.println(arbol.toString());
        arbol.insertar(50);
        System.out.println(arbol.toString());
        arbol.insertar(58);
        System.out.println(arbol.toString());
        arbol.insertar(67);
        System.out.println(arbol.toString());
        arbol.insertar(66);
        System.out.println(arbol.toString());
        arbol.eliminar(65);
        System.out.println(arbol.toString());
        arbol.eliminar(34);
        System.out.println(arbol.toString());
        arbol.eliminar(36);
        System.out.println(arbol.toString());
        arbol.eliminar(40);
        System.out.println(arbol.toString());
    }
}
