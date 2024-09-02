package grafos;

import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.PriorityQueue;

import lineales.dinamicas.Lista;
import tests.usoEspecifico.ColaPrioridad;

public class GrafoEtiquetado {
    // Atributo
    private NodoVert inicio;

    // Constructor
    public GrafoEtiquetado() {
        this.inicio = null;
    }

    // Metodos
    public boolean insertarVertice(Object nuevoVertice) {
        // Verifica que el vertice no exista en el grafo
        boolean exito = false;
        NodoVert aux = this.ubicarVertice(nuevoVertice);
        if (aux == null) {
            this.inicio = new NodoVert(nuevoVertice, this.inicio);
            exito = true;
        }
        return exito;
    }

    public Object encontrarElemento(Object elemento) {
        NodoVert vertice = ubicarVertice(elemento);
        Object elem = null;
        if (vertice != null) {
            elem = ubicarVertice(elemento).getElemento();

        }
        return elem;
    }

    private NodoVert ubicarVertice(Object buscado) {
        // Recorre la lista de vértices y devuelve el vertice buscado
        NodoVert aux = this.inicio;
        if (aux != null) {
            // Hace la recurcion hasta que encuentre un null (es decir, no se encuentra)
            // O hasta que se encuentre el vertice deseado
            while (aux != null && !aux.getElemento().equals(buscado)) {
                aux = aux.getSigVertice();
            }
        }

        return aux;
    }

    public boolean eliminarVertice(Object buscado) {
        boolean exito = false;
        NodoAdy auxAdy;
        NodoVert aux = this.inicio, aux2 = aux.getSigVertice();
        if (aux.getElemento().equals(buscado)) {
            auxAdy = aux.getPrimerAdy();
            while (auxAdy != null) {
                this.eliminarArcoAux(auxAdy.getVertice(), buscado);
                auxAdy = auxAdy.getSigAdyacente();
            }
            this.inicio = aux2;
            exito = true;
        } else {
            while (aux2 != null && !exito) {
                exito = aux2.getElemento().equals(buscado);
                if (!exito) {
                    aux = aux2;
                    aux2 = aux2.getSigVertice();
                }
            }
            if (exito) {
                auxAdy = aux2.getPrimerAdy();
                while (auxAdy != null) {
                    this.eliminarArcoAux(auxAdy.getVertice(), buscado);
                    auxAdy = auxAdy.getSigAdyacente();
                }
                aux.setSigVertice(aux2.getSigVertice());
            }
        }
        return exito;
    }

    public boolean insertarArco(Object verticeA, Object verticeB, double etiqueta) {
        boolean exito = false;
        NodoVert aux = this.inicio;
        NodoVert auxO = null, auxD = null;
        // Recorre la lista de vertices y para cuando la recorre por completo
        // o cuando encuentra los dos vertices
        while (aux != null && (auxO == null || auxD == null)) {
            if (aux.getElemento().equals(verticeA)) {
                auxO = aux;
            } else if (aux.getElemento().equals(verticeB)) {
                auxD = aux;
            }
            aux = aux.getSigVertice();
        }
        // Si encuentra los nodos, inserta el arco entre ellos
        if (auxO != null && auxD != null) {
            conectarNodos(auxO, auxD, etiqueta);
            conectarNodos(auxD, auxO, etiqueta);
            exito = true;
        }
        return exito;
    }

    private void conectarNodos(NodoVert nodoOrigen, NodoVert nodoDestino, double etiqueta) {
        NodoAdy aux = nodoOrigen.getPrimerAdy();
        // Si es el primer elemento adyacente lo agrego al nodo vertice
        if (aux == null) {
            nodoOrigen.setPrimerAdy(new NodoAdy(nodoDestino, etiqueta));
        } else {
            // Si ya tiene nodos adyacentes, lo inserto al ultimo nodo adyacente
            while (aux.getSigAdyacente() != null) {
                aux = aux.getSigAdyacente();
            }
            aux.setSigAdyacente(new NodoAdy(nodoDestino, etiqueta));
        }
    }

    public boolean eliminarArco(Object verticeA, Object verticeB) {
        boolean exito = false;
        NodoVert aux = this.inicio;
        NodoVert auxO = null, auxD = null;
        // Recorro los vertices buscando los nodos a eliminar
        while (aux != null && (auxO == null || aux == null)) {
            if (aux.getElemento().equals(verticeA)) {
                auxO = aux;
            } else if (aux.getElemento().equals(verticeB)) {
                auxD = aux;
            }
            aux = aux.getSigVertice();
        }
        // Si los encuentra pruebo eliminando del verticeA(auxO) el verticeB
        if (auxO != null && auxD != null) {
            exito = eliminarArcoAux(auxO, verticeB);
            // Si se eliminó con exito, elimino el verticeA del verticeB)(auxD)
            if (exito) {
                exito = eliminarArcoAux(auxD, verticeA);
            }
        }
        return exito;
    }

    private boolean eliminarArcoAux(NodoVert verticeA, Object verticeB) {
        boolean exito = false;
        NodoAdy auxAdy = verticeA.getPrimerAdy();
        if (auxAdy.getVertice().getElemento().equals(verticeB)) {
            verticeA.setPrimerAdy(auxAdy.getSigAdyacente());
            exito = true;
        } else {
            NodoAdy auxAdy2 = auxAdy.getSigAdyacente();
            while (!exito && auxAdy2 != null) {
                exito = auxAdy2.getVertice().getElemento().equals(verticeB);
                if (exito) {
                    auxAdy.setSigAdyacente(auxAdy2.getSigAdyacente());
                }
                auxAdy = auxAdy2;
                auxAdy2 = auxAdy2.getSigAdyacente();
            }
        }
        return exito;
    }

    public boolean existeVertice(Object buscado) {
        // si el modulo ubicarVertice lo encuentra, es verdadero
        return ubicarVertice(buscado) != null;
    }

    public boolean existeArco(Object verticeA, Object verticeB) {
        // Al ser un grafo en cuanto encuentre el verticeA
        // verifica que el verticeB sea un adyacente
        boolean exito = false;
        NodoVert aux = this.ubicarVertice(verticeA);
        if (aux != null) {
            NodoAdy auxAdy = aux.getPrimerAdy();
            // Itera hasta que encuentre el verticeB o recorra todos los adyacentes
            while (!exito && auxAdy != null) {
                exito = auxAdy.getVertice().getElemento().equals(verticeB);
                auxAdy = auxAdy.getSigAdyacente();
            }
        }
        return exito;
    }

    public boolean esVacio() {
        return this.inicio == null;
    }

    public String toString() {
        String s = "";
        if (this.inicio == null) {
            s = "Grafo vacio";
        } else {
            NodoVert aux = this.inicio;
            while (aux != null) {
                NodoAdy auxAdy = aux.getPrimerAdy();
                s = s + "Vertice: " + aux.getElemento().toString();
                while (auxAdy != null) {
                    s = s + "-----" + auxAdy.getEtiqueta() + "---->" + auxAdy.getVertice().getElemento().toString()
                            + "\t";
                    auxAdy = auxAdy.getSigAdyacente();
                }
                s = s + "\n";
                aux = aux.getSigVertice();
            }
        }
        return s;
    }

    public boolean existeCamino(Object inicio, Object destino) {
        boolean retorno = false;
        NodoVert nodoInicio = this.ubicarVertice(inicio);
        NodoVert nodoDestino = this.ubicarVertice(destino);
        Lista visitados = new Lista();

        if (nodoInicio != null && nodoDestino != null) {
            retorno = existeCaminoAux(nodoInicio, nodoDestino, visitados);
        }
        return retorno;
    }

    private boolean existeCaminoAux(NodoVert inicio, NodoVert destino, Lista visitados) {
        boolean retorno = false;

        if (inicio != null) {
            if (inicio.getElemento().equals(destino.getElemento())) {
                retorno = true;
            } else {
                visitados.insertar(inicio.getElemento(), visitados.longitud() + 1);
                NodoAdy aux = inicio.getPrimerAdy();
                while (!retorno && aux != null) {
                    if (visitados.localizar(aux.getVertice().getElemento()) < 0) {
                        retorno = existeCaminoAux(aux.getVertice(), destino, visitados);
                    }
                    aux = aux.getSigAdyacente();
                }
            }
        }
        return retorno;
    }

    public Lista caminoMasCorto(Object inicio, Object destino) {
        Lista camino = new Lista();
        Lista visitados = new Lista();
        NodoVert nodoInicio = this.ubicarVertice(inicio);
        NodoVert nodoDestino = this.ubicarVertice(destino);
        if (nodoInicio != null && nodoDestino != null) {
            camino = caminoMasCortoAux(nodoInicio, nodoDestino, visitados);
        }
        return camino;
    }

    private Lista caminoMasCortoAux(NodoVert inicio, NodoVert destino, Lista visitados) {
        Lista retorno = new Lista();

        visitados.insertar(inicio.getElemento(), visitados.longitud() + 1);
        //System.out.println(visitados.toString());
        if (!inicio.getElemento().equals(destino.getElemento())) {
            NodoAdy aux = inicio.getPrimerAdy();
            if (aux != null) {
                while (aux != null && visitados.localizar(aux.getVertice().getElemento()) != -1) {
                    aux = aux.getSigAdyacente();
                }
                if (aux != null) {
                    retorno = caminoMasCortoAux(aux.getVertice(), destino, visitados);
                    aux = aux.getSigAdyacente();
                }
                while (aux != null) {
                    if (visitados.localizar(aux.getVertice().getElemento()) == -1) {
                        Lista listaAux = caminoMasCortoAux(aux.getVertice(), destino, visitados);
                        if (listaAux.longitud() <= retorno.longitud()) {
                            retorno = listaAux;
                            System.out.println("-------------------------------------------");
                            System.out.println(retorno.toString());
                        }
                    }
                    aux = aux.getSigAdyacente();
                }
            }
        }
        retorno.insertar(inicio.getElemento(), 1);
        visitados.eliminar(visitados.localizar(inicio.getElemento()));
        return retorno;
    }

    public Lista caminoMenorLong(Object origen, Object destino, int longMax) {
        Lista recorrido = new Lista();
        Lista primerCamino = new Lista();

        NodoVert inicio = ubicarVertice(origen);
        NodoVert fin = ubicarVertice(destino);

        if (inicio != null && fin != null) {
            caminoMenorLongAux(inicio, destino, recorrido, primerCamino, longMax, false);

        }
        return primerCamino;
    }

    private boolean caminoMenorLongAux(NodoVert aux, Object dest, Lista recorrido, Lista primerCamino, int max,
            boolean exito) {

        if (aux != null) {
            
            recorrido.insertar(aux.getElemento().toString(), recorrido.longitud() + 1);
            if (aux.getElemento().equals(dest)) {
                if (recorrido.longitud() <= max) {
                    primerCamino.setLista(recorrido.clone());
                    exito = true;
                }
            } else {
                NodoAdy ady = aux.getPrimerAdy();
                while (ady != null && !exito) {
                    if (recorrido.localizar(ady.getVertice().getElemento()) < 0) {
                        if ((recorrido.longitud()) <= max) { 
                            exito = caminoMenorLongAux(ady.getVertice(), dest, recorrido, primerCamino, max, exito);
                        }
                    }
                    ady = ady.getSigAdyacente();
                }
            }
            recorrido.eliminar(recorrido.longitud()); // Elimino el ultimo nodo agregado para proceder con otros nodos
                                                      // de la estructura
        }
        return exito;
    }
}
