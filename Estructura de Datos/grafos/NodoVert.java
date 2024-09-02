package grafos;

public class NodoVert {
    // Atributos
    private Object elemento;
    private NodoVert sigVertice;
    private NodoAdy primerAdy;

    // Constructor
    public NodoVert(Object vert) {
        this.elemento = vert;
        this.sigVertice = null;
        this.primerAdy = null;
    }

    public NodoVert(Object vert, NodoVert siguiente) {
        this.elemento = vert;
        this.sigVertice = siguiente;
        this.primerAdy = null;
    }

    // Modificadores
    public void setElemento(Object vert) {
        this.elemento = vert;
    }

    public void setSigVertice(NodoVert siguiente) {
        this.sigVertice = siguiente;
    }

    public void setPrimerAdy(NodoAdy primer) {
        this.primerAdy = primer;
    }

    // Observadores
    public Object getElemento() {
        return elemento;
    }

    public NodoVert getSigVertice() {
        return sigVertice;
    }

    public NodoAdy getPrimerAdy() {
        return primerAdy;
    }
}
