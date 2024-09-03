package TrabajoPractico2;

public class Carrera {
    public static void main(String[] args) {

        Corredor[] corredores = new Corredor[5];
        corredores[0] = new Corredor("Alfre");
        corredores[1] = new Corredor("Luquitas");
        corredores[2] = new Corredor("German");
        corredores[3] = new Corredor("Joaco");
        corredores[4] = new Corredor("Bobson");

        Thread[] hiloCorredores = new Thread[5];
        for (int i = 0; i < corredores.length; i++) {
            hiloCorredores[i] = new Thread(corredores[i]);
        }

        for (int i = 0; i < hiloCorredores.length; i++) {
            hiloCorredores[i].start();
        }

        for (int i = 0; i < hiloCorredores.length; i++) {
            try {
                hiloCorredores[i].join();
            } catch (Exception e) {
                System.out.println("Error");
            }
        }
        Corredor ganador = null;
        int mayorDistancia = 0;
        for (int i = 0; i < corredores.length; i++) {
            if(corredores[i].getDistancia()>mayorDistancia){
                ganador = corredores[i];
                mayorDistancia = ganador.getDistancia();
            }
        }
        System.out.println("El ganador es "+ganador.getNombre()+" recorriendo "+mayorDistancia+" mts");
    }
}
