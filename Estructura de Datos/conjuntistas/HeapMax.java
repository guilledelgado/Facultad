package conjuntistas;

public class HeapMax {
    private Comparable[] heap;
    private int ultimo;
    private int TAMANIO;

    public HeapMax(int tam) {
        this.heap = new Comparable[tam];
        this.TAMANIO = tam;
        this.ultimo = 0; // la posicion 0 nunca es utilizada
    }

    public boolean insertar(Comparable elem) {
        boolean exito = false;
        if (ultimo < TAMANIO-1) {
            ultimo++;
            this.heap[ultimo] = elem;
            hacerSubir();
            exito = true;
        }
        return exito;
    }

    private void hacerSubir() {
        int posNuevo = this.ultimo;
        boolean salir = false;
        int posPadre = this.ultimo / 2;
        while (posPadre >= 1 && !salir) {
            if (this.heap[posNuevo].compareTo(this.heap[posPadre]) > 0) {
                Comparable temp = this.heap[posNuevo];
                this.heap[posNuevo] = this.heap[posPadre];
                this.heap[posPadre] = temp;
                posNuevo = posPadre;
                posPadre = posNuevo /2 ;
            } else {
                salir = true;
            }
        }
    }

    public boolean eliminarCima() {
        boolean exito;
        if (this.ultimo == 0) {
            // estructura vacia
            exito = false;
        } else {
            // saca la raiz y pone la ultima hoja en su lugar
            this.heap[1] = this.heap[ultimo];
            this.ultimo--;
            // reestablece la propiedad de heap max
            hacerBajar(1);
            exito = true;
        }
        return exito;
    }

    private void hacerBajar(int posPadre) {
        
        int posH;
        Comparable temp = this.heap[posPadre];
        boolean salir = false;

        while (!salir) {
            posH = posPadre * 2;
            if (posH <= this.ultimo) {
                // temp tiene al menos un hijo (izq) y lo considera mayor

                if (posH < this.ultimo) {
                    // hijo menor tiene hermano derecho
                    if (this.heap[posH + 1].compareTo(this.heap[posH]) > 0) {
                        // el hijo es el mayor de los dos
                        posH++;
                    }
                }
                // compara al hijo menor con el padre
                if (this.heap[posH].compareTo(temp) > 0) {
                    // el hijo es mayor que el padre, los intercambia
                    this.heap[posPadre] = this.heap[posH];
                    this.heap[posH] = temp;
                    posPadre = posH;
                } else {
                    // el padre es menor que los hijos, esta bien ubicado
                    salir = true;
                }
            } else {
                // el temp es hoja, esta bien ubicado
                salir = true;
            }
        }
    }

    public Object recuperarCima() {
        return this.heap[1];
    }

    public boolean esVacio() {
        return this.ultimo == 0;
    }

    public String toString(){
        String s = "[";
        int i = 1;
        while(i <= ultimo){
            s =s + this.heap[i].toString() + ", ";
            i++;
        }
        s += "]";
        return s;
    }
}
