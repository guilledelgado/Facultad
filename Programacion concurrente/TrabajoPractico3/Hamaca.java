package TrabajoPractico3;

public class Hamaca {
    private boolean estaEnUso;

    public Hamaca() {
        estaEnUso = false;
    }

    public void usarHamaca() {
        estaEnUso = true;
    }

    public void dejarHamaca() {
        estaEnUso = false;
    }
}