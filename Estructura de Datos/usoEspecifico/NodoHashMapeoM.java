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
        rango = new Lista();
        rango.insertar(ran, 1);
        this.enlace = nodo;
    }

    //Observadores
    public Object getDominio(){
        return dominio;
    }

    public NodoHashMapeoM getEnlace(){
        return enlace;
    }

    //Modificadores
    public void agregarRango(Object ran){
        rango.insertar(ran, rango.longitud() + 1);
    }

    public void setEnlace(NodoHashMapeoM nodo){
        this.enlace = nodo;
    }
    
    public String toString(){
        return rango.toString() + "->" + enlace.toString();
    }
}
