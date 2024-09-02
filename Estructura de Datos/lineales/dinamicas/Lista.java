package lineales.dinamicas;

public class Lista {
    private Nodo cabecera;

    public Lista() {
        cabecera = null;
    }

    public boolean insertar(Object nuevoElemento, int pos) {
        // inserta el elemento nuevo en la posicion pos
        // detecta y reporta error posicion invalida

        boolean exito = true;

        if (pos < 1 || pos > this.longitud() + 1) {
            exito = false;
        } else {
            if (pos == 1) {// crea un nuevo nodo y se enlaza en la cabecera
                this.cabecera = new Nodo(nuevoElemento, this.cabecera);
            } else {
                Nodo aux = this.cabecera;
                int i = 1;
                while (i < pos - 1) {
                    aux = aux.getEnlace();
                    i++;
                }
                // crea el nodo y lo enlaza
                Nodo nuevo = new Nodo(nuevoElemento, aux.getEnlace());
                aux.setEnlace(nuevo);
                ;
            }
        }
        // nunca hay error de lista llena, entonces devuelve true
        return exito;
    }

    public boolean eliminar(int pos) {
        // elimino el nodo ubicado en la pos
        // reporta error si la posicion es negativa o mayor a la longitud
        boolean exito = true;
        if (pos < 1 || pos > this.longitud() || this.cabecera == null) {
            exito = false;
        } else {
            if (pos == 1) {// El caso especial de que se desea eliminar el primer elemento
                this.cabecera = this.cabecera.getEnlace();
            } else {
                Nodo aux = this.cabecera;
                int i = 1;
                while (i < pos - 1) {
                    aux = aux.getEnlace();
                    i++;
                }
                // enlaza el nodo anterior a la posicion
                aux.setEnlace(aux.getEnlace().getEnlace());
            }
        }
        return exito;
    }

    public Object recuperar(int pos) {
        // Devuelve el elemento de la posición pos. La precondición es que la posición
        // sea válida.
        Object aux;
        int longi = this.longitud();
        if (pos < 1 || pos > longi) { // En caso de enviar una posicion incorrecta, devuelve nulo
            aux = null;
        } else {
            Nodo nodoAux = this.cabecera;
            for (int i = 1; i < pos; i++) {// recorro los nodos hasta la posicion
                nodoAux = nodoAux.getEnlace();
            }
            aux = nodoAux.getElem();
        }
        return aux;
    }

    public int localizar(Object elemento) {
        // Busco el elemento enviado y retorno la primera ocurrencia del mismo
        int pos = -1, i = 1;
        if (this.cabecera != null) {// Si la cola es vacia retorna el menos uno
            Nodo aux = this.cabecera;
            int longi = this.longitud();
            while (i <= longi) {
                if (aux.getElem() == elemento) {
                    pos = i;
                    i = longi + 1;
                }
                aux = aux.getEnlace();
                i++;

            }

        }

        return pos;
    }

    public int longitud() {
        int longitud = 0;
        Nodo aux = this.cabecera;
        while (aux != null) {
            longitud++;
            aux = aux.getEnlace();
        }
        return longitud;
    }

    public boolean esVacia() {
        return this.cabecera == null;
    }

    public void vaciar() {
        cabecera = null;
    }

    public Lista clone() {
        Lista listaClon = new Lista();
        if (this.cabecera != null) {
            Nodo aux = this.cabecera;
            Nodo aux2 = new Nodo(aux.getElem(), null);
            listaClon.cabecera = aux2;
            aux = aux.getEnlace();
            while (aux != null) {
                Nodo aux3 = new Nodo(aux.getElem(), null);
                aux2.setEnlace(aux3);
                aux2 = aux3;
                aux = aux.getEnlace();
            }
        }
        return listaClon;
    }

    public String toString() {
        String s = "";
        if (this.cabecera == null) {
            s = "Lista vacia";
        } else {
            Nodo aux = this.cabecera;
            s = "[";
            while (aux != null) {
                s = s + aux.getElem().toString();
                aux = aux.getEnlace();
                if (aux != null) {
                    s = s + ", ";
                }
            }
            s = s + "]";
        }
        return s;
    }

    public Lista obtenerMultiplos(int num) {
        Lista nuevaLista = new Lista();
        int i = 1;
        if (this.cabecera != null) {
            Nodo aux = this.cabecera;
            Nodo aux2 = new Nodo(aux.getElem(), null);
            if (num == 1) {
                nuevaLista.cabecera = aux2;
            }
            while (aux.getEnlace() != null) {
                if ((i + 1) % num == 0) {
                    if (nuevaLista.cabecera == null) {
                        aux2.setElem(aux.getEnlace().getElem());
                        nuevaLista.cabecera = aux2;
                    } else {
                        Nodo nuevo = new Nodo(aux.getEnlace().getElem(), null);
                        aux2.setEnlace(nuevo);
                        aux2 = nuevo;
                    }
                }
                aux = aux.getEnlace();
                i++;
            }
        }
        return nuevaLista;
    }

    public void eliminarApariciones(Object x) {
        eliminarAparicionesAux(this.cabecera, x);
    }

    private void eliminarAparicionesAux(Nodo nodoActual, Object x) {
        if (nodoActual != null) {
            if (this.cabecera == nodoActual) {
                if (nodoActual.getElem().equals(x)) {
                    this.cabecera = nodoActual.getEnlace();
                    eliminarAparicionesAux(this.cabecera, x);
                } else {
                    if (nodoActual.getEnlace() != null) {
                        if (nodoActual.getEnlace().getElem().equals(x)) {
                            nodoActual.setEnlace(nodoActual.getEnlace().getEnlace());
                        }
                        eliminarAparicionesAux(nodoActual.getEnlace(), x);
                    }
                }
            } else {
                if (nodoActual.getEnlace() != null) {
                    if (nodoActual.getEnlace().getElem().equals(x)) {
                        nodoActual.setEnlace(nodoActual.getEnlace().getEnlace());
                    }
                    eliminarAparicionesAux(nodoActual.getEnlace(), x);
                }

            }
        }
    }
    
    public void setLista(Lista l) { 
        this.cabecera = l.cabecera;
    }
}