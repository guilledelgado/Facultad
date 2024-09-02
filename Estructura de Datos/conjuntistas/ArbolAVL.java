package conjuntistas;

import lineales.dinamicas.Lista;
import lineales.dinamicas.Pila;

public class ArbolAVL {
    private NodoAVL raiz;

    public ArbolAVL() {
        raiz = null;
    }

    public boolean pertenece(Comparable elemento) {
        return perteneceAux(this.raiz, elemento);
    }

    private boolean perteneceAux(NodoAVL n, Comparable elemento) {
        boolean exito = false;
        if (n != null) {
            if (elemento.compareTo(n.getElem()) == 0) {
                exito = true;
            } else if (elemento.compareTo(n.getElem()) < 0) {
                exito = perteneceAux(n.getIzquierdo(), elemento);
            } else {
                exito = perteneceAux(n.getDerecho(), elemento);
            }
        }
        return exito;
    }

    public boolean insertar(Comparable elem) {

        boolean exito = true;
        if (this.raiz == null) {
            this.raiz = new NodoAVL(elem, null, null);
        } else {
            if (this.raiz.getElem().compareTo(elem) != 0) {
                if (this.raiz.getElem().compareTo(elem) > 0) {
                    exito = insertarAux(elem, raiz, raiz.getIzquierdo());
                } else {
                    exito = insertarAux(elem, raiz, raiz.getDerecho());
                }
            }
        }
        if (exito) {
            this.raiz.recalcularAltura();
            realizarBalanceRaiz(raiz);
        }
        return exito;
    }

    private boolean insertarAux(Comparable elem, NodoAVL padre, NodoAVL nodo) {
        boolean exito = true;
        if (nodo != null) {
            if (elem.compareTo(nodo.getElem()) == 0) {
                // Si el elemento ya existe en el arbol, devuelve false
                exito = false;
            } else if (elem.compareTo(nodo.getElem()) < 0) {
                exito = insertarAux(elem, nodo, nodo.getIzquierdo());
            } else {
                exito = insertarAux(elem, nodo, nodo.getDerecho());
            }
        } else {
            nodo = new NodoAVL(elem, null, null);
            if (padre.getElem().compareTo(elem) > 0) {
                padre.setIzquierdo(nodo);
            } else {
                padre.setDerecho(nodo);
            }
        }
        // Despues de insertar verifico los balances
        if (exito) {
            nodo.recalcularAltura();
            realizarBalance(padre, nodo);
        }
        return exito;
    }

    private void realizarBalance(NodoAVL padre, NodoAVL pivote) {
        int balance = calcularBalance(pivote);
        if (balance > 1) {
            int balanceIzq = calcularBalance(pivote.getIzquierdo());
            if (balanceIzq >= 0) {
                if (pivote == padre.getIzquierdo()) {
                    padre.setIzquierdo(rotarDerecha(pivote));
                } else {
                    padre.setDerecho(rotarDerecha(pivote));
                }
            } else {
                if (pivote == padre.getIzquierdo()) {
                    padre.setIzquierdo(rotacionDobleIzquierdaDerecha(pivote));
                } else {
                    padre.setDerecho(rotacionDobleIzquierdaDerecha(pivote));
                }

            }

        } else if (balance < -1) {
            int balanceDer = calcularBalance(pivote.getDerecho());
            if (balanceDer <= 0) {
                if (pivote == padre.getIzquierdo()) {
                    padre.setIzquierdo(rotarIzquierda(pivote));
                } else {
                    padre.setDerecho(rotarIzquierda(pivote));
                }

            } else {
                if (pivote == padre.getIzquierdo()) {
                    padre.setIzquierdo(rotacionDobleDerechaIzquierda(pivote));
                } else {
                    padre.setDerecho(rotacionDobleDerechaIzquierda(pivote));
                }

            }
        }
        padre.recalcularAltura();
    }

    private NodoAVL rotacionDobleDerechaIzquierda(NodoAVL padre) {
        NodoAVL hijoDerecha = rotarDerecha(padre.getDerecho());
        padre.setDerecho(hijoDerecha);
        NodoAVL nuevoPadre = rotarIzquierda(padre);
        padre.recalcularAltura();
        nuevoPadre.recalcularAltura();
        return nuevoPadre;
    }

    private NodoAVL rotacionDobleIzquierdaDerecha(NodoAVL padre) {
        NodoAVL hijoIzquierdo = rotarIzquierda(padre.getIzquierdo());
        padre.setIzquierdo(hijoIzquierdo);
        NodoAVL nuevoPadre = rotarDerecha(padre);
        padre.recalcularAltura();
        nuevoPadre.recalcularAltura();
        return nuevoPadre;
    }

    private void realizarBalanceRaiz(NodoAVL nodo) {
        int balance = calcularBalance(nodo);

        if (balance > 1) {
            int balanceIzq = calcularBalance(nodo.getIzquierdo());
            if (balanceIzq >= 0) {
                this.raiz = rotarDerecha(nodo);
            } else {
                this.rotacionDobleIzquierdaDerecha(nodo);
            }
        } else if (balance < -1) {
            int balanceDer = calcularBalance(nodo.getDerecho());
            if (balanceDer <= 0) {
                this.raiz = rotarIzquierda(nodo);
            } else {
                this.raiz = rotacionDobleDerechaIzquierda(nodo);
            }

        }
        nodo.recalcularAltura();
        raiz.recalcularAltura();
    }

    private int calcularBalance(NodoAVL nodo) {
        int alturaHI = -1, alturaHD = -1;
        if (nodo.getIzquierdo() != null) {
            alturaHI = nodo.getIzquierdo().getAltura();
        }
        if (nodo.getDerecho() != null) {
            alturaHD = nodo.getDerecho().getAltura();
        }
        return alturaHI - alturaHD;
    }

    private NodoAVL rotarDerecha(NodoAVL nodo) {
        // Apunto al hijo izquierdo del pivote
        NodoAVL hijoIzq = nodo.getIzquierdo();
        // Apunto al hijo Derecho del Hijo Izquierdo del pivote
        NodoAVL temp = null;
        if (hijoIzq.getDerecho() != null) {
            temp = hijoIzq.getDerecho();
        }
        // El nodo ahora es el hijo derecho de su hijo izquierdo
        hijoIzq.setDerecho(nodo);
        // Ahora el hijo izquierdo es el HD del que era su HI
        nodo.setIzquierdo(temp);
        // Recalculo sus alturas
        nodo.recalcularAltura();
        hijoIzq.recalcularAltura();
        return hijoIzq;
    }

    private NodoAVL rotarIzquierda(NodoAVL nodo) {
        // Guardo el HD del pivote
        NodoAVL hijoDer = nodo.getDerecho();
        // Guardo el HI del hijoDer en una variable temporal
        NodoAVL temp = null;
        if (hijoDer.getIzquierdo() != null) {
            temp = hijoDer.getIzquierdo();
        }
        // El pivote ahora es el HI de su hijoDer
        hijoDer.setIzquierdo(nodo);
        // Ahora el HD del pivote es el subarbol guardado en la variable temporal
        nodo.setDerecho(temp);
        // Recalculo las alturas
        nodo.recalcularAltura();
        hijoDer.recalcularAltura();
        return hijoDer;
    }

    public boolean eliminar(Comparable elemento) {
        boolean exito = false;
        if (this.raiz.getElem().compareTo(elemento) == 0) {
            exito = true;
            if (this.raiz.getIzquierdo() != null && this.raiz.getDerecho() != null) {
                eliminarCasoTres(this.raiz);
            } else if ((this.raiz.getIzquierdo() != null && this.raiz.getDerecho() == null)
                    || (this.raiz.getIzquierdo() == null && this.raiz.getDerecho() != null)) {
                eliminarCasoDos(this.raiz, null);
            } else {
                eliminarCasoUno(this.raiz, null);
            }
        } else if (this.raiz.getElem().compareTo(elemento) > 0) {
            exito = eliminarAux(this.raiz.getIzquierdo(), raiz, elemento);
        } else {
            exito = eliminarAux(this.raiz.getDerecho(), this.raiz, elemento);
        }
        if (exito) {
            this.raiz.recalcularAltura();
            realizarBalanceRaiz(raiz);
        }
        return exito;
    }

    private boolean eliminarAux(NodoAVL n, NodoAVL padre, Comparable elemento) {
        boolean exito = false;
        if (n != null) {
            if (elemento.compareTo(n.getElem()) == 0) {
                exito = true;
                if (n.getIzquierdo() != null && n.getDerecho() != null) {
                    eliminarCasoTres(n);
                } else if ((n.getIzquierdo() != null && n.getDerecho() == null)
                        || (n.getIzquierdo() == null && n.getDerecho() != null)) {
                    eliminarCasoDos(n, padre);
                } else {
                    eliminarCasoUno(n, padre);
                }
            } else if (elemento.compareTo(n.getElem()) < 0) {
                exito = eliminarAux(n.getIzquierdo(), n, elemento);
            } else {
                exito = eliminarAux(n.getDerecho(), n, elemento);
            }
        }
        if (exito) {
            n.recalcularAltura();
            realizarBalance(padre, n);
        }
        return exito;
    }

    private void eliminarCasoUno(NodoAVL n, NodoAVL padre) {
        if (padre != null) {
            if (n == padre.getIzquierdo()) {
                padre.setIzquierdo(null);
            } else {
                padre.setDerecho(null);
            }

        } else {
            this.raiz = null;
        }
    }

    private void eliminarCasoDos(NodoAVL n, NodoAVL padre) {
        NodoAVL hijo;
        if (n.getIzquierdo() != null) {
            hijo = n.getIzquierdo();
        } else {
            hijo = n.getDerecho();
        }
        if (padre != null) {
            if (n == padre.getIzquierdo()) {
                padre.setIzquierdo(hijo);
            } else {
                padre.setDerecho(hijo);
            }
        } else {
            this.raiz = hijo;
        }
    }

    private void eliminarCasoTres(NodoAVL n) {
        //Corregir, no calcula el balance de los nodos visitados
        // El candidato es el menor elemento del HD
        NodoAVL padreCandidato = n;
        NodoAVL candidato = n.getDerecho();
        while (candidato.getIzquierdo() != null) {
            padreCandidato = candidato;
            candidato = candidato.getIzquierdo();
        }
        if (padreCandidato == n) {
            n.setElem(candidato.getElem());
            n.setDerecho(candidato.getDerecho());
        } else {
            n.setElem(candidato.getElem());
            padreCandidato.setIzquierdo(candidato.getDerecho());
        }

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

    private String toStringAux(NodoAVL nodo) {
        String dato = "";
        if (nodo != null) {
            dato = nodo.getElem().toString() + " altura: " + nodo.getAltura() + "\t|";
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

    public Lista listarRango(Comparable min, Comparable max) {
        Lista lis = new Lista();
        if (this.raiz != null) {
            listarRangoAux(this.raiz, min, max, lis);
        }
        return lis;
    }

    private void listarRangoAux(NodoAVL n, Comparable min, Comparable max, Lista lis) {
        if (n != null) {
            Comparable elemento = n.getElem();
            if (elemento.compareTo(min) >= 0) {
                listarRangoAux(n.getIzquierdo(), min, max, lis);
            }
            if (elemento.compareTo(min) >= 0 && elemento.compareTo(max) <= 0) {
                lis.insertar(n.getElem(), lis.longitud() + 1);
            }
            if (elemento.compareTo(max) <= 0) {
                listarRangoAux(n.getDerecho(), min, max, lis);
            }
        }
    }

    public Lista listar() {
        Lista lis = new Lista();
        if (this.raiz != null) {
            listarAux(this.raiz, lis);
        }
        return lis;
    }

    private void listarAux(NodoAVL n, Lista lis) {
        if (n != null) {
            listarAux(n.getIzquierdo(), lis);
            lis.insertar(n.getElem(), lis.longitud() + 1);
            listarAux(n.getDerecho(), lis);
        }
    }

    public Pila apilar() {
        Pila pila = new Pila();
        if (this.raiz != null) {
            apilarAux(this.raiz, pila);
        }
        return pila;
    }

    private void apilarAux(NodoAVL n, Pila pila) {
        if(n != null){
            apilarAux(n.getIzquierdo(), pila);
            pila.apilar(n.getElem());
            apilarAux(n.getDerecho(), pila);
        }
    }

    public Comparable minimoElemento() {
        Comparable minimo = null;
        if (this.raiz != null) {
            minimo = minimoElementoAux(this.raiz);
        }
        return minimo;
    }

    private Comparable minimoElementoAux(NodoAVL n) {
        Comparable elem;
        if (n.getIzquierdo() == null) {
            elem = n.getElem();
        } else {
            elem = minimoElementoAux(n.getIzquierdo());
        }
        return elem;
    }

    public Comparable maximoElemento() {
        Comparable maximo = null;
        if (this.raiz != null) {
            maximo = maximoElementoAux(this.raiz);
        }
        return maximo;
    }

    private Comparable maximoElementoAux(NodoAVL n) {
        Comparable elem;
        if (n.getDerecho() == null) {
            elem = n.getElem();
        } else {
            elem = maximoElementoAux(n.getDerecho());
        }
        return elem;
    }

    public Comparable buscarNodo(Comparable elem) {
        // Busca y devuelve el elemento del nodo que se busca
        NodoAVL buscado = buscarNodoAux(this.raiz, elem);

        Comparable elemento = null;
        if (buscado != null) {
            elemento = buscado.getElem();
        }
        return elemento;
    }

    private NodoAVL buscarNodoAux(NodoAVL nodo, Comparable elem) {
        NodoAVL buscado = null;
        if (nodo != null) {
            if (elem.compareTo(nodo.getElem()) == 0) {
                buscado = nodo;
            } else if (elem.compareTo(nodo.getElem()) < 0) {
                buscado = buscarNodoAux(nodo.getIzquierdo(), elem);
            } else {
                buscado = buscarNodoAux(nodo.getDerecho(), elem);
            }
        }
        return buscado;
    }
}
