package jerarquicas;

public class NodoArbol {
    
    //Atributos
    private Object elem;
    private NodoArbol izquierdo;
    private NodoArbol derecho;

    //Constructor
    public NodoArbol(Object elem, NodoArbol hijoIzq, NodoArbol hijoDer){
        this.elem = elem;
        this.izquierdo = hijoIzq;
        this.derecho = hijoDer;
    }

    //Observadores
    public Object getElem(){
        return this.elem;
    }

    public NodoArbol getIzquierdo(){
        return this.izquierdo;
    }

    public NodoArbol getDerecho(){
        return this.derecho;
    }

    //Modificadores
    public void setElem(Object elem){
        this.elem = elem;
    }

    public void setIzquierdo(NodoArbol hijoIzq){
        this.izquierdo = hijoIzq;
    }

    public void setDerecho(NodoArbol hijoDer){
        this.derecho = hijoDer;
    }
}
