package tests.conjuntistas;

import conjuntistas.ArbolAVL;
import lineales.dinamicas.Lista;

public class TestAVL {
    public static void main(String[] args) {
        ArbolAVL arbol = new ArbolAVL();
        arbol.insertar(10);
        System.out.println(arbol.toString());
        arbol.insertar(5);
        System.out.println(arbol.toString());
        arbol.insertar(1);
        System.out.println(arbol.toString());
        arbol.insertar(15);
        System.out.println(arbol.toString());
        arbol.insertar(20);
        System.out.println(arbol.toString());
        arbol.insertar(7);
        System.out.println(arbol.toString());
        arbol.insertar(-1);
        System.out.println(arbol.toString());
        arbol.insertar(0);
        System.out.println(arbol.toString());
        //eliminar simple derecha
        arbol.eliminar(7);
        System.out.println(arbol.toString());
        //eliminar doble izquierda derecha
        arbol.eliminar(20);
        System.out.println(arbol.toString());
        //eliminar
        arbol.eliminar(0);
        System.out.println(arbol.toString());
        arbol.eliminar(-1);
        System.out.println(arbol.toString());
        //eliminar rotacion simple izquierda
        arbol.eliminar(1);
        System.out.println(arbol.toString());
        arbol.insertar(13);
        System.out.println(arbol.toString());
        arbol.insertar(22);
        System.out.println(arbol.toString());
        //insertar con rotacion derecha izquierda
        arbol.insertar(11);
        System.out.println(arbol.toString());
        System.out.println("minimo elemento: "+arbol.minimoElemento());
        System.out.println("maximo elemento: "+arbol.maximoElemento());
        System.out.println(arbol.listar().toString());
        System.out.println(arbol.listarRango(3,14 ).toString());
    }
}
