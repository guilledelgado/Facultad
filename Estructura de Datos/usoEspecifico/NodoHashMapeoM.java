package usoEspecifico;

import lineales.dinamicas.Lista;

public class NodoHashMapeoM {
    // Atributos
    private Object dominio;
    private Object rango;
    private NodoHashMapeoM enlace;

    // Constructores
    public NodoHashMapeoM(Object dom, Object ran, NodoHashMapeoM nodo){
        this.dominio = dom;
        this.rango = ran;
        this.enlace = nodo;
    }

    //Observadores
    public Object getDominio(){
        return dominio;
    }

    public Object getRango(){
        return rango;
    }

    public NodoHashMapeoM getEnlace(){
        return enlace;
    }

    //Modificadores
    public void setRango(Object ran){
        this.rango = ran;
    }

    public void setEnlace(NodoHashMapeoM nodo){
        this.enlace = nodo;
    }
    
    public String toString(){
        return rango.toString() + "->" + enlace.toString();
    }
}
