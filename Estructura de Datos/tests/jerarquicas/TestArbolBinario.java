package tests.jerarquicas;

import jerarquicas.ArbolBin;
import lineales.dinamicas.Lista;

public class TestArbolBinario {

    public static void main(String[] args) {
        ArbolBin aBin1 = new ArbolBin();
        Lista listado = new Lista();

        System.out.println("Inserto 'normal'");
        aBin1.insertar(1, 1, 'd');
        System.out.println(aBin1.toString());
        aBin1.insertar(2, 1, 'I');
        System.out.println(aBin1.toString());
        aBin1.insertar(3, 1, 'D');
        System.out.println(aBin1.toString());
        aBin1.insertar(4, 2, 'I');
        System.out.println(aBin1.toString());
        aBin1.insertar(5, 2, 'D');
        System.out.println(aBin1.toString());
        aBin1.insertar(6, 3, 'I');
        System.out.println(aBin1.toString());
        aBin1.insertar(7, 3, 'D');
        System.out.println(aBin1.toString());
        aBin1.insertar(8, 6, 'I');
        System.out.println(aBin1.toString());
        System.out.println("Inserto por posicion");
        aBin1.insertarPorPosicion(9, 3, 'I');
        System.out.println(aBin1.toString());
        aBin1.insertarPorPosicion(10, 3, 'D');
        System.out.println(aBin1.toString());

        listado = aBin1.listarPreorden();
        System.out.println("Lista Preorden:\t\t" + listado.toString());
        listado = aBin1.listarPosorden();
        System.out.println("Lista Posorden:\t\t" + listado.toString());
        listado = aBin1.listarInorden();
        System.out.println("Lista Inorden:\t\t" + listado.toString());
        Lista listaNiveles = aBin1.listarPorNiveles();
        System.out.println("Lista por niveles:\t" + listaNiveles.toString());
        System.out.println("");

        ArbolBin arbolClon = new ArbolBin();
        arbolClon = aBin1.clone();
        System.out.println("Arbol clon:");
        System.out.println(arbolClon.toString());
        System.out.println("");

        System.out.println("La altura del Arbol Binario es: " + aBin1.altura());
        System.out.println("En que nivel se encuentra el elemento 6? En el nivel " + aBin1.nivel(6));
        System.out.println("En que nivel se encuentra el elemento 12? " + aBin1.nivel(12));
        System.out.println("");
        // Ejercicios del apunte
        listado = aBin1.frontera();
        System.out.println("Frontera del arbol: " + listado.toString());
        listado = aBin1.obtenerAncestros(8);
        System.out.println("Ancestros del elemento 8: " + listado.toString());
        listado = aBin1.obtenerAncestros(12);
        System.out.println("Ancestros del elemento 12: " + listado.toString());
        listado = aBin1.obtenerAncestros(1);
        System.out.println("Ancestros del elemento 1: " + listado.toString());
        listado = aBin1.obtenerDescendientes(2);
        System.out.println("Descendientes de 2: " + listado.toString());
        listado = aBin1.obtenerDescendientes(12);
        System.out.println("Descendientes de 12: " + listado.toString());
        listado = aBin1.obtenerDescendientes(5);
        System.out.println("Descendientes de 5: "+listado.toString());
        System.out.println("");
        //Simulacro de Parcial
        arbolClon = aBin1.clonarInvertido();
        System.out.println(aBin1.toString());
        System.out.println(arbolClon.toString());
        System.out.println("");
        Lista lis = new Lista();
        lis.insertar(1, 1);
        lis.insertar(2,2);
        lis.insertar(4, 3);
        System.out.println(lis.toString());
        System.out.println(aBin1.verificarPatron(lis));
        System.out.println("");

        System.out.println("El arbol binario está vacio? " + aBin1.esVacio());
        System.out.println("Vacio el arbol binario");
        aBin1.vaciar();
        System.out.println(aBin1.toString());

    }
}
