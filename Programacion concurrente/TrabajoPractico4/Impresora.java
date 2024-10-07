package TrabajoPractico4;

import java.util.concurrent.Semaphore;

public class Impresora {
    private Semaphore estado;
    private char tipo;

    public Impresora(char tipo){
        estado = new Semaphore(1);
        this.tipo = tipo;
    }

    public char getTipo(){
        return tipo;
    }

    public void imprimir() {
        System.out.println("Imprimiendo...");
    }
}
