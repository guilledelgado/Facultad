package usoEspecifico;

import lineales.dinamicas.Lista;

public class MapeoAMuchosAVL {
    NodoAVLMapeoM raiz;

    public MapeoAMuchosAVL(){
        this.raiz = null;
    }

    public boolean asociar(Comparable dominio, Object rango){
        boolean exito = true;
        if (raiz == null){
            Lista lis = new Lista();
            lis.insertar(rango, 1);
            raiz = new NodoAVLMapeoM(dominio, lis);
        } else {
            if(raiz.getDominio().compareTo(dominio) == 0){
                raiz.agregarRango(rango);
            } else if (raiz.getDominio().compareTo(dominio) > 0){
                exito = asociarAux(dominio, rango, raiz.getIzquierdo(), raiz);
            } else {
                exito = asociarAux(dominio, rango, raiz.getDerecho(), raiz);
            }
        }
        return exito;
    }

    private boolean asociarAux(Comparable dominio, Object rango, NodoAVLMapeoM nodo, NodoAVLMapeoM nodoPadre){
        boolean exito = true;
        if (nodo != null) {
            if (nodo.getDominio().compareTo(dominio) == 0){
                nodo.agregarRango(rango);
            } else if (nodo.getDominio().compareTo(dominio) > 0){
                exito = asociarAux(dominio, rango, nodo.getIzquierdo(), nodo);
            } else {
                exito = asociarAux(dominio, rango, nodo.getDerecho(), nodo);
            }
        } else {
            Lista lis = new Lista();
            lis.insertar(rango, 1);
            NodoAVLMapeoM nuevo = new NodoAVLMapeoM(dominio, lis);
            if (nodoPadre.getDominio().compareTo(dominio) > 0){
                nodoPadre.setIzquierdo(nuevo);
            } else {
                nodoPadre.setDerecho(nuevo);
            }
        }
        return exito;
    }
}
