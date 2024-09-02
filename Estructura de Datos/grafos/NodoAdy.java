package grafos;

public class NodoAdy {
    // Atributos
    private NodoVert vertice;
    private NodoAdy sigAdyacente;
    private double etiqueta;

    // Constructor
    public NodoAdy(NodoVert vert, double eti) {
        this.vertice = vert;
        this.sigAdyacente = null;
        this.etiqueta = eti;
    }

    // Modificadores
    public void setVertice(NodoVert vert) {
        this.vertice = vert;
    }

    public void setSigAdyacente(NodoAdy siguiente) {
        this.sigAdyacente = siguiente;
    }

    public void setEtiqueta(double eti) {
        this.etiqueta = eti;
    }

    // Observadores
    public NodoVert getVertice() {
        return vertice;
    }

    public NodoAdy getSigAdyacente() {
        return sigAdyacente;
    }

    public double getEtiqueta() {
        return etiqueta;
    }
}
