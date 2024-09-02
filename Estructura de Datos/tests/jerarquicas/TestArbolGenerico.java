package tests.jerarquicas;

import jerarquicas.ArbolGen;

public class TestArbolGenerico {
    public static void main(String[] args) {
        ArbolGen arbol = new ArbolGen();
        arbol.insertar('A', 'A');
        System.out.println(arbol.toString());
        arbol.insertar('B', 'A');
        System.out.println(arbol.toString());
        arbol.insertar('C', 'A');
        System.out.println(arbol.toString());
        arbol.insertar('D', 'A');
        System.out.println(arbol.toString());
        arbol.insertar('E', 'B');
        System.out.println(arbol.toString());
        arbol.insertar('F', 'B');
        System.out.println(arbol.toString());
        arbol.insertar('G', 'B');
        System.out.println(arbol.toString());
        arbol.insertar('H', 'D');
        System.out.println(arbol.toString());
        arbol.insertar('I', 'G');
        System.out.println(arbol.toString());
    
        arbol.insertarPorPosicion('J', 7);
        System.out.println(arbol.toString());
        arbol.insertarPorPosicion('K', 7);
        System.out.println(arbol.toString());
        System.out.println(arbol.pertenece('A'));
        System.out.println(arbol.pertenece('J'));
        System.out.println(arbol.padre('H'));
        System.out.println(arbol.padre('F'));
        System.out.println(arbol.padre('K'));
        System.out.println(arbol.padre('B'));
        System.out.println(arbol.padre('A'));
        System.out.println(arbol.altura());
        System.out.println(arbol.nivel('A'));
        System.out.println(arbol.nivel('C'));
        System.out.println(arbol.nivel('G'));
        System.out.println(arbol.nivel('Z'));
        System.out.println(arbol.ancestros('J').toString());
        System.out.println(arbol.ancestros('G').toString());
        System.out.println(arbol.ancestros('D').toString());
        System.out.println(arbol.ancestros('A').toString());
        System.out.println(arbol.ancestros('Z').toString());
        ArbolGen arbolClon = arbol.clone();
        System.out.println(arbolClon.toString());
        System.out.println(arbol.listarPreorden().toString());
        System.out.println(arbol.listarInorden().toString());
        System.out.println(arbol.listarPosorden().toString());
        System.out.println(arbol.listarPorNiveles().toString());
        System.out.println(arbol.listarEntreNiveles(1, 2).toString());
        arbol.eliminar('C');
        System.out.println(arbol.toString());
        arbol.eliminar('H');
        System.out.println(arbol.toString());
        System.out.println(arbol.esSobrino('E','I'));
        System.out.println(arbol.esSobrino('A', 'B'));
        System.out.println(arbol.esSobrino('B', 'H'));
        System.out.println(arbol.listarHastaNivel(1).toString());
    }

}
