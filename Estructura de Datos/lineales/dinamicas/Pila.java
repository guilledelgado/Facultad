/****************** Autores*****************
        - Guillermo Delgado, Legajo FAI - 3273
        - Francisco Gutierrez, Legajo FAI - 4047
*/
package lineales.dinamicas;
public class Pila {
    private Nodo tope;


    // Constructor
    public Pila() {
        this.tope = null;
    }


    public boolean apilar(Object nuevoElem) {
        // Crea un nuevo nodo delante de la antigua cabecera
        Nodo nuevo = new Nodo(nuevoElem, this.tope);


        // actualiza el tope para que apunte al nodo nuevo
        this.tope = nuevo;


        // nunca hay error de pila llena, entonces devuelve true
        return true;
    }


    public boolean desapilar() {
        boolean estaVacio = this.tope != null;
        if (estaVacio) {
            // El tope apunta al enlace del ultimo nodo
            tope = tope.getEnlace();
        }
        // Si el tope es nulo devuelve false
        return (estaVacio);
    }


    public Object obtenerTope() {
        //En caso de que el tope es nulo, se envia como un elemento nulo
        Object aux = tope;
        //Cuando el tope no es nulo, envio el elemento
        if (tope != null) {
            aux = tope.getElem();
        }
        return aux;
    }
   
    public boolean esVacia(){
        return (tope == null);
    }


    public void vaciar(){
        //Dejo el tope nulo y el garbage collector se lleva a los nodos "abandonados"
        this.tope = null;
    }


    public Pila clone(){
        Pila pilaClon = new Pila();
        pilaClon.tope = cloneAux(this.tope);
        return pilaClon;
    }


    private Nodo cloneAux(Nodo original){
        Nodo nuevoNodo;
        //El caso base es el nodo que tiene enlazado el null
        if(original.getEnlace() == null){
            //Crea el nodo con el elemento del original y con el enlace en null
            nuevoNodo = new Nodo(original.getElem(), null);
        } else {
            //Paso recursivo crea el nodo con el elemento del nodo original y llama recursivamente al modulo para obtener el nodo
            nuevoNodo = new Nodo(original.getElem(), cloneAux(original.getEnlace()));
        }
        return nuevoNodo;
    }


    public String toString() {
        String s = "";
        if (this.tope == null) {
            s = "Pila vacia";
        } else {
            // Se ubica para recorrer la pila
            Nodo aux = this.tope;
            s = "[";
            while (aux != null) {
                // agrega el texto del elem y avanza
                s += aux.getElem().toString();
                aux = aux.getEnlace();
                if (aux != null) {
                    s += ",";
                }
                s += "]";
            }
        }
        return s;
    }
}
