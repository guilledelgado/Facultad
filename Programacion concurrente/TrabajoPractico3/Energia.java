package TrabajoPractico3;

public class Energia {
    private int energia = 10;

    public Energia() {}

    public void modificarEnergia(int cantidad) {
        energia += cantidad;
    }

    public int getEnergia() {
        return energia;
    }
}
