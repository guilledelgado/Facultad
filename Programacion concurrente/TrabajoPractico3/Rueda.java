package TrabajoPractico3;

public class Rueda {
    private boolean estaEnUso;
    public Rueda() {
        estaEnUso = false;
    }

    public void usarRueda() {
        estaEnUso = true;
    }

    public void dejarRueda() {
        estaEnUso = false;
    }

}
