package jerarquicas;

import lineales.dinamicas.Lista;

import lineales.dinamicas.Cola;

public class ArbolBin {

    // atributos
    private NodoArbol raiz;

    // Constructor
    public ArbolBin() {
        raiz = null;
    }

    public boolean insertar(Object elemNuevo, Object elemPadre, char lugar) {

        boolean exito = true;

        if (this.raiz == null) {
            // si el arbol esta vacio, pone elem nuevo en la raiz
            this.raiz = new NodoArbol(elemNuevo, null, null);
        } else {
            // si arbol no esta vacio, busca al padre
            NodoArbol nPadre = obtenerNodo(this.raiz, elemPadre);

            // si padre existe y lugar no está ocupado lo pone, sino da error
            if (nPadre != null) {
                if (lugar == 'I' && nPadre.getIzquierdo() == null) {
                    nPadre.setIzquierdo(new NodoArbol(elemNuevo, null, null));
                } else if (lugar == 'D' && nPadre.getDerecho() == null) {
                    nPadre.setDerecho(new NodoArbol(elemNuevo, null, null));
                } else {
                    exito = false;
                }
            }
        }
        return exito;
    }

    private NodoArbol obtenerNodo(NodoArbol n, Object buscado) {
        // metodo PRIVADO que busca un elemento y devuelve el nodo que
        // lo contiene. Si no se encuentra buscado devuelve null

        NodoArbol resultado = null;
        if (n != null) {
            if (n.getElem().equals(buscado)) {
                // su el buscado es n, lo devuelve
                resultado = n;
            } else {
                // no es el buscado: busca primero el HI
                resultado = obtenerNodo(n.getIzquierdo(), buscado);
                // si no lo encontro en el HI, busca en HD
                if (resultado == null) {
                    resultado = obtenerNodo(n.getDerecho(), buscado);
                }
            }
        }
        return resultado;
    }

    public boolean insertarPorPosicion(Object elemNuevo, int posPadre, char posHijo) {

        boolean exito = true;

        if (this.raiz == null) {
            // si el arbol está vacio, pone elem nuevo en la raiz
            this.raiz = new NodoArbol(elemNuevo, null, null);
        } else {
            Lista lis = listarPreorden();
            if (lis.recuperar(posPadre) != null) {
                NodoArbol nPadre = obtenerNodo(this.raiz, lis.recuperar(posPadre));
                if (posHijo == 'I' && nPadre.getIzquierdo() == null) {
                    nPadre.setIzquierdo(new NodoArbol(elemNuevo, null, null));
                } else if (posHijo == 'D' && nPadre.getDerecho() == null) {
                    nPadre.setDerecho(new NodoArbol(elemNuevo, null, null));
                } else {
                    exito = false;
                }
            }

        }
        return exito;
    }

    public Object padre(Object elem) {
        return padreAux(this.raiz, elem);
    }

    private Object padreAux(NodoArbol n, Object elem) {
        Object encontrado = null;
        // Corroboro si uno de los hijos tiene el elemento
        if (n != null) {
            if (n.getIzquierdo().getElem().equals(elem) || n.getDerecho().getElem().equals(elem)) {
                encontrado = n.getElem();
            } else { // Si no lo tiene, llamo al hijo izquierdo
                encontrado = padreAux(n.getIzquierdo(), elem);
                if (encontrado == null) {// Si no encuentra en el izquierdo, llamo al derecho
                    encontrado = padreAux(n.getDerecho(), elem);
                }
            }
        }
        return encontrado;
    }

    public int altura() {
        int altura = -1;
        if (this.raiz != null) {
            altura = alturaAux(this.raiz);
        }
        return altura;
    }

    private int alturaAux(NodoArbol n) {
        int resultado = 0, alturaHI = 0, alturaHD = 0;
        // Calculo desde n la altura del hijo izquierdo
        if (n.getIzquierdo() != null) {
            alturaHI = alturaAux(n.getIzquierdo()) + 1;
        }
        // Calculo desde n la altura del hijo derecho
        if (n.getDerecho() != null) {
            alturaHD = alturaAux(n.getDerecho()) + 1;
        }
        // Comparo las alturas
        if (alturaHI >= alturaHD) {
            resultado = alturaHI;
        } else {
            resultado = alturaHD;
        }

        return resultado;
    }

    public int nivel(Object unElemento) {
        return nivelAux(unElemento, this.raiz, 0);
    }

    private int nivelAux(Object elem, NodoArbol nodoActual, int nivel) {
        int verifica = -1;
        if (nodoActual != null) {
            if (nodoActual.getElem().equals(elem)) {
                verifica = nivel;
            } else {
                verifica = nivelAux(elem, nodoActual.getIzquierdo(), nivel + 1);
                if (verifica == -1) {
                    verifica = nivelAux(elem, nodoActual.getDerecho(), nivel + 1);
                }
            }
        }
        return verifica;
    }

    public boolean esVacio() {
        return raiz == null;
    }

    public void vaciar() {
        this.raiz = null;
    }

    public ArbolBin clone() {
        ArbolBin nuevoArbol = new ArbolBin();
        if (this.raiz != null) {
            nuevoArbol.raiz = cloneAux(this.raiz);
        }
        return nuevoArbol;
    }

    private NodoArbol cloneAux(NodoArbol nodo) {
        NodoArbol nuevoNodo = new NodoArbol(nodo.getElem(), null, null);
        if (nodo.getIzquierdo() != null) {
            nuevoNodo.setIzquierdo(cloneAux(nodo.getIzquierdo()));
        }
        if (nodo.getDerecho() != null) {
            nuevoNodo.setDerecho(cloneAux(nodo.getDerecho()));
        }

        return nuevoNodo;
    }

    public String toString() {
        String cadena = "";
        if (this.raiz != null) {
            cadena = toStringAux(this.raiz);
        } else {
            cadena = "El arbol está vacio";
        }
        return cadena;
    }

    private String toStringAux(NodoArbol nodo) {
        String dato = "";
        if (nodo != null) {
            dato = nodo.getElem().toString() + "\t|";
            if (nodo.getIzquierdo() != null) {
                dato = dato + "HI: " + nodo.getIzquierdo().getElem().toString() + "\t|";
            } else {
                dato = dato + "HI: ~ \t|";
            }
            if (nodo.getDerecho() != null) {
                dato = dato + "HD: " + nodo.getDerecho().getElem().toString();
            } else {
                dato = dato + "HD: ~";
            }
            dato = dato + "\n";
            if (nodo.getIzquierdo() != null) {
                dato = dato + toStringAux(nodo.getIzquierdo());
            }
            if (nodo.getDerecho() != null) {
                dato = dato + toStringAux(nodo.getDerecho());
            }

        }
        return dato;
    }

    public Lista listarPreorden() {
        // retorna una lista con los elementos del arbol en PREORDEN
        Lista lis = new Lista();
        listarPreordenAux(this.raiz, lis);
        return lis;
    }

    private void listarPreordenAux(NodoArbol nodo, Lista lis) {
        // metodo recursivo PRIVADO porque su parametro es de tipo NodoArbol
        if (nodo != null) {
            // visita el elemento en el nodo
            lis.insertar(nodo.getElem(), lis.longitud() + 1); // (1)

            // recorre a sus hijos en preorden
            listarPreordenAux(nodo.getIzquierdo(), lis); // (2)
            listarPreordenAux(nodo.getDerecho(), lis); // (3)
        }
    }

    public Lista listarPosorden() {
        Lista lis = new Lista();
        listarPosordenAux(this.raiz, lis);
        return lis;
    }

    private void listarPosordenAux(NodoArbol nodo, Lista lis) {
        if (nodo != null) {
            listarPosordenAux(nodo.getIzquierdo(), lis);
            listarPosordenAux(nodo.getDerecho(), lis);
            lis.insertar(nodo.getElem(), lis.longitud() + 1);
        }
    }

    public Lista listarInorden() {
        Lista lis = new Lista();
        listarInordenAux(this.raiz, lis);
        return lis;
    }

    private void listarInordenAux(NodoArbol nodo, Lista lis) {
        if (nodo != null) {
            listarInordenAux(nodo.getIzquierdo(), lis);
            lis.insertar(nodo.getElem(), lis.longitud() + 1);
            listarInordenAux(nodo.getDerecho(), lis);
        }
    }

    public Lista listarPorNiveles() {
        Cola cola = new Cola();
        Lista lista = new Lista();
        cola.poner(this.raiz);
        while (!cola.esVacia()) {
            NodoArbol nodoActual = (NodoArbol) cola.obtenerFrente();
            lista.insertar(nodoActual.getElem(), lista.longitud() + 1);
            cola.sacar();
            if (nodoActual.getIzquierdo() != null) {
                cola.poner(nodoActual.getIzquierdo());
            }
            if (nodoActual.getDerecho() != null) {
                cola.poner(nodoActual.getDerecho());
            }
        }

        return lista;
    }

    public Lista frontera() {
        Lista lis = new Lista();
        fronteraAux(this.raiz, lis);
        return lis;
    }

    private void fronteraAux(NodoArbol nodo, Lista lis) {
        if (nodo != null) {
            if (nodo.getIzquierdo() == null && nodo.getDerecho() == null) {
                lis.insertar(nodo.getElem(), lis.longitud() + 1);
            }
            fronteraAux(nodo.getIzquierdo(), lis);
            fronteraAux(nodo.getDerecho(), lis);
        }
    }

    public Lista obtenerAncestros(Object unElemento) {
        Lista lis = new Lista();
        if (this.raiz != null) {
            obtenerAncestrosAux(this.raiz, lis, unElemento);
            if (!lis.esVacia()) {
                lis.eliminar(1);
            }
        }
        return lis;
    }

    private void obtenerAncestrosAux(NodoArbol nodo, Lista lis, Object elem) {
        // Caso base, encontrar el elemento ingresado
        if (nodo.getElem().equals(elem)) {
            lis.insertar(nodo.getElem(), lis.longitud() + 1);
        } else {
            // Paso recursivo, busco primero por el nodo izquierdo y luego por el nodo
            // derecho
            if (nodo.getIzquierdo() != null) {
                obtenerAncestrosAux(nodo.getIzquierdo(), lis, elem);
            }
            if (nodo.getDerecho() != null && lis.esVacia()) {
                obtenerAncestrosAux(nodo.getDerecho(), lis, elem);
            }
            if (!lis.esVacia()) {
                lis.insertar(nodo.getElem(), lis.longitud() + 1);
            }
        }
    }

    public Lista obtenerDescendientes(Object unElemento) {
        Lista lis = new Lista();
        if (this.raiz != null) {
            obtenerDescendientesAux(this.raiz, lis, unElemento);
            if (!lis.esVacia()) {
                lis.eliminar(1);
            }
        }
        return lis;
    }

    private void obtenerDescendientesAux(NodoArbol nodo, Lista lis, Object elem) {
        // Caso base, encuentra el nodo
        if (nodo.getElem().equals(elem)) {
            listarPreordenAux(nodo, lis);
        } else {
            if (nodo.getIzquierdo() != null) {
                obtenerDescendientesAux(nodo.getIzquierdo(), lis, elem);
            }
            if (nodo.getDerecho() != null) {
                obtenerDescendientesAux(nodo.getDerecho(), lis, elem);
            }
        }
    }

    public ArbolBin clonarInvertido() {
        ArbolBin clon = new ArbolBin();
        if (this.raiz != null) {
            clon.raiz = clonarInvertidoAux(this.raiz);
        }
        return clon;
    }

    private NodoArbol clonarInvertidoAux(NodoArbol nodo) {
        NodoArbol nuevoNodo = new NodoArbol(nodo.getElem(), null, null);
        if (nodo.getIzquierdo() != null) {
            nuevoNodo.setDerecho(clonarInvertidoAux(nodo.getIzquierdo()));
        }
        if (nodo.getDerecho() != null) {
            nuevoNodo.setIzquierdo(clonarInvertidoAux(nodo.getDerecho()));
        }
        return nuevoNodo;
    }

    public boolean verificarPatron(Lista l1) {
        boolean verifica = false;
        if (this.raiz.getElem().equals(l1.recuperar(1))) {
            verifica = verificarPatronAux(this.raiz, l1, 2);
        }
        return verifica;
    }

    private boolean verificarPatronAux(NodoArbol nodo, Lista lis, int i) {
        boolean verifica = false;
        if (nodo.getIzquierdo() == null && nodo.getDerecho() == null) {
            verifica = nodo.getElem().equals(lis.recuperar(i-1));
        } else {
            if(nodo.getIzquierdo()!=null){
                if(nodo.getIzquierdo().getElem().equals(lis.recuperar(i))){
                    verifica = verifica || verificarPatronAux(nodo.getIzquierdo(), lis, i+1);
                }
            }
            if(nodo.getDerecho()!=null){
                if(nodo.getDerecho().getElem().equals(lis.recuperar(i))){
                    verifica = verifica || verificarPatronAux(nodo.getDerecho(), lis, i+1);
                }
            }
        }
        return verifica;
    }
}
