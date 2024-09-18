package TrabajoPractico3;

public class Energia {
    private int energia = 10;

    public Energia() {
    }

    public void modificarEnergia(int cantidad) {
        if (cantidad + energia <= 10) {
            energia += cantidad;
        }
    }

    public int getEnergia() {
        return energia;
    }
}
