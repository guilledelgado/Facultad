package usoEspecifico;

import lineales.dinamicas.Lista;

public class NodoAVLMapeoM {
    private Comparable dominio;
    private Lista rango;
    private int altura;
    private NodoAVLMapeoM izquierdo;
    private NodoAVLMapeoM derecho;

    public NodoAVLMapeoM(Comparable dominio, Lista rango) {
        this.dominio = dominio;
        this.rango = rango;
        this.altura = 0;
        this.izquierdo = null;
        this.derecho = null;
    }

    public int getAltura() {
        return altura;
    }

    public Comparable getDominio() {
        return dominio;
    }

    public Lista getRango() {
        return rango;
    }

    public int compareTo(Comparable otroDominio){
        return this.dominio.compareTo(otroDominio);
    }

    public NodoAVLMapeoM getIzquierdo() {
        return izquierdo;
    }

    public NodoAVLMapeoM getDerecho() {
        return derecho;
    }

    public void recalcularAltura(){
        int alturaHijoDer = (this.derecho == null)? -1: derecho.getAltura();
        int alturaHijoIzq = (this.izquierdo == null)? -1: izquierdo.getAltura();
        this.altura = Math.max(alturaHijoDer, alturaHijoIzq) + 1;
    }

    public void setDominio(Comparable nuevoDominio){
        this.dominio = nuevoDominio;
    }

    public void setIzquierdo(NodoAVLMapeoM nuevoIzquierdo){
        this.izquierdo = nuevoIzquierdo;
    }

    public void setDerecho(NodoAVLMapeoM nuevoDerecho){
        this.derecho = nuevoDerecho;
    }

    public void setRango(Lista nuevoRango){
        this.rango = nuevoRango;
    }

    public void agregarRango(Object nuevoRango){
        this.rango.insertar(nuevoRango, 1);
    }

    public void eliminarRango(Object rangoAEliminar){
        this.rango.eliminarApariciones(rangoAEliminar);
    }

}
