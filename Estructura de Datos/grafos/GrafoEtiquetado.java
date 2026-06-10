package grafos;

import lineales.dinamicas.Lista;

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
                    s = s + "-" + auxAdy.getEtiqueta() + "->" + auxAdy.getVertice().getElemento().toString()
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
        boolean exito = false;
        NodoVert nodoInicio = null;
        NodoVert nodoDestino = null;
        NodoVert aux = this.inicio;

        while ((nodoInicio == null || nodoDestino == null) && aux != null){
            if(aux.getElemento().equals(inicio)) nodoInicio = aux;
            if(aux.getElemento().equals(destino)) nodoDestino = aux;
            aux = aux.getSigVertice();
        }

        if (nodoInicio != null && nodoDestino != null) {
            Lista visitados = new Lista();
            exito = existeCaminoAux(nodoInicio, nodoDestino, visitados);
        }
        return exito;
    }

    private boolean existeCaminoAux(NodoVert inicio, NodoVert destino, Lista visitados) {
        boolean exito = false;

        if (inicio != null) {
            if (inicio.getElemento().equals(destino.getElemento())) {
                exito = true;
            } else {
                visitados.insertar(inicio.getElemento(), visitados.longitud() + 1);
                NodoAdy aux = inicio.getPrimerAdy();
                while (!exito && aux != null) {
                    if (visitados.localizar(aux.getVertice().getElemento()) < 0) {
                        exito = existeCaminoAux(aux.getVertice(), destino, visitados);
                    }
                    aux = aux.getSigAdyacente();
                }
            }
        }
        return exito;
    }

    public Lista caminoMasCorto(Object comienzo, Object destino) {
        Lista caminoActual = new Lista();
        Lista mejorCamino = this.listarEnProfundidad(); //dejo seteado al mejor camino con una lista con todos los nodos
                                                        //asi no me genera error al comparar por primera vez
        NodoVert auxI = ubicarVertice(comienzo);
        NodoVert auxD = ubicarVertice(destino);
        if(auxI != null && auxD != null) {
            caminoMasCortoAux(auxI, destino, mejorCamino, caminoActual);
        }
        return mejorCamino;
    }

    private void caminoMasCortoAux(NodoVert n, Object destino, Lista mejorCamino, Lista caminoActual) {
        if (n != null){ //¿deberia chequear si el nodo es nulo?
            caminoActual.insertar(n.getElemento(), caminoActual.longitud() + 1);
            if (n.getElemento().equals(destino)) { //llegamos al destino
                if(mejorCamino.longitud()>caminoActual.longitud()){ //si el camino actual es más corto que el mejor encontrado
                    mejorCamino.setLista(caminoActual); //copia el camino
                }
            } else {
                NodoAdy aux = n.getPrimerAdy();
                while (aux != null) { //Recorro sus nodos adyacentes
                    if(caminoActual.localizar(aux.getVertice().getElemento()) < 0) { //si el nodo no fue visitado llamo recursivamente
                        caminoMasCortoAux(aux.getVertice(), destino, mejorCamino, caminoActual);
                    }
                    aux = aux.getSigAdyacente();
                }
            }
            //desmarco el vertice para que pueda entrar en otro camino
            caminoActual.eliminarApariciones(n.getElemento());
        }
    }

    public Lista caminoMenorTiempo(Object comienzo, Object destino){
        Lista caminoActual = new Lista();
        Lista mejorCamino = new Lista();
        double mejorTiempo = 100000;
        double tiempoActual = 0;
        NodoVert auxI = this.ubicarVertice(comienzo);
        NodoVert auxD = this.ubicarVertice(destino);
        if(auxI!=null && auxD != null){
            caminoMenorTiempoAux(auxI, destino, mejorTiempo,tiempoActual, caminoActual, mejorCamino);
        }
        return mejorCamino;
    }

    private void caminoMenorTiempoAux(NodoVert n, Object destino, double mejorTiempo, double tiempoActual, Lista caminoActual, Lista mejorCamino){
        if(n != null){
            caminoActual.insertar(n.getElemento(), caminoActual.longitud()+1);
            if(n.getElemento().equals(destino)) {
                //Llego al nodo destino y verifico si su camino es más corto
                if (tiempoActual < mejorTiempo) {
                    //Guardo el mejor tiempo y mejor camino
                    mejorCamino.setLista(caminoActual);
                    mejorTiempo = tiempoActual;
                }
            } else {
                NodoAdy aux = n.getPrimerAdy();
                while(aux != null){ //Recorro los nodos adyacentes
                    if(caminoActual.localizar(aux.getVertice().getElemento()) < 0){
                        //Sumo el tiempo que tomaria llegar a ese nodo
                        tiempoActual += aux.getEtiqueta();
                        //Llamo recursivamente desde el nodo siguiente
                        caminoMenorTiempoAux(aux.getVertice(), destino, mejorTiempo, tiempoActual, caminoActual, mejorCamino);
                        //Al volver, resto el tiempo actual
                        tiempoActual += aux.getEtiqueta();
                    }
                }
            }
            //desmarco el vertice para que pueda entrar en otro camino
            caminoActual.eliminarApariciones(n.getElemento());
        }
    }

    public Lista caminoMenorTiempoEvitandoNodo(Object comienzo, Object destino, Object evitar){
        Lista caminoActual = new Lista();
        Lista mejorCamino = new Lista();
        double mejorTiempo = 100000;
        double tiempoActual = 0;
        NodoVert auxI = this.ubicarVertice(comienzo);
        NodoVert auxD = this.ubicarVertice(destino);
        if(auxI!=null && auxD != null && !comienzo.equals(evitar) && !comienzo.equals(evitar)){
            //En caso de que el nodo a evitar no exista, simplemente buscaria el camino más rapido
            //No busca el camino si los nodos inicio y fin no existen o si se los quiere poner como nodo a evitar
            caminoMenorTiempoEvitandoNodoAux(auxI, destino, evitar, mejorTiempo,tiempoActual, caminoActual, mejorCamino);
        }
        return mejorCamino;
    }

    private void caminoMenorTiempoEvitandoNodoAux(NodoVert n, Object destino, Object evitar, double mejorTiempo, double tiempoActual, Lista caminoActual, Lista mejorCamino){
        if(n != null && !n.getElemento().equals(evitar)){ //En caso de que el nodo que se ingrese es el que queremos evitar, no hago nada
            caminoActual.insertar(n.getElemento(), caminoActual.longitud()+1);
            if(n.getElemento().equals(destino)) {
                //Llego al nodo destino y verifico si su camino es más corto
                if (tiempoActual < mejorTiempo) {
                    //Guardo el mejor tiempo y mejor camino
                    mejorCamino.setLista(caminoActual);
                    mejorTiempo = tiempoActual;
                }
            } else {
                NodoAdy aux = n.getPrimerAdy();
                while(aux != null){ //Recorro los nodos adyacentes
                    if(caminoActual.localizar(aux.getVertice().getElemento()) < 0){
                        //Sumo el tiempo que tomaria llegar a ese nodo
                        tiempoActual += aux.getEtiqueta();
                        //Llamo recursivamente desde el nodo siguiente
                        caminoMenorTiempoAux(aux.getVertice(), destino, mejorTiempo, tiempoActual, caminoActual, mejorCamino);
                        //Al volver, resto el tiempo actual
                        tiempoActual += aux.getEtiqueta();
                    }
                }
            }
            //desmarco el vertice para que pueda entrar en otro camino
            caminoActual.eliminarApariciones(n.getElemento());
        }
    }

    public Lista listarEnProfundidad(){
        Lista visitados = new Lista();
        // define un vertice donde comenzar a recorrer
        NodoVert aux = this.inicio;
        while (aux != null){
            if(visitados.localizar(aux.getElemento()) < 0){
                listarEnProfundidadAux(aux,visitados);
            }
            aux = aux.getSigVertice();
        }
        return visitados;
    }

    private void listarEnProfundidadAux(NodoVert n, Lista vis){
        if (n != null){
            vis.insertar(n.getElemento(), vis.longitud()+1);
            NodoAdy aux = n.getPrimerAdy();
            while (aux != null){
                if(vis.localizar(aux.getVertice().getElemento()) < 0){
                    listarEnProfundidadAux(aux.getVertice(),vis);
                }
                aux = aux.getSigAdyacente();
            }
        }
    }
}
