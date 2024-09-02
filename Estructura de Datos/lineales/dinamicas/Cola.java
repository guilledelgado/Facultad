package lineales.dinamicas;

public class Cola {
    private Nodo frente;
    private Nodo fin;

    public Cola() {
        this.frente = null;
        this.fin = null;
    }

    public boolean poner(Object tipoElemento) {
        Nodo nuevo = new Nodo(tipoElemento, null);
        if (this.frente == null) {
            this.frente = nuevo;
            this.fin = nuevo;
        } else {
            this.fin.setEnlace(nuevo);
            this.fin = nuevo;
        }
        return true;
    }

    public boolean sacar() {
        boolean exito = true;

        if (this.frente == null) {
            // la cola esta vacia, reporta error
            exito = false;
        } else {
            // al menos hay un elemento:
            // quita el primer elemento y actualiza frente (y fin si queda vacia)
            this.frente = this.frente.getEnlace();
            if (this.frente == null) {
                this.fin = null;
            }
        }
        return exito;
    }

    public Object obtenerFrente() {
        Object aux = frente;
        if (this.frente != null) {
            aux = frente.getElem();
        }
        return aux;
    }

    public boolean esVacia() {
        return frente == null;
    }

    public void vaciar() {
        this.frente = null;
        this.fin = null;
    }

    public Cola clone() {
        Cola nuevaCola = new Cola();
        if (this.frente != null) {
            Nodo auxOriginal = this.frente;
            Nodo auxClon = new Nodo (auxOriginal.getElem(),null);
            nuevaCola.frente = auxClon;
            auxOriginal = auxOriginal.getEnlace();
            while (auxOriginal != null) {
                Nodo aux3 = new Nodo (auxOriginal.getElem(), null);
                auxClon.setEnlace(aux3);
                auxClon = aux3;
                auxOriginal = auxOriginal.getEnlace();
            }
            nuevaCola.fin = auxClon;
        }
        return nuevaCola;
    }

    public String toString() {
        String cadena ="";
        if (this.frente == null) {
            cadena = "Cola vacia";
        } else {
            Nodo aux = this.frente;
            cadena = "[";
            while (aux != null) {
                cadena = cadena + aux.getElem().toString();
                aux = aux.getEnlace();
                if(aux != null){
                    cadena = cadena + ",";
                }
            }
            cadena = cadena + "]";
        }
        return cadena;
    }
}
