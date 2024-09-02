package tests.usoEspecifico;

public class CeldaCP {
    private int prioridad;
    private int ordenLLegada;
    private Object elemento;

    public CeldaCP(int prio, int orden, Object elem){
        this.prioridad = prio;
        this.ordenLLegada = orden;
        this.elemento = elem;
    }

    public int getOrdenLLegada(){
        return ordenLLegada;
    }

    public Object getElemento(){
        return elemento;
    }

    public int getPrioridad(){
        return prioridad;
    }
}
