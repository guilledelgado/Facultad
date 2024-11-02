package TrabajoPractico8;

public class mainPunto3 {
    public static void main(String[] args) {
        Mostrador mostrador = new Mostrador(1,2,3,30,8);
        Horno hornoA = new Horno(mostrador, 'A');
        Horno hornoB = new Horno(mostrador, 'B');
        Horno hornoC = new Horno(mostrador, 'C');
        Empaquetadora empa1 = new Empaquetadora(mostrador);
        Empaquetadora empa2 = new Empaquetadora(mostrador);
        Brazo brazo = new Brazo(mostrador);
        Thread tHornoA = new Thread(hornoA, "Horno A");
        Thread tHornoB = new Thread(hornoB, "Horno B");
        Thread tHornoC = new Thread(hornoC, "Horno C");
        Thread tEmpa1 = new Thread(empa1, "Empaquetador 1");
        Thread tEmpa2 = new Thread(empa2, "Empaquetador 2");
        Thread tBrazo = new Thread(brazo, "Brazo");
        tHornoA.start();
        tHornoB.start();
        tHornoC.start();
        tEmpa1.start();
        tEmpa2.start();
        tBrazo.start();
    }
}
