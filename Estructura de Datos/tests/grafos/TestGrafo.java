package tests.grafos;

import grafos.*;

public class TestGrafo {
    public static void main(String[] args) {
        GrafoEtiquetado grafo = new GrafoEtiquetado();
        grafo.insertarVertice('C');
        grafo.insertarVertice('D');
        grafo.insertarVertice('B');
        grafo.insertarVertice('A');
        grafo.insertarArco('C', 'D', 1);
        grafo.insertarArco('D', 'A', 2);
        grafo.insertarArco('D', 'B', 3);
        grafo.insertarArco('A', 'B', 4);
        System.out.println(grafo.toString());
        grafo.eliminarArco('D', 'A');
        System.out.println(grafo.toString());
        grafo.eliminarVertice('D');
        System.out.println(grafo.toString());
        System.out.println("Existe un arco entre A y B? "+grafo.existeArco('A', 'B'));
        System.out.println("Existe un arco entre A y D? "+grafo.existeArco('A', 'D'));
        System.out.println("Existe el vertice D? "+grafo.existeVertice('D'));
        }
}
