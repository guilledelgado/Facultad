package jerarquicas;

public class NodoGen {
    private Object elem;
    private NodoGen hijoIzquierdo;
    private NodoGen hermanoDerecho;

    public NodoGen(Object elem, NodoGen hijoIzq, NodoGen hermanoDer){
        this.elem = elem;
        hijoIzquierdo = hijoIzq;
        hermanoDerecho = hermanoDer;
    }

    public Object getElem(){
        return elem;
    }

    public NodoGen getHijoIzquierdo(){
        return hijoIzquierdo;
    }

    public NodoGen getHermanoDerecho(){
        return hermanoDerecho;
    }

    public void setElem(Object unElem){
        elem = unElem;
    }

    public void setHijoIzquierdo(NodoGen hijoIzq){
        hijoIzquierdo = hijoIzq;
    }

    public void setHermanoDerecho(NodoGen hermanoDer){
        hermanoDerecho = hermanoDer;
    }
}
