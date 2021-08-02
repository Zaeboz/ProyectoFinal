package co.uniquindio.ProyectoFinal.estructuraDeDatos;

import co.uniquindio.ProyectoFinal.excepciones.*;
import java.io.Serializable;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class GrafoNoDirigido<T extends Comparable<T>> implements Serializable {

    private HashMap<String, NodoGrafo> grafo;
    private HashMap<Integer, Arista> aristas;
    private NodoGrafo<T> inicial;
    private int size;

    public GrafoNoDirigido(HashMap<String, NodoGrafo> nodos, HashMap<Integer, Arista> aristas) {
        this.grafo = nodos;
        this.aristas = aristas;
        this.size=0;
    }

    public void agregar(String nombre, T elemento) throws NombreRepetidoException {
        if(!grafo.containsKey(nombre)) {
            NodoGrafo<T> nodoN = new NodoGrafo<>(nombre,elemento);
            grafo.put(nombre, nodoN);
        }else {
            throw new NombreRepetidoException("ya existe un nodo con ese nombre");
        }
        size++;
    }

    public void eliminar(String nombre) {//
        if(grafo.containsKey(nombre)) {
            grafo.remove(nombre);
            size--;
        }
    }

    public NodoGrafo<T> buscar(T elemento){
        NodoGrafo<T> nodoGrafo = null;
        if(grafo.containsKey(elemento)){
            nodoGrafo = grafo.get(elemento);
        }
        return nodoGrafo;
    }

    public void setDato(String nombre, T elemento) throws NombreRepetidoException {
        NodoGrafo<T> nodo=null;
        if(grafo.containsKey(nombre)) {
            nodo=grafo.get(nombre);
            nodo.setValorNodo(elemento);
        }
        else {
            throw new NombreRepetidoException("Nodo origen no existe");
        }
    }

    public T getDato(int indice) throws NombreRepetidoException  {
        T dato;
        NodoGrafo<T> nodo;
        if(indiceValido(indice)) {
            nodo=grafo.get(indice);
            dato=nodo.getValorNodo();
        }
        else {
            throw new NombreRepetidoException("Nodo origen no existe");
        }
        return dato;
    }

    public T getDatoN(String nombre) throws ErrorExisteNodo {
        T dato;
        NodoGrafo<T> nodo;
        if(grafo.containsKey(nombre)) {
            nodo=grafo.get(nombre);
            dato=nodo.getValorNodo();
        }
        else {
            throw new ErrorExisteNodo("Nodo origen no existe");
        }
        return dato;
    }

    //Verificar si indice es valido
    private boolean indiceValido(int indice) {
        if (indice >= 0 && indice < size) {
            return true;
        }
        throw new RuntimeException("�ndice no v�lido");
    }

    public int getSize() {
        return size;
    }

    public int getSizeNodo(String nombre) throws ErrorExisteNodo {
        int dato;
        NodoGrafo<T> nodo;
        if(grafo.containsKey(nombre)) {
            nodo=grafo.get(nombre);
            dato=nodo.getSize();
        }
        else {
            throw new ErrorExisteNodo("Nodo origen no existe");
        }
        return dato;
    }

    public NodoGrafo<T> seguirEnlace (String nombre, int indice) throws ErrorExisteNodo {
        NodoGrafo<T> nodoOrigen=null;
        NodoGrafo<T> nodoEnlace=null;
        if(grafo.containsKey(nombre)) {
            nodoOrigen=grafo.get(nombre);
            nodoEnlace=nodoOrigen.seguirEnlace(indice);
        }
        else {
            throw new ErrorExisteNodo("Nodo origen no existe");
        }
        return nodoEnlace;
    }

    /**
     * Inserta una arista unitaria entre los vertices v1 y nodo2
     * si y solo si no exista ya una arista que los una
     *
     * @param nodo1 Un extremo de la arista
     * @param nodo2 Otro extremo de la arista
     * @return true. Si y solo si la arista no existe previamente
     **/
    public boolean insertarArista(T nodo1, T nodo2)
    {
        NodoGrafo<T> nuevoOrigen = new NodoGrafo<>(nodo1);
        NodoGrafo<T> nuevoDestino = new NodoGrafo<>(nodo1);

        Arista arista = new Arista(nuevoOrigen, nuevoDestino);
        aristas.put(arista.hashCode(), arista);
        nuevoOrigen.insertarVecino(arista);
        nuevoDestino.insertarVecino(arista);
        return true;
    }

    /**
     * @param arista Arista que estamos buscando
     * @return true. Si y solo si el grafo contiene a la arista
     **/
    public boolean contieneLaArista(Arista arista)
    {
        if(arista.getNodo1() == null || arista.getNodo2() == null)
            return false;
        return this.aristas.containsKey(arista.hashCode());
    }

    /**
     * Elimina una arista del grafo. Por tanto, los vertices que unían
     * pierden la adyacencia entre ellos
     *
     *@param arista Arista que se quiere eliminar del grafo
     *@return Arista. Arista borrada del grafo
     */
    public Arista eliminarArista(Arista arista)
    {
        arista.getNodo1().eliminarVecino(arista);
        arista.getNodo2().eliminarVecino(arista);
        return this.aristas.remove(arista.hashCode());
    }

    /**
     * Nos devuelve true si encuentra el vértice que se pasa
     * como parámetro de entrada
     *
     * @param nodo Vértice que buscamos
     * @return boolean True si el vertice se encuentra.
     **/
    public boolean contieneElVertice(NodoGrafo nodo)
    {
        return (this.grafo.get(nodo.getValorNodo()) != null);
    }

    /**
     * @param valor Distintivo de cada vértice
     * @return NodoGrafo Devuelve el vértice asociado a la etiqueta
     **/
    public NodoGrafo getVertice(String valor)
    {
        return this.grafo.get(valor);
    }

    /**
     * Inserta un nuevo nodo
     *
     * @param nodo Vértice a insertar
     * @return boolean Verdarero si el vértice se inserta con éxito
     **/
    public boolean insertarNodo(NodoGrafo nodo)
    {
        NodoGrafo actual = this.grafo.get(nodo.getValorNodo());
        if(actual != null) //existía previamente?
        {
            return false;
        }

        grafo.put((String) nodo.getValorNodo(), nodo);
        return true;
    }

    /**
     * Elimina el vértice especificado mediante la valor
     * distintiva por parámetro de entrada. Al eliminar el vértice
     * se elimina también todas las adyancencias que poseía este.
     *
     * @param valor Cadena distintiva de cada vértice
     * @return Vertice. Devuelve el vértice eliminado
     **/
    public NodoGrafo eliminarVertice(String valor)
    {
        NodoGrafo nodoAux = grafo.remove(valor);

        while(nodoAux.getContarVecinos() >= 0)
            this.eliminarArista(nodoAux.getVecino(0));

        return nodoAux;
    }

    /**
     * @return Set<String>. Devuelve  los valores, que son el distintivo
     * único de cada objeto Vertice en el Grafo
     **/
    public Set<String> nodosKeys()
    {
        return this.grafo.keySet();
    }

    /**
     * @return Set<Arista>. Devuelve todos los objetos Arista del Grafo
     **/
    public Set<Arista> getAristas()
    {
        return new HashSet<Arista>(this.aristas.values());
    }
}
