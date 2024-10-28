package TrabajoPractico6;

import java.util.Random;

public class Estudiante implements Runnable{
    private SalaEstudio sala;

    public Estudiante(SalaEstudio sala){
        this.sala = sala;
    }

    public void run(){
        try {
            sala.ocuparMesa();
            Thread.sleep((int) new Random().nextDouble(2,5)*1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        sala.desocuparMesa();
    }
}
