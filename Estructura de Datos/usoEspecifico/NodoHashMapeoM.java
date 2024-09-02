package usoEspecifico;

import lineales.dinamicas.Lista;

public class NodoHashMapeoM {
    // Atributos
    private Object dominio;
    private Lista rango;
    private NodoHashMapeoM enlace;

    // Constructores
    public NodoHashMapeoM(Object dom, Object ran, NodoHashMapeoM nodo){
        this.dominio = dom;
        this.rango = new Lista();
        this.rango.insertar(ran, 1);
        this.enlace = nodo;
    }

    //Observadores
    public Object getDominio(){
        return dominio;
    }

    public Lista getRango(){
        return rango;
    }

    public NodoHashMapeoM getEnlace(){
        return enlace;
    }

    //Modificadores
    public void addRango(Object ran){
        this.rango.insertar(ran, rango.longitud()+1);
    }
    
    public void quitarRango(Object ran){
        this.rango.eliminarApariciones(ran);
    }

    public void setEnlace(NodoHashMapeoM nodo){
        this.enlace = nodo;
    }
    
    public String toString(){
        return "Dominio: "+ dominio.toString() + " Rango: " + rango.toString();
    }
}
