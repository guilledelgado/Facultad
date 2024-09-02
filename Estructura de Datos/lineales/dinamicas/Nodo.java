/****************** Autores*****************
        - Guillermo Delgado, Legajo FAI - 3273
        - Francisco Gutierrez, Legajo FAI - 4047
*/
package lineales.dinamicas;
public class Nodo {
    private Object elem;
    private Nodo enlace;


    //Constructor
    public Nodo (Object elem, Nodo enlace){
        this.elem = elem;
        this.enlace = enlace;
    }


    //modificadoras
    public void setElem(Object elem){
        this.elem = elem;
    }
    public void setEnlace(Nodo enlace){
        this.enlace = enlace;
    }


    //observadoras
    public Object getElem(){
        return elem;
    }
    public Nodo getEnlace(){
        return enlace;
    }
}
