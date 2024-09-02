package conjuntistas;

import lineales.dinamicas.Nodo;
import lineales.dinamicas.Lista;

public class HashAbierto {
    private int TAMANIO;
    private Nodo[] tabla;
    private int cant;

    public HashAbierto(int tamanio) {
        this.TAMANIO = tamanio;
        this.tabla = new Nodo[tamanio];
        this.cant = 0;
    }

    public boolean insertar(Object nuevoElem) {
        // primero verifica si el elemento ya esta cargado
        // si no lo encuentra lo pone adelante del resto
        int pos = nuevoElem.hashCode() % this.TAMANIO;
        Nodo aux = this.tabla[pos];
        boolean encontrado = false;
        while (!encontrado && aux != null) {
            encontrado = aux.getElem().equals(nuevoElem);
            aux = aux.getEnlace();
        }
        if (!encontrado) {
            this.tabla[pos] = new Nodo(nuevoElem, this.tabla[pos]);
            this.cant++;
        }
        return !encontrado;
    }

    public boolean pertenece(Object elemento) {
        // verifica si el elemento se encuentra en la tablas
        int pos = elemento.hashCode() % this.TAMANIO;
        Nodo aux = this.tabla[pos];
        boolean encontrado = false;
        while (!encontrado && aux != null) {
            encontrado = aux.getElem().equals(elemento);
            aux = aux.getEnlace();
        }
        return encontrado;
    }

    public boolean eliminar(Object elemento) {
        // primero verifica si el elemento existe en la tabla
        // si existe, lo elimina
        int pos = elemento.hashCode() % this.TAMANIO;
        Nodo aux = this.tabla[pos];
        Nodo aux2 = null;
        boolean encontrado = false;
        while (!encontrado && aux != null) {
            encontrado = aux.getElem().equals(elemento);
            aux2 = aux;
            aux = aux.getEnlace();
        }
        if (encontrado) {
            if (aux2 != null) {
                aux2.setEnlace(aux.getEnlace());
            } else {
                this.tabla[pos] = null;
            }
        }
        return encontrado;
    }

    public boolean esVacio() {
        return cant == 0;
    }

    public Lista listar() {
        Lista lis = new Lista();
        int ultimo = 1;
        for (int i = 0; i < tabla.length; i++) {
            Nodo aux = this.tabla[i];
            while (aux != null) {
                lis.insertar(aux.getElem(), ultimo);
                aux = aux.getEnlace();
                ultimo++;
            }
        }
        return lis;
    }
}
