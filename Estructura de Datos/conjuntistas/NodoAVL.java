package conjuntistas;

public class NodoAVL {
    Comparable elem;
    int altura;
    NodoAVL izquierdo;
    NodoAVL derecho;

    public NodoAVL(Comparable elemento, NodoAVL izq, NodoAVL der) {
        elem = elemento;
        izquierdo = izq;
        derecho = der;
        this.recalcularAltura();
    }

    public Comparable getElem() {
        return elem;
    }

    public void setElem(Comparable elemento) {
        elem = elemento;
    }

    public int getAltura() {
        return altura;
    }

    public void recalcularAltura() {
        int alturaHijoDer = (this.derecho == null)? -1: derecho.getAltura();
        int alturaHijoIzq = (this.izquierdo == null)? -1: izquierdo.getAltura();
        this.altura = Math.max(alturaHijoDer, alturaHijoIzq) + 1;
    }

    public NodoAVL getIzquierdo() {
        return izquierdo;
    }

    public NodoAVL getDerecho() {
        return derecho;
    }

    public void setIzquierdo(NodoAVL izquierdo) {
        this.izquierdo = izquierdo;
    }

    public void setDerecho(NodoAVL derecho) {
        this.derecho = derecho;
    }
}
