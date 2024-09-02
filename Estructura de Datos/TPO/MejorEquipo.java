package TPO;

public class MejorEquipo implements Comparable {
    private String nombre;
    private int goles;

    public MejorEquipo(String nom, int gol) {
        this.nombre = nom;
        this.goles = gol;
    }

    @Override
    public int compareTo(Object aux) {
        return Integer.compare(this.goles, ((MejorEquipo) aux).getGoles());
    }

    public int getGoles() {
        return goles;
    }

    public String getNombres(){
        return nombre;
    }

    public String toString(){
        return this.nombre + " Goles: " + this.goles+" |";
    }
}
