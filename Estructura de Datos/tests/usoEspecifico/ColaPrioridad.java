package tests.usoEspecifico;

public class ColaPrioridad {
    private int TAMANIO;
    private CeldaCP[] heap;
    private int ultimo;
    private int ordenLLegada;

    public ColaPrioridad(int tam) {
        TAMANIO = tam;
        heap = new CeldaCP[tam+1];
        ultimo = 0;
        ordenLLegada = 0;
    }

    public boolean insertar(Object elem, int prioridad) {
        boolean exito = false;
        if (ultimo <= TAMANIO) {
            CeldaCP nuevo = new CeldaCP(prioridad, ordenLLegada, elem);
            ordenLLegada++;
            ultimo++;
            this.heap[ultimo] = nuevo;
            hacerSubir();
            exito = true;
        }
        return exito;
    }

    private void hacerSubir() {
        int posNuevo = ultimo;
        boolean salir = false;
        int posPadre = posNuevo / 2;
        while (posPadre >= 1 && !salir) {
            if (this.heap[posNuevo].getPrioridad() < this.heap[posPadre].getPrioridad()) {
                CeldaCP temp = this.heap[posNuevo];
                this.heap[posNuevo] = this.heap[posPadre];
                this.heap[posPadre] = temp;
                posNuevo = posPadre;
                posPadre = posNuevo / 2;
            } else if (this.heap[posNuevo].getPrioridad() == this.heap[posPadre].getPrioridad()) {
                if (this.heap[posNuevo].getOrdenLLegada() < this.heap[posPadre].getOrdenLLegada()) {
                    CeldaCP temp = this.heap[posNuevo];
                    this.heap[posNuevo] = this.heap[posPadre];
                    this.heap[posPadre] = temp;
                    posNuevo = posPadre;
                    posPadre = posNuevo / 2;
                }
            } else {
                salir = true;
            }
        }
    }

    public boolean eliminarCima() {
        boolean exito;
        if (this.ultimo == 0) {
            exito = false;
        } else {
            this.heap[1] = this.heap[ultimo];
            this.ultimo--;
            hacerBajar(1);
            exito = true;
        }
        return exito;
    }

    private void hacerBajar(int posPadre) {
        int posH;
        CeldaCP temp = this.heap[posPadre];
        boolean salir = false;

        while (!salir) {
            posH = posPadre * 2;
            if (posH <= this.ultimo) {
                if (posH < this.ultimo) {
                    if (this.heap[posH + 1].getPrioridad() < this.heap[posH].getPrioridad()) {
                        posH++;
                    } else if (this.heap[posH + 1].getPrioridad() == this.heap[posH].getPrioridad()) {
                        if (this.heap[posH + 1].getOrdenLLegada() < this.heap[posH].getOrdenLLegada()) {
                            posH++;
                        }
                    }
                }
                if (this.heap[posH].getPrioridad() < temp.getPrioridad()) {
                    this.heap[posPadre] = this.heap[posH];
                    this.heap[posH] = temp;
                    posPadre = posH;
                } else if (this.heap[posH].getPrioridad() == temp.getPrioridad()) {
                    if (this.heap[posH].getOrdenLLegada() < temp.getOrdenLLegada()) {
                        this.heap[posPadre] = this.heap[posH];
                        this.heap[posH] = temp;
                        posPadre = posH;
                    }
                } else {
                    salir = true;
                }
            } else {
                salir = true;
            }
        }
    }

    public Object recuperarCima() {
        return this.heap[1].getElemento();
    }

    public boolean esVacio() {
        return this.ultimo == 0;
    }
}
