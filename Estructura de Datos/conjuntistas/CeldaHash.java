package conjuntistas;

public class CeldaHash {
    private Object elemento;
    private int estado;
    
    public CeldaHash(){
        this.elemento = null;
        this.estado = 0;
    }

    public CeldaHash(Object elem, int est){
        this.elemento = elem;
        this.estado = est;
    }

    public void setElemento(Object elem){
        this.elemento = elem;
    }

    public void setEstado(int est){
        this.estado = est;
    }

    public Object getElemento(){
        return elemento;
    }

    public int getEstado(){
        return estado;
    }
}
