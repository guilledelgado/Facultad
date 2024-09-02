package lineales.estaticas;

public class Cola {

    private Object[] arreglo;
    private int frente;
    private int fin;
    private static final int TAMANIO = 10;

    public Cola() {
        this.arreglo = new Object[TAMANIO];
        this.frente = 0;
        this.fin = 0;
    }

    public boolean poner(Object nuevoElem) {
        boolean exito = true;

        if ((this.fin + 1) % TAMANIO == frente) {// La cola esta llena, reporta error
            exito = false;
        } else {
            arreglo[fin] = nuevoElem;
            this.fin = (this.fin + 1) % TAMANIO;
        }

        return exito;
    }

    public boolean sacar() {
        boolean exito = true;

        if (this.esVacia()) {// la cola esta vacia, reporta error
            exito = false;
        } else {// al menos hay 1 elemento: avanza frente (de manera circular)
            this.arreglo[this.frente] = null;
            this.frente = (this.frente + 1) % TAMANIO;
        }

        return exito;
    }

    public Object obtenerFrente() {
        return arreglo[frente];
    }

    public boolean esVacia() {
        return frente == fin;
    }

    public void vaciar() {
        for (int i = 0; i < TAMANIO; i++) {// Se asigna null a todos los elementos
            arreglo[i] = null;
        }
        this.frente = 0;// Se reinicia la posicion de frente y null
        this.fin = 0;
    }

    public Cola clone() {
        Cola nuevaCola = new Cola();
        nuevaCola.frente = this.frente;// Le asigno la posicion del frente original
        nuevaCola.fin = this.fin;// Y del fin original
        for (int i = 0; i < TAMANIO; i++) {// Se asigna los elementos en la posicion del arreglo original
            nuevaCola.arreglo[i] = this.arreglo[i];
        }
        return nuevaCola;
    }

    public String toString() {
        String s = "[";
        int aux = this.frente;
        while (this.arreglo[aux] != null) { //Mientras el objeto en la pos es distindo de null
            s += this.arreglo[aux] + " ";
            aux = (aux + 1) % TAMANIO;// Realizo el recorrido circular
        }
        s = s + "]";
        return s;
    }
}
