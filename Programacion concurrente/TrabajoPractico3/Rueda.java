package TrabajoPractico3;

public class Rueda {
    private boolean estaEnUso = false;

    public Rueda() {}

    public void usarRueda(){
        estaEnUso = true;
    }

    public void dejarRueda(){
        estaEnUso = false;
    }
}
