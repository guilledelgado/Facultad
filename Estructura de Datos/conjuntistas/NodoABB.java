package conjuntistas;

public class NodoABB {
    
    private Comparable elem;
    private NodoABB hijoIzquierdo;
    private NodoABB hijoDerecho;

    public NodoABB(Comparable elemento, NodoABB hijoI, NodoABB hijoD) {
        this.elem = elemento;
        this.hijoDerecho = hijoD;
        this.hijoIzquierdo = hijoI;
    }

    public NodoABB(Comparable elemento) {
        this.elem = elemento;
        hijoDerecho = null;
        hijoIzquierdo = null;
    }

    public Comparable getElem() {
        return elem;
    }

    public NodoABB getIzquierdo() {
        return hijoIzquierdo;
    }

    public NodoABB getDerecho() {
        return hijoDerecho;
    }

    public void setElem(Comparable elemento) {
        this.elem = elemento;
    }

    public void setIzquierdo(NodoABB hijoI) {
        this.hijoIzquierdo = hijoI;
    }

    public void setDerecho(NodoABB hijoD) {
        this.hijoDerecho = hijoD;
    }
}
