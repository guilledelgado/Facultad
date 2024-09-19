package TrabajoPractico3;

public class Surtidor {
    private double cantidadDisponible;

    public Surtidor(double cantidadDisponible) {
        this.cantidadDisponible = cantidadDisponible;
    }

    public void cargarCombustible(double cant){
        if (cant <= cantidadDisponible){
            cantidadDisponible -= cant;
            System.out.println("Se realizo una carga de " + cant + " l");
        } else {
            System.out.println("No se puede realizar la carga de " + cant + " l");
        }
    }

    public boolean puedeCargarCombustible(double cant){
        return cantidadDisponible >= cant;
    }
}
