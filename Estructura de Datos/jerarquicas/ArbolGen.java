package jerarquicas;

import lineales.dinamicas.Cola;
import lineales.dinamicas.Lista;

public class ArbolGen {
    // atributos
    private NodoGen raiz;

    // constructor
    public ArbolGen() {
        this.raiz = null;
    }

    public boolean insertar(Object elemNuevo, Object elemPadre) {
        boolean exito = true;
        // En el caso que el arbol esté vacio, asigno la raiz
        if (this.raiz == null) {
            raiz = new NodoGen(elemNuevo, null, null);
        } else {
            // Busco el nodo padre dentro del Arbol
            NodoGen nPadre = obtenerNodo(this.raiz, elemPadre);
            if (nPadre != null) {
                // Si se encontro el padre, inserto en el hijo izquierdo si es que no tiene
                // hijos;
                if (nPadre.getHijoIzquierdo() == null) {
                    nPadre.setHijoIzquierdo(new NodoGen(elemNuevo, null, null));
                } else {
                    NodoGen hijo = nPadre.getHijoIzquierdo();
                    boolean corte = false;
                    // Si tiene hijo izquierdo recorro por sus hermanos
                    while (!corte && hijo != null) {
                        // Cuando llego al 'ultimo' hermano inserto el nuevo elemento
                        if (hijo.getHermanoDerecho() == null) {
                            hijo.setHermanoDerecho(new NodoGen(elemNuevo, null, null));
                            corte = true;
                        }
                        hijo = hijo.getHermanoDerecho();
                    }
                }
            } else {
                exito = false;
            }

        }
        return exito;
    }

    private NodoGen obtenerNodo(NodoGen n, Object elemBuscado) {
        // Busco el nodo que se necesite
        NodoGen resultado = null;
        if (n != null) {
            // si me encuentro en el nodo buscado, lo devuelvo
            if (n.getElem().equals(elemBuscado)) {
                resultado = n;
            } else {
                // recorro recursivamente los hijos y luego iterativamente a los hermanos de los
                // hijos
                NodoGen hijo = n.getHijoIzquierdo();
                while (hijo != null && resultado == null) { // En cuanto encuentre el nodo corta la repetitiva
                    resultado = obtenerNodo(hijo, elemBuscado);
                    hijo = hijo.getHermanoDerecho();
                }
            }
        }
        return resultado;
    }

    public boolean insertarPorPosicion(Object elem, int posPadre) {
        boolean verifica = true;
        // Si el arbol esta vacio, no importa la pos ingresada, asigno al elemento como
        // raiz
        if (this.raiz == null) {
            this.raiz = new NodoGen(elem, null, null);
        } else {
            Lista lis = listarPreorden();
            // Verifico que la posPadre sea correcta
            if (lis.recuperar(posPadre) != null) {
                NodoGen nPadre = obtenerNodo(this.raiz, lis.recuperar(posPadre));// Obtengo el nodo
                if (nPadre.getHijoIzquierdo() == null) {// Si no tiene hijo izquierdo lo asigno
                    nPadre.setHijoIzquierdo(new NodoGen(elem, null, null));
                } else {
                    NodoGen hijo = nPadre.getHijoIzquierdo();
                    boolean corte = false;
                    // Si tiene hijo izquierdo recorro por sus hermanos
                    while (!corte && hijo != null) {
                        // Cuando llego al 'ultimo' hermano inserto el nuevo elemento
                        if (hijo.getHermanoDerecho() == null) {
                            hijo.setHermanoDerecho(new NodoGen(elem, null, null));
                            corte = true;
                        }
                        hijo = hijo.getHermanoDerecho();
                    }
                }
            } else {
                verifica = false;
            }
        }
        return verifica;
    }

    public boolean pertenece(Object buscado) {
        boolean verifica = false;
        if (this.raiz != null) {
            verifica = perteneceAux(this.raiz, buscado);
        }
        return verifica;
    }

    private boolean perteneceAux(NodoGen n, Object buscado) {
        boolean verifica = false;
        if (n.getElem().equals(buscado)) {
            verifica = true;
        } else {
            NodoGen hijo = n.getHijoIzquierdo();
            while (hijo != null && !verifica) {
                verifica = perteneceAux(hijo, buscado);
                hijo = hijo.getHermanoDerecho();
            }
        }
        return verifica;

    }

    public boolean esVacio() {
        return this.raiz == null;
    }

    public Object padre(Object elem) {
        return padreAux(this.raiz, elem);
    }

    private Object padreAux(NodoGen n, Object elem) {
        Object encontrado = null;
        if (n != null) {
            NodoGen hijo = n.getHijoIzquierdo();
            while (hijo != null && encontrado == null) {
                if (hijo.getElem().equals(elem)) {
                    encontrado = n.getElem();
                }
                hijo = hijo.getHermanoDerecho();
            }
            if (encontrado == null) {
                NodoGen hijo2 = n.getHijoIzquierdo();
                while (hijo2 != null && encontrado == null) {
                    encontrado = padreAux(hijo2, elem);
                    hijo2 = hijo2.getHermanoDerecho();
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

    private int alturaAux(NodoGen n) {
        int altura = 0;
        if (n != null) {
            NodoGen hijo = n.getHijoIzquierdo();
            while (hijo != null) {
                int alturaHijo = alturaAux(hijo) + 1;
                if (alturaHijo > altura) {
                    altura = alturaHijo;
                }
                hijo = hijo.getHermanoDerecho();
            }
        }
        return altura;
    }

    public int nivel(Object buscado) {
        int nivel = -1;
        if (this.raiz != null) {
            nivel = nivelAux(buscado, this.raiz, 0);
        }
        return nivel;
    }

    private int nivelAux(Object elem, NodoGen n, int nivel) {
        int verifica = -1;
        if (n.getElem().equals(elem)) {
            verifica = nivel;
        } else {
            NodoGen hijo = n.getHijoIzquierdo();
            while (hijo != null && verifica == -1) {
                verifica = nivelAux(elem, hijo, nivel + 1);
                hijo = hijo.getHermanoDerecho();
            }
        }
        return verifica;
    }

    public Lista ancestros(Object elem) {
        Lista lis = new Lista();
        if (this.raiz != null) {
            ancestrosAux(this.raiz, lis, elem);
            if (!lis.esVacia()) {
                lis.eliminar(1);
            }
        }
        return lis;
    }

    private void ancestrosAux(NodoGen n, Lista lis, Object elem) {
        if (n.getElem().equals(elem)) {
            lis.insertar(elem, 1);
        } else {
            NodoGen hijo = n.getHijoIzquierdo();
            while (hijo != null && lis.esVacia()) {
                ancestrosAux(hijo, lis, elem);
                hijo = hijo.getHermanoDerecho();
            }
            if (!lis.esVacia()) {
                lis.insertar(n.getElem(), lis.longitud() + 1);
            }
        }
    }

    public ArbolGen clone() {
        ArbolGen nuevo = new ArbolGen();
        if (this.raiz != null) {
            nuevo.raiz = cloneAux(this.raiz);
        }
        return nuevo;
    }

    private NodoGen cloneAux(NodoGen nodo) {
        NodoGen nuevoNodo = new NodoGen(nodo.getElem(), null, null);
        if (nodo.getHijoIzquierdo() != null) {
            nuevoNodo.setHijoIzquierdo(cloneAux(nodo.getHijoIzquierdo()));
        }
        if (nodo.getHermanoDerecho() != null) {
            nuevoNodo.setHermanoDerecho(cloneAux(nodo.getHermanoDerecho()));
        }
        return nuevoNodo;
    }

    public void vaciar() {
        this.raiz = null;
    }

    public Lista listarPreorden() {
        Lista salida = new Lista();
        listarPreordenAux(this.raiz, salida);
        return salida;
    }

    private void listarPreordenAux(NodoGen n, Lista lis) {
        if (n != null) {
            // visita del nodo n
            lis.insertar(n.getElem(), lis.longitud() + 1);

            // llamados recursivos con los hijos de n
            NodoGen hijo = n.getHijoIzquierdo();
            while (hijo != null) {
                listarPreordenAux(hijo, lis);
                hijo = hijo.getHermanoDerecho();
            }
        }
    }

    public String toString() {
        return toStringAux(this.raiz);
    }

    private String toStringAux(NodoGen n) {
        String s = "";
        if (n != null) {
            // visita el nodo n
            s += n.getElem().toString() + "->";
            NodoGen hijo = n.getHijoIzquierdo();
            while (hijo != null) {
                s += hijo.getElem().toString() + ", ";
                hijo = hijo.getHermanoDerecho();
            }
            // comienza recorrido de los hijos de n llamando recursivamente
            // para que cada hijo agregue su subcadena a la general
            hijo = n.getHijoIzquierdo();
            while (hijo != null) {
                s += "\n" + toStringAux(hijo);
                hijo = hijo.getHermanoDerecho();
            }
        }
        return s;
    }

    public Lista listarInorden() {
        Lista salida = new Lista();
        listarInordenAux(this.raiz, salida);
        return salida;
    }

    private void listarInordenAux(NodoGen n, Lista ls) {
        if (n != null) {
            // llamado recursivo con primer hijo de n
            if (n.getHijoIzquierdo() != null) {
                listarInordenAux(n.getHijoIzquierdo(), ls);
            }

            // visita del nodo n
            ls.insertar(n.getElem(), ls.longitud() + 1);

            // llamados recursivos con los otros hijos de n
            if (n.getHijoIzquierdo() != null) {
                NodoGen hijo = n.getHijoIzquierdo().getHermanoDerecho();
                while (hijo != null) {
                    listarInordenAux(hijo, ls);
                    hijo = hijo.getHermanoDerecho();
                }
            }
        }
    }

    public Lista listarPosorden() {
        Lista salida = new Lista();
        listarPosordenAux(this.raiz, salida);
        return salida;
    }

    private void listarPosordenAux(NodoGen n, Lista lis) {
        if (n != null) {
            NodoGen hijo = n.getHijoIzquierdo();
            while (hijo != null) {
                listarPosordenAux(hijo, lis);
                hijo = hijo.getHermanoDerecho();
            }
            lis.insertar(n.getElem(), lis.longitud() + 1);
        }
    }

    public Lista listarPorNiveles() {
        Cola cola = new Cola();
        Lista lista = new Lista();
        cola.poner(this.raiz);
        while (!cola.esVacia()) {
            NodoGen nodo = (NodoGen) cola.obtenerFrente();
            cola.sacar();
            lista.insertar(nodo.getElem(), lista.longitud() + 1);
            nodo = nodo.getHijoIzquierdo();
            while (nodo != null) {
                cola.poner(nodo);
                nodo = nodo.getHermanoDerecho();
            }
        }
        return lista;
    }

    public boolean verificarCamino(Lista camino) {
        // Entra una lista con un camino y verifica que sea correcta
        boolean verifica = verificarCaminoAux(this.raiz, 1, camino, camino.longitud());
        // Caso especial: arbol y lista vacios
        if (this.raiz == null && camino.esVacia()) {
            verifica = true;
        }
        return verifica;
    }

    private boolean verificarCaminoAux(NodoGen nodo, int pos, Lista camino, int longitud) {
        boolean verifica = false;
        if (nodo != null) {
            if (pos == longitud) {// Caso base es el ultimo elemento del camino
                verifica = nodo.getElem().equals(camino.recuperar(pos));
            } else if (pos < longitud) {// Paso recursivo
                if (nodo.getElem().equals(camino.recuperar(pos))) {
                    // Si el elemento de la pos existe, verifica que alguno de sus hijos esté en
                    // pos+1 en la lista
                    NodoGen hijo = nodo.getHijoIzquierdo();
                    pos++;
                    while (hijo != null && !verifica) {
                        verifica = verificarCaminoAux(hijo, pos, camino, longitud);
                        hijo = hijo.getHermanoDerecho();
                    }
                }
            }
        }
        return verifica;
    }

    public Lista listarEntreNiveles(int niv1, int niv2) {
        Lista listaNiveles = new Lista();
        if (this.raiz != null) {
            listarEntreNivelesAux(this.raiz, listaNiveles, niv1, niv2, 0);
        }
        return listaNiveles;
    }

    private void listarEntreNivelesAux(NodoGen nodo, Lista lis, int niv1, int niv2, int nivelActual) {
        if (nivelActual <= niv2) {
            if (nivelActual >= niv1) {
                lis.insertar(nodo.getElem(), lis.longitud() + 1);
            }
            NodoGen hijo = nodo.getHijoIzquierdo();
            nivelActual++;
            while (hijo != null && nivelActual <= niv2) {
                listarEntreNivelesAux(hijo, lis, niv1, niv2, nivelActual);
                hijo = hijo.getHermanoDerecho();
            }
        }
    }

    public boolean eliminar(Object elem) {
        boolean verificar = false;
        if (this.raiz != null) {
            if (this.raiz.equals(elem)) {
                this.raiz = null;
                verificar = true;
            } else {
                verificar = eliminarAux(this.raiz, elem);
            }
        }
        return verificar;
    }

    private boolean eliminarAux(NodoGen nodo, Object elem) {
        boolean comprueba = false;
        NodoGen hijo = nodo.getHijoIzquierdo();
        if (hijo != null) {
            if (hijo.getElem().equals(elem)) {
                nodo.setHijoIzquierdo(nodo.getHijoIzquierdo().getHermanoDerecho());
                comprueba = true;
            }
        }
        if (nodo.getHermanoDerecho() != null) {
            if (nodo.getHermanoDerecho().getElem().equals(elem)) {
                nodo.setHermanoDerecho(nodo.getHermanoDerecho().getHermanoDerecho());
                comprueba = true;
            }
        }
        while (hijo != null && !comprueba) {
            comprueba = eliminarAux(hijo, elem);
            hijo = hijo.getHermanoDerecho();
        }
        return comprueba;
    }

    public boolean esSobrino(Object tio, Object sobrino) {
        boolean verifica = false;
        if (this.raiz != null) {
            verifica = esSobrinoAux(this.raiz, tio, sobrino);
        }
        return verifica;
    }

    private boolean esSobrinoAux(NodoGen n, Object tio, Object sobrino) {
        boolean verifica = false;
        if (n.getElem().equals(tio)) {
            NodoGen hermano = n.getHermanoDerecho();
            while (hermano != null && !verifica) {
                NodoGen hijoHer = hermano.getHijoIzquierdo();
                while (hijoHer != null && !verifica) {
                    verifica = hijoHer.getElem().equals(sobrino);
                    hijoHer = hijoHer.getHermanoDerecho();
                }
                hermano = hermano.getHermanoDerecho();
            }
        } else {
            NodoGen hijo = n.getHijoIzquierdo();
            while (hijo != null && !verifica) {
                verifica = esSobrinoAux(hijo, tio, sobrino);
                hijo = hijo.getHermanoDerecho();
            }
        }

        return verifica;
    }

    public Lista listarHastaNivel(int niv){
        Lista lis = new Lista();
        if(this.raiz != null && niv>=0){
            listarHastaNivelAux(this.raiz, niv, 0, lis);
        }
        return lis;
    }

    private void listarHastaNivelAux(NodoGen nodo, int niv, int nivActual, Lista lis){
        if(nivActual <= niv){
            lis.insertar(nodo.getElem(), lis.longitud()+1);
        }
        NodoGen hijo = nodo.getHijoIzquierdo();
        nivActual++;
        while (hijo != null && nivActual <= niv) {
            listarHastaNivelAux(hijo, niv, nivActual, lis);
            hijo = hijo.getHermanoDerecho();
        }
    }
}
