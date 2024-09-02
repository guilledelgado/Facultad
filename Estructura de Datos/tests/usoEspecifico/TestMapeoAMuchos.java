package tests.usoEspecifico;

import usoEspecifico.MapeoAMuchos;

public class TestMapeoAMuchos {
    public static void main(String[] args) {
        MapeoAMuchos map = new MapeoAMuchos(10);
        map.asociar("FAI-3232", "poo");
        System.out.println(map.toString());
        map.asociar("FAI-2678", "edat");
        System.out.println(map.toString());
        map.asociar("FAI-2321", "poo");
        System.out.println(map.toString());
        map.asociar("FAI-1234", "edat");
        System.out.println(map.toString());
        map.asociar("FAI-1456", "tcc1");
        System.out.println(map.toString());
        map.asociar("FAI-3232", "ing1");
        System.out.println(map.toString());
        map.asociar("FAI-3232", "edat");
        map.asociar("FAI-2678", "tcc1");
        map.asociar("FAI-2321", "tcc1");
        map.asociar("FAI-1234", "poo");
        map.asociar("FAI-1234", "calc");
        map.asociar("FAI-1456", "edat");
        map.asociar("FAI-1456", "ing1");
        System.out.println(map.toString());
        map.desasociar("FAI-3232", "poo");
        System.out.println(map.toString());
        map.desasociar("FAI-2678", "tcc1");
        System.out.println(map.toString());
        map.desasociar("FAI-2678", "edat");
        System.out.println(map.toString());
        System.out.println(map.obtenerValor("FAI-1234").toString());
        System.out.println(map.obtenerConjuntoDominio().toString());
        System.out.println(map.obtenerConjuntoRango().toString());
    }
}
