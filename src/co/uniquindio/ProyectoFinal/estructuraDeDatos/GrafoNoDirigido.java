package co.uniquindio.ProyectoFinal.estructuraDeDatos;

import java.io.Serializable;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class GrafoNoDirigido <T> implements Serializable {

    private HashMap<T, NodoGrafo> nodos;
    private HashMap<Integer, Arista> aristas;

    public GrafoNoDirigido(HashMap<T, NodoGrafo> nodos, HashMap<Integer, Arista> aristas) {
        this.nodos = nodos;
        this.aristas = aristas;
    }

    /**
     * Inserta una arista unitaria entre los vertices v1 y nodo2
     * si y solo si no exista ya una arista que los una
     *
     * @param nodo1 Un extremo de la arista
     * @param nodo2 Otro extremo de la arista
     * @return true. Si y solo si la arista no existe previamente
     **/
    public boolean insertarArista(NodoGrafo nodo1, NodoGrafo nodo2)
    {
        Arista arista = new Arista(nodo1, nodo2);
        aristas.put(arista.hashCode(), arista);
        nodo1.insertarVecino(arista);
        nodo1.insertarVecino(arista);
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
        return (this.nodos.get(nodo.getValorNodo()) != null);
    }

    /**
     * @param valor Distintivo de cada vértice
     * @return NodoGrafo Devuelve el vértice asociado a la etiqueta
     **/
    public NodoGrafo getVertice(T valor)
    {
        return this.nodos.get(valor);
    }

    /**
     * Inserta un nuevo nodo
     *
     * @param nodo Vértice a insertar
     * @return boolean Verdarero si el vértice se inserta con éxito
     **/
    public boolean insertarNodo(NodoGrafo nodo)
    {
        NodoGrafo actual = this.nodos.get(nodo.getValorNodo());
        if(actual != null) //existía previamente?
        {
            return false;
        }

        nodos.put((T) nodo.getValorNodo(), nodo);
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
    public NodoGrafo eliminarVertice(T valor)
    {
        NodoGrafo nodoAux = nodos.remove(valor);

        while(nodoAux.getContarVecinos() >= 0)
            this.eliminarArista(nodoAux.getVecino(0));

        return nodoAux;
    }

    /**
     * @return Set<String>. Devuelve  los valores, que son el distintivo
     * único de cada objeto Vertice en el Grafo
     **/
    public Set<T> nodosKeys()
    {
        return this.nodos.keySet();
    }

    /**
     * @return Set<Arista>. Devuelve todos los objetos Arista del Grafo
     **/
    public Set<Arista> getAristas()
    {
        return new HashSet<Arista>(this.aristas.values());
    }
}
