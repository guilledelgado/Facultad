package TrabajoPractico3;

public class PlatoComida {
    private boolean estaEnUso;

    public PlatoComida() {
        estaEnUso = false;
    }

    public void usarPlato() {
        estaEnUso = true;
    }

    public void dejarPlato() {
        estaEnUso = false;
    }

}
