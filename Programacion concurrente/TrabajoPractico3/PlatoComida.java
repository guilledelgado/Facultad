package TrabajoPractico3;

public class PlatoComida {
    private boolean estaEnUso = false;

    public PlatoComida() {}

    public void usarPlato(){
        estaEnUso = true;
    }

    public void dejarPlato(){
        estaEnUso = false;
    }
}
