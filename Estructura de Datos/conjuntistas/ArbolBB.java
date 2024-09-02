package conjuntistas;

import lineales.dinamicas.Lista;

public class ArbolBB {
    private NodoABB raiz;

    public ArbolBB() {
        this.raiz = null;
    }

    public boolean insertar(Comparable elem) {
        boolean exito = true;
        if (this.raiz == null) {
            this.raiz = new NodoABB(elem);
        } else {
            exito = insertarAux(this.raiz, elem);
        }
        return exito;
    }

    private boolean insertarAux(NodoABB n, Comparable elem) {
        // precondicion: n no es nulo
        boolean exito = true;
        if ((elem.compareTo(n.getElem()) == 0)) {
            // Reporta error: Elemento repetido
            exito = false;
        } else if (elem.compareTo(n.getElem()) < 0) {
            // elemento es menor que el padre
            // Si tiene HI baja a la izquierda, sino agrega elemento
            if (n.getIzquierdo() != null) {
                exito = insertarAux(n.getIzquierdo(), elem);
            } else {
                n.setIzquierdo(new NodoABB(elem));
            }
        } else if (n.getDerecho() != null) {
            // elemento es mayor que el padre
            // Si tiene HD baja a la derecha, sino agrega elemento
            exito = insertarAux(n.getDerecho(), elem);
        } else {
            n.setDerecho(new NodoABB(elem));
        }
        return exito;
    }

    public boolean eliminar(Comparable elemento) {
        return eliminarAux(this.raiz, null, elemento);
    }

    private boolean eliminarAux(NodoABB n, NodoABB padre, Comparable elemento) {
        // Si el elemento a eliminar es la raiz, el padre va a ser nulo
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
        return exito;
    }

    private void eliminarCasoUno(NodoABB n, NodoABB padre) {
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

    private void eliminarCasoDos(NodoABB n, NodoABB padre) {
        NodoABB hijo;
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

    private void eliminarCasoTres(NodoABB n) {
        // El candidato es el menor elemento del HD
        NodoABB padreCandidato = n;
        NodoABB candidato = n.getDerecho();
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

    public boolean pertenece(Comparable elemento) {
        return perteneceAux(this.raiz, elemento);
    }

    private boolean perteneceAux(NodoABB n, Comparable elemento) {
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

    public boolean esVacia() {
        return this.raiz == null;
    }

    public void vaciar() {
        this.raiz = null;
    }

    public Comparable minimoElemento() {
        Comparable minimo = null;
        if (this.raiz != null) {
            minimo = minimoElementoAux(this.raiz);
        }
        return minimo;
    }

    private Comparable minimoElementoAux(NodoABB n) {
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

    private Comparable maximoElementoAux(NodoABB n) {
        Comparable elem;
        if (n.getDerecho() == null) {
            elem = n.getElem();
        } else {
            elem = maximoElementoAux(n.getDerecho());
        }
        return elem;
    }

    public Lista listar() {
        Lista lis = new Lista();
        if (this.raiz != null) {
            listarAux(this.raiz, lis);
        }
        return lis;
    }

    private void listarAux(NodoABB n, Lista lis) {
        if (n != null) {
            listarAux(n.getIzquierdo(), lis);
            lis.insertar(n.getElem(), lis.longitud() + 1);
            listarAux(n.getDerecho(), lis);
        }
    }

    public Lista listarRango(Comparable min, Comparable max) {
        Lista lis = new Lista();
        if (this.raiz != null) {
            listarRangoAux(this.raiz, min, max, lis);
        }
        return lis;
    }

    private void listarRangoAux(NodoABB n, Comparable min, Comparable max, Lista lis) {
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

    public String toString() {
        String cadena = "";
        if (this.raiz != null) {
            cadena = toStringAux(this.raiz);
        } else {
            cadena = "El arbol está vacio";
        }
        return cadena;
    }

    private String toStringAux(NodoABB nodo) {
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

    public boolean eliminarMinimo() {
        boolean exito = false;
        if (this.raiz != null) {
            NodoABB hijoDer = this.raiz.getDerecho();
            NodoABB hijoIzq = this.raiz.getIzquierdo();
            if (hijoIzq != null) {
                if (this.raiz.getElem().compareTo(hijoIzq.getElem()) > 0) {
                    eliminarMinimoIzq(hijoIzq, this.raiz);
                } else if (hijoDer == null) {
                    this.raiz = hijoIzq;
                }
            }
            if (hijoDer != null) {
                if (this.raiz.getElem().compareTo(hijoDer.getElem()) > 0) {
                    eliminarMinimoDer(hijoDer, this.raiz);
                } else if (hijoIzq == null) {
                    this.raiz = hijoDer;
                }
            } else {
                this.raiz = null;
            }
            exito = true;
        }
        return exito;
    }

    private void eliminarMinimoIzq(NodoABB hijo, NodoABB padre) {
        if (hijo.getIzquierdo() != null) {
            eliminarMinimoIzq(hijo.getIzquierdo(), hijo);
        } else {
            padre.setIzquierdo(null);
        }
    }

    private void eliminarMinimoDer(NodoABB hijo, NodoABB padre) {
        if (hijo.getDerecho() != null) {
            eliminarMinimoIzq(hijo.getDerecho(), hijo);
        } else {
            padre.setDerecho(null);
        }
    }

    public ArbolBB clonarParteInvertida(Comparable elem) {
        ArbolBB clonInvertido = new ArbolBB();
        if (this.raiz != null) {
            NodoABB nodoBuscado = buscarNodo(this.raiz, elem);
            if (nodoBuscado != null) {
                clonInvertido.raiz = clonarParteInvertidaAux(nodoBuscado);
            }
        }
        return clonInvertido;
    }

    private NodoABB buscarNodo(NodoABB nodo, Comparable elem) {
        NodoABB buscado = null;
        if (nodo != null) {
            if (nodo.getElem().compareTo(elem) == 0) {
                buscado = nodo;
            } else if (nodo.getElem().compareTo(elem) < 0) {
                buscado = buscarNodo(nodo.getIzquierdo(), elem);
            } else {
                buscado = buscarNodo(nodo.getDerecho(), elem);
            }
        }
        return buscado;
    }

    private NodoABB clonarParteInvertidaAux(NodoABB nodo) {
        NodoABB nuevoNodo = new NodoABB(nodo.getElem());
        if (nodo.getIzquierdo() != null) {
            nuevoNodo.setDerecho(clonarParteInvertidaAux(nodo.getIzquierdo()));
        }
        if (nodo.getDerecho() != null) {
            nuevoNodo.setIzquierdo(clonarParteInvertidaAux(nodo.getDerecho()));
        }
        return nuevoNodo;
    }

    public boolean eliminarElementoAnterior(Comparable elem) {
        return eliminarElemAnteriorAux(this.raiz, elem);
    }

    private boolean eliminarElemAnteriorAux(NodoABB nodo, Comparable elem) {
        boolean verifica = false;
        if (nodo != null) {
            if (nodo.getElem().compareTo(elem) == 0) {
                if (nodo.getIzquierdo() != null) {
                    if (nodo.getIzquierdo().getDerecho() == null) {
                        nodo.setIzquierdo(nodo.getIzquierdo().getIzquierdo());
                    } else {
                        eliminarMayor(nodo.getIzquierdo(), nodo.getIzquierdo().getDerecho());
                    }
                    verifica = true;
                }
            } else if (nodo.getElem().compareTo(elem) > 0) {
                verifica = eliminarElemAnteriorAux(nodo.getIzquierdo(), elem);
            } else {
                verifica = eliminarElemAnteriorAux(nodo.getDerecho(), elem);
            }
        }
        return verifica;
    }

    private void eliminarMayor(NodoABB padre, NodoABB hijo) {
        if (hijo.getDerecho() != null) {
            eliminarMayor(hijo, hijo.getDerecho());
        } else {
            padre.setDerecho(null);
        }
    }

    public Lista listarMayoresIguales(Comparable elem){
        Lista lis = new Lista();
        if(this.raiz != null){
            listarMayoresIgualesAux(this.raiz, lis, elem);
        }
        return lis;
    }

    private void listarMayoresIgualesAux(NodoABB nodo, Lista lis, Comparable elem){
        if(nodo!=null){
            listarMayoresIgualesAux(nodo.getDerecho(), lis, elem);
            if(nodo.getElem().compareTo(elem)>=0){
                lis.insertar(nodo.getElem(), lis.longitud()+1);
            }
            if(nodo.getElem().compareTo(elem)>0){
                listarMayoresIgualesAux(nodo.getIzquierdo(), lis, elem);
            }
        }
    }

    public Lista listarMenores(Comparable elem){
        Lista lis = new Lista();
        if(this.raiz != null){
            listarMenoresAux(this.raiz, lis, elem);
        }
        return lis;
    }

    private void listarMenoresAux(NodoABB nodo, Lista lis, Comparable elem){
        if(nodo != null){
            listarMenoresAux(nodo.getIzquierdo(), lis, elem);
            if(nodo.getElem().compareTo(elem) < 0){
                lis.insertar(nodo.getElem(), lis.longitud()+1);
                listarMenoresAux(nodo.getDerecho(), lis, elem);
            }
        }
    }
}
