/****************** Autores*****************
        - Guillermo Delgado, Legajo FAI - 3273
        - Francisco Gutierrez, Legajo FAI - 4047
*/
package tests.lineales;

//import lineales.estaticas.Pila;
import lineales.dinamicas.Pila;
import tda.Alumno;

public class TestPila {
    static String sOk = "Ok!", sErr = "ERROR";

    public static void main(String[] args) {

        // El test se realizara con el TDA Alumno
        Pila p1 = new Pila();
        Alumno alum1 = new Alumno(1, "Santiago", "Chandia", "DNI", 11111111, "Buenos Aires", 1, "Neuquen", 29911111,
                "sChandia", "santi123");
        Alumno alum2 = new Alumno(2, "Guillermo", "Delgado", "DNI", 22222222, "Buenos Aires", 2, "Neuquen", 29922222,
                "gDelgado", "chicho123");
        Alumno alum3 = new Alumno(3, "Agustin", "Barbaro", "DNI", 33333333, "Buenos Aires", 3, "Neuquen", 29933333,
                "aBarbaro", "agus123");
        Alumno alum4 = new Alumno(4, "Valentino", "Torres", "DNI", 44444444, "Buenos Aires", 4, "Neuquen", 29944444,
                "vTorres", "tino123");
        Alumno alum5 = new Alumno(5, "Laureano", "Arriagada", "DNI", 55555555, "Buenos Aires", 5, "Neuquen", 29955555,
                "lArriagada", "lau123");
        Alumno alum6 = new Alumno(6, "Paloma", "Arriagada", "DNI", 66666666, "Buenos Aires", 6, "Neuquen", 29966666,
                "pArriagada", "palo123");
        Alumno alum7 = new Alumno(7, "Victoria", "Delgado", "DNI", 77777777, "Buenos Aires", 7, "Neuquen", 29977777,
                "vDelgado", "vicky123");
        Alumno alum8 = new Alumno(8, "Morena", "Chandia", "DNI", 88888888, "Buenos Aires", 8, "Neuquen", 29988888,
                "mChandia", "more123");
        Alumno alum9 = new Alumno(9, "Sol", "Bruschi", "DNI", 99999999, "Buenos Aires", 9, "Neuquen", 29999999,
                "sBruschi", "sol123");
        Alumno alum10 = new Alumno(10, "Franco", "Marquez", "DNI", 10101010, "Buenos Aires", 10, "Neuquen", 299101010,
                "fMarquez", "franco123");
        Alumno alum11 = new Alumno(11, "Agustin", "Arenas", "DNI", 11011011, "Buenos Aires", 11, "Neuquen", 299111011,
                "aArenas", "arenas123");

        System.out.println("COMIENZO DEL TEST PILA");

        // Comienzo a apilar
        System.out.print("Apila el primer alumno: \t" + (p1.apilar(alum1) ? sOk : sErr));
        System.out.println(p1.toString());
        System.out.print("Apila el segundo alumno: \t" + (p1.apilar(alum2) ? sOk : sErr));
        System.out.println(p1.toString());
        System.out.print("Apila el tercer alumno: \t" + (p1.apilar(alum3) ? sOk : sErr));
        System.out.println(p1.toString());
        System.out.print("Apila el cuarto alumno: \t" + (p1.apilar(alum4) ? sOk : sErr));
        System.out.println(p1.toString());
        System.out.print("Apila el quinto alumno: \t" + (p1.apilar(alum5) ? sOk : sErr));
        System.out.println(p1.toString());
        System.out.print("Apila el sexto alumno: \t\t" + (p1.apilar(alum6) ? sOk : sErr));
        System.out.println(p1.toString());
        System.out.print("Apila el septimo alumno: \t" + (p1.apilar(alum7) ? sOk : sErr));
        System.out.println(p1.toString());
        System.out.print("Apila el octavo alumno: \t" + (p1.apilar(alum8) ? sOk : sErr));
        System.out.println(p1.toString());
        System.out.print("Apila el noveno alumno: \t" + (p1.apilar(alum9) ? sOk : sErr));
        System.out.println(p1.toString());
        System.out.print("Apila el decimo alumno: \t" + (p1.apilar(alum10) ? sOk : sErr));
        System.out.println(p1.toString());
        System.out.println("Intento apilar el onceavo alumno: \t" + p1.apilar(alum11) + " ");

        System.out.println("Obtengo el tope, debe devolver el Alumno con legajo 10: " + p1.obtenerTope());
        System.out.println("");

        System.out.print("Quito al ultimo alumno, es decir, al decimo: \t" + (p1.desapilar() ? sOk : sErr));
        System.out.println(p1.toString());
        System.out.print("Quito al ultimo alumno, es decir, al noveno: \t" + (p1.desapilar() ? sOk : sErr));
        System.out.println(p1.toString());
        System.out.print("Apilo al alumno con legajo 11: \t\t\t" + (p1.apilar(alum11)? sOk : sErr));
        System.out.println(p1.toString());

        System.out.println("");

        //declaro pila clon
        Pila pilaClone= new Pila();
        pilaClone= p1.clone();
        System.out.println("Creo la pila clone");
        System.out.println("Pila original:\t"+ p1.toString());
        System.out.println("Pila clone:\t"+ pilaClone.toString());
        System.out.println("");

        System.out.println("Desapilo en la pila original y la muestro junto con la clone");
        System.out.println("Pila original:\t"+ (p1.desapilar() ? sOk : sErr) + p1.toString());
        System.out.println("Pila clon:\t"+ pilaClone.toString());
        System.out.println("");

        System.out.println("Desapilo en la clon y la muestro junto con la original");
        System.out.println("Pila clon:\t"+(pilaClone.desapilar()? sOk : sErr)+ pilaClone.toString());
        System.out.println("Pila original:\t"+ p1.toString());
        System.out.println("");

        System.out.println("Vacio la original y la muestro junto con la clon");
        p1.vaciar();
        System.out.println("Pila original vacia:\t"+ p1.toString());
        System.out.println("Pila clon:\t"+pilaClone.toString());
        System.out.println("");

        System.out.println("Apilo un elemento en la clone y en la original");
        Alumno alum12 = new Alumno(12, "Guillermo", "Agüero", "DNI", 12121212, "Buenos Aires", 12, "Neuquen", 299121212,"gAguero","guille123");
        Alumno alum13= new Alumno(13, "Benjamin", "Chandia", " DNI", 13131313, "Buenos Aires", 13, "Neuquen", 299131313, "bChandia", "cr7siuu");
        System.out.println("Pila original:\t"+(p1.apilar(alum13)? sOk : sErr)+ p1.toString());
        System.out.println("Pila clon:\t"+(pilaClone.apilar(alum12)? sOk : sErr)+ pilaClone.toString());
        System.out.println("");

        //Pruebo que funcione la funcion esCapicua
        p1.apilar(alum1);
        p1.apilar(alum2);
        p1.apilar(alum3);
        p1.apilar(alum4);
        p1.apilar(alum4);
        p1.apilar(alum3);
        p1.apilar(alum2);
        p1.apilar(alum1);
        p1.apilar(alum13);
        System.out.println("La pila clon de alumno es capicua? Debe retornar false\t\t"+esCapicua(pilaClone));
        System.out.println(pilaClone.toString());
        System.out.println("La pila original de alumno es capicua? Debe retornar true\t"+esCapicua(p1));
        System.out.println(p1.toString());

    }

    public static boolean esCapicua(Pila unaPila) {
        Pila aux = new Pila();
        Pila pilaClonada = unaPila.clone();
        boolean verificaCapicua;
        for (int i = 0; i < 5; i++) {
            // Paso la mitad de los elementos de la pila clonada al aux
            aux.apilar(pilaClonada.obtenerTope());
            pilaClonada.desapilar();
        }
        // Verifico cada tope de las pilas
        do {
            verificaCapicua = pilaClonada.obtenerTope() == aux.obtenerTope();
        } while (verificaCapicua && pilaClonada.desapilar() && aux.desapilar());
        return verificaCapicua;
    }
}