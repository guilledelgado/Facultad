/****************** Autores*****************
        - Guillermo Delgado, Legajo FAI - 3273
        - Francisco Gutierrez, Legajo FAI - 4047
*/
package lineales.estaticas;

public class Pila {
    private Object[] arreglo;
    private int tope;
    private static final int TAMANIO = 10;
   
    //Constructores
    public Pila(){
        this.arreglo = new Object[TAMANIO];
        this.tope = -1;
    }


    //Operaciones
    public boolean apilar(Object nuevoElem){
         // Pone el elemento nuevoElem en el tope de la pila. Devuelve verdadero si el elemento se pudo apilar y falso en caso contrario.
        boolean exito;
        if (this.tope+1 >= TAMANIO){
            //Error pila llena
            exito = false;
        } else {
            // pone el elemento en el tope de la pila e incrementa el tope
            this.tope++;
            this.arreglo[tope]=nuevoElem;
            exito = true;
        }
        return exito;
    }
    public boolean desapilar(){
         /* Saca el elemento del tope de la pila.
          Devuelve verdadero si la pila no estaba vacía al momento de desapilar (es decir que se pudo desapilar) y falso en caso contrario.*/
          boolean exito;
          if (this.tope < 0){
            //Error pila vacia
            exito = false;
          } else {
            //Saca el elemento en el tope de la pila y disminuye el tope
            this.arreglo[tope]=null;
            this.tope--;
            exito = true;
          }
          return exito;
    }
    public Object obtenerTope(){
        Object unObjeto;
         // Devuelve el elemento en el tope de la pila.
         if (this.tope < 0){
            //Comprueba que la pila no este vacía
            unObjeto = null;
         } else {
            //Obtengo el objeto para retornar
            unObjeto = this.arreglo[tope];
         }
         return unObjeto;
    }


    public boolean esVacia(){
         // Devuelve verdadero si la pila no tiene elementos y falso en caso contrario
         return (this.tope < 0);
    }


    public void vaciar(){
         // Saca todos los elementos de la pila.
         for (int i = 0; i < this.tope; i++) {
            this.arreglo[i]=null;
         }
         this.tope = -1;
    }
   
    public Pila clone(){
        /*Devuelve una copia exacta de los datos en la estructura original, y respetando el orden de los mismos,
        en otra estructura del mismo tipo*/
        Pila nuevaPila =new Pila();
        int i = 0;
        while (i <= this.tope){
            nuevaPila.arreglo[i] = this.arreglo[i];
            i++;
        }
        nuevaPila.tope = i-1;
        return nuevaPila;
    }
    public String toString(){
        //Clono la pila y voy mostrando elemento a elemento y elimino el tope
        Pila nuevaPila = clone();
        String listaObjetos = "";
        while(!nuevaPila.esVacia()){
            listaObjetos = nuevaPila.obtenerTope() + " " + listaObjetos;
            nuevaPila.desapilar();
        }
        listaObjetos = "[" + listaObjetos + "]";
        return listaObjetos;
    }
}

