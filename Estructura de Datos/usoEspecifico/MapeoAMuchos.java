package usoEspecifico;

import conjuntistas.ArbolAVL;
import lineales.dinamicas.Lista;

public class MapeoAMuchos {
    // Atributos
    private int TAMANIO;
    private NodoHashMapeoM[] tabla;
    private int cant;

    // Constructor
    public MapeoAMuchos(int tam) {
        this.TAMANIO = tam;
        this.tabla = new NodoHashMapeoM[tam];
        this.cant = 0;
    }

    public boolean asociar(Object dom, Object ran) {
        boolean exito = false;
        int pos = Math.abs(dom.hashCode() % TAMANIO);
        NodoHashMapeoM aux = this.tabla[pos];
        while (!exito && aux != null) {
            exito = aux.getDominio().equals(dom);
            if (exito) {
                aux.addRango(ran);
            }
            aux = aux.getEnlace();
        }
        if (!exito) {
            this.tabla[pos] = new NodoHashMapeoM(dom, ran, this.tabla[pos]);
            this.cant++;
        }
        return !exito;
    }

    public boolean desasociar(Object dom, Object ran) {
        int pos = Math.abs(dom.hashCode() % TAMANIO);
        NodoHashMapeoM aux = this.tabla[pos];
        NodoHashMapeoM aux2 = null;
        boolean exito = false;
        while (!exito && aux != null) {
            exito = aux.getDominio().equals(dom);
            if (exito) {
                aux.quitarRango(ran);
                if (aux.getRango().esVacia()) {
                    if (aux2 == null) {
                        tabla[pos] = null;
                    } else {
                        aux2.setEnlace(aux.getEnlace());
                    }
                    this.cant--;
                }
            }
            aux2 = aux;
            aux = aux.getEnlace();
        }

        return exito;
    }

    public Lista obtenerValor(Object dom) {
        Lista lis = new Lista();
        int pos = Math.abs(dom.hashCode() % TAMANIO);
        NodoHashMapeoM aux = this.tabla[pos];
        boolean encontrado = aux.getDominio().equals(dom);
        while (!encontrado && aux != null) {
            encontrado = aux.getDominio().equals(dom);
            aux = aux.getEnlace();
        }
        if (encontrado) {
            lis = aux.getRango();
        }
        return lis;
    }
//Guardar elementos Object en vez de los datos de cada objeto
    public Lista obtenerDominio(Object dom){
        int pos = Math.abs(dom.hashCode()%TAMANIO);
        Lista lis = new Lista();
        NodoHashMapeoM aux = this.tabla[pos];
        while(aux!=null){
            if(aux.getDominio().equals(dom)){
                lis.insertar(aux.getDominio().toString() + " " + aux.getRango().toString(), lis.longitud()+1);
            }
            aux = aux.getEnlace();
        }
        return lis;
    }

    public Lista obtenerConjuntoDominio() {
        Lista lis = new Lista();
        NodoHashMapeoM aux;
        if (cant > 0) {
            for (int i = 0; i < tabla.length; i++) {
                aux = tabla[i];
                while (aux != null) {
                    lis.insertar(aux.getDominio(), lis.longitud() + 1);
                    aux = aux.getEnlace();
                }
            }
        }
        return lis;
    }

    public Lista obtenerConjuntoRango() {
        ArbolAVL conjuntoRangos = new ArbolAVL();
        NodoHashMapeoM aux;
        Lista lisRangos;
        if (cant > 0) {
            // Recorro toda la tabla
            for (int i = 0; i < tabla.length; i++) {
                aux = tabla[i];
                // Recorro cada nodo de la celda i
                while (aux != null) {
                    lisRangos = aux.getRango();
                    // Inserto cada Rango en un ArbolAVL para que no haya repeticiones
                    for (int j = 1; j < lisRangos.longitud(); j++) {
                        conjuntoRangos.insertar((Comparable) lisRangos.recuperar(j));
                    }
                    aux = aux.getEnlace();
                }
            }
        }
        return conjuntoRangos.listar();
    }

    public String toString() {
        String s = "";
        NodoHashMapeoM aux;
        for (int i = 0; i < tabla.length; i++) {
            aux = tabla[i];
            while (aux != null) {
                s = s + " [" + (i + 1) + "] " + aux.toString() + "\n";
                aux = aux.getEnlace();
            }
        }
        return s;
    }
}
