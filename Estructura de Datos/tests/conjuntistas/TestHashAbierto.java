package tests.conjuntistas;
import conjuntistas.HashAbierto;
public class TestHashAbierto {
    public static void main(String[] args) {
        HashAbierto tabla = new HashAbierto(13);
        tabla.insertar(91);
        tabla.insertar(119);
        tabla.insertar(147);
        tabla.insertar(43);
        tabla.insertar(148);
        tabla.insertar(109);
        tabla.insertar(137);
        tabla.insertar(72);
        tabla.insertar(85);
        tabla.insertar(101);
        tabla.insertar(141);
        tabla.insertar(38);
        System.out.println(tabla.listar().toString()); 
    }
}
