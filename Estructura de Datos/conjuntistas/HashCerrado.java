package conjuntistas;

public class HashCerrado {
    private int TAMANIO;
    private CeldaHash[] tabla;
    private int cant;
    private int VACIO = 0;
    private int BORRADO = -1;
    private int OCUPADO = 1;

    public HashCerrado(int tamanio) {
        this.TAMANIO = tamanio;
        this.tabla = new CeldaHash[tamanio];
        this.cant = 0;
    }

    public boolean insertar(Object elemento) {
        int pos = elemento.hashCode() % TAMANIO;
        boolean exito = false;
        int intento = 1;
        while (!exito && intento < this.TAMANIO) {
            if(this.tabla[pos].getEstado() != OCUPADO){
                this.tabla[pos].setElemento(elemento);
                this.tabla[pos].setEstado(OCUPADO);
                this.cant++;
                exito = true;
            } else if (this.tabla[pos].getElemento().equals(elemento)){
                intento = TAMANIO+1;
            } else {
                pos = (pos+1)%this.TAMANIO;
                intento++;
            }
        }
        return exito;
    }

    public boolean eliminar(Object buscado) {
        // calcula posicion inicial
        int pos = buscado.hashCode() % this.TAMANIO;
        boolean encontrado = false;
        int intento = 1;

        // busca el elemento hasta encontrarlo o encontrar una celda vacia
        // o para despues de TAMANIO intentos
        while (!encontrado && intento < this.TAMANIO && this.tabla[pos].getEstado() != VACIO) {
            if (this.tabla[pos].getEstado() == OCUPADO) {
                encontrado = this.tabla[pos].getElemento() == buscado;
                if (encontrado) {
                    // si lo encuentra lo marca y para el ciclo
                    this.tabla[pos].setEstado(BORRADO);
                    this.cant--;
                }
            }
            pos = (pos + 1) % this.TAMANIO;
            intento++;
        }

        return encontrado;
    }
}
