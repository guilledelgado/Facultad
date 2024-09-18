package TrabajoPractico3;

public class Hamaca {
    private boolean estaEnUso = false;

    public Hamaca() {}

    public void usarHamaca(){
        estaEnUso = true;
    }

    public void dejarHamaca(){
        estaEnUso = false;
    }
}
