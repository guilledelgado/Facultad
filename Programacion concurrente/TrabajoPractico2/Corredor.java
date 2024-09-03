package TrabajoPractico2;

import java.util.Random;

public class Corredor implements Runnable {
    String nombreCorredor;
    int distanciaRecorrida;

    public Corredor(String nombre){
        nombreCorredor = nombre;
        distanciaRecorrida = 0;
    }

    public void run(){
        Random random = new Random();
        System.out.println("Comenzando "+nombreCorredor+ " distancia: " + distanciaRecorrida);
        try{
            while (distanciaRecorrida <= 100){
                Thread.sleep(400);
                distanciaRecorrida = distanciaRecorrida + (random.nextInt(10) + 1);
                System.out.println(nombreCorredor+" recorre "+distanciaRecorrida);
            }
        } catch (InterruptedException exc){
            System.out.println(nombreCorredor+" Interrumpido");
        }
        System.out.println("Terminado "+ nombreCorredor + " distancia "+distanciaRecorrida);
    }

    public int getDistancia(){
        return distanciaRecorrida;
    }

    public String getNombre(){
        return nombreCorredor;
    }
}
