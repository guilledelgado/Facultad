package tests.conjuntistas;

import conjuntistas.HeapMin;

public class TestArbolHeap {
    public static void main(String[] args) {
        HeapMin heap = new HeapMin();
        heap.insertar(1);
        heap.insertar(2);
        heap.insertar(3);
        heap.insertar(17);
        heap.insertar(19);  
        heap.insertar(36);
        heap.insertar(7);
        heap.insertar(25);
        heap.insertar(100);
        System.out.println(heap.toString());
        heap.insertar(1);
        System.out.println(heap.toString());
        heap.eliminarCima();
        System.out.println(heap.toString());
        System.out.println(heap.recuperarCima());
    }
}
