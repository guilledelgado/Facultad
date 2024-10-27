package TrabajoPractico5;

public class mainComedero {
    public static void main(String[] args) {
        Comedero comedero = new Comedero();
        Perro[] perros = new Perro[10];
        Gato[] gatos = new Gato[10];

        for (int i = 0; i < 10; i++) {
            perros[i]= new Perro(comedero);
            gatos[i]= new Gato(comedero);
        }
        for (int i = 0; i < perros.length; i++) {
            Thread hiloPerro = new Thread(perros[i], "rrope "+(i+1));
            Thread hiloGato = new Thread(gatos[i], "gato "+(i+1));
            hiloPerro.start();
            hiloGato.start();
        }
    }
}
