package co.uniquindio.ProyectoFinal.estructuraDeDatos;

import co.uniquindio.ProyectoFinal.excepciones.*;
import java.io.Serializable;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class GrafoNoDirigido<T extends Comparable<T>> implements Serializable {

    private HashMap<String, NodoGrafo> nodos = new HashMap<>();
    private HashMap<Integer, Arista> aristas = new HashMap<>();
    private NodoGrafo<T> inicial = new NodoGrafo<>();
    private int size;

    public GrafoNoDirigido(HashMap<String, NodoGrafo> nodos, HashMap<Integer, Arista> aristas) {
        this.nodos = nodos;
        this.aristas = aristas;
        this.size=0;
    }

    public GrafoNoDirigido() {

    }

    public void agregar(String key, T elemento) throws NombreRepetidoException {
        if(!nodos.containsKey(key)) {
            NodoGrafo<T> nodoN = new NodoGrafo<>();
            nodoN.setValorNodo(elemento);
            nodos.put(key, nodoN);
        }else {
            throw new NombreRepetidoException("ya existe un nodo con ese nombre");
        }
        size++;
    }

    public void eliminar(String nombre) {//
        if(nodos.containsKey(nombre)) {
            nodos.remove(nombre);
            size--;
        }
    }

    public NodoGrafo<T> buscar(T elemento){
        NodoGrafo<T> nodoGrafo = null;
        if(nodos.containsKey(elemento)){
            nodoGrafo = nodos.get(elemento);
        }
        return nodoGrafo;
    }

    public void editarNodo(String oldKey, T dato, String newKey){
        if(nodos.containsKey(oldKey)){
            NodoGrafo<T> nodoNuevo = new NodoGrafo<>();
            nodoNuevo.setValorNodo(dato);
            nodos.remove(oldKey);
            nodos.put(newKey, nodoNuevo);
        }
    }

    public void setDato(String nombre, T elemento) throws NombreRepetidoException {
        NodoGrafo<T> nodo = null;
        if(nodos.containsKey(nombre)) {
            nodo = nodos.get(nombre);
            nodo.setValorNodo(elemento);
        }
        else {
            throw new NombreRepetidoException("Nodo origen no existe");
        }
    }

    public T getDatoN(String nombre) throws ErrorExisteNodo {
        T dato;
        NodoGrafo<T> nodo;
        if(nodos.containsKey(nombre)) {
            nodo= nodos.get(nombre);
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
        if(nodos.containsKey(nombre)) {
            nodo= nodos.get(nombre);
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
        if(nodos.containsKey(nombre)) {
            nodoOrigen= nodos.get(nombre);
            nodoEnlace=nodoOrigen.seguirEnlace(indice);
        }
        else {
            throw new ErrorExisteNodo("Nodo origen no existe");
        }
        return nodoEnlace;
    }

    /**
     * Inserta una arista unitaria entre los nodos nodo1 y nodo2
     * si y solo si no exista ya una arista que los una
     *
     * @param nodo1 Un extremo de la arista
     * @param nodo2 Otro extremo de la arista
     * @return true. Si y solo si la arista no existe previamente
     **/
    public boolean insertarArista(NodoGrafo nodo1, NodoGrafo nodo2)
    {
        if (nodo1.equals(nodo2)) {
            return false;
        } else {
            Arista arista = new Arista(nodo1, nodo2);
            if (this.aristas.containsKey(arista.hashCode())) {
                return false;
            } else if (!nodo1.contieneUnVecino(arista) && !nodo2.contieneUnVecino(arista)) {
                this.aristas.put(arista.hashCode(), arista);
                nodo1.insertarVecino(arista);
                nodo2.insertarVecino(arista);
                return true;
            } else {
                return false;
            }
        }
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
     *@return Arista Arista borrada del grafo
     */
    public Arista eliminarArista(Arista arista)
    {
        try {
            arista.getNodo1().eliminarVecino(arista);
            arista.getNodo2().eliminarVecino(arista);
            return this.aristas.remove(arista.hashCode());
        }catch (NullPointerException e){
            System.out.println(e.getMessage());
        }
        return null;
    }

    /**
     * Nos devuelve true si encuentra el nodo que se pasa
     * como parámetro de entrada
     *
     * @param nodo Vértice que buscamos
     * @return boolean True si el nodo se encuentra.
     **/
    public boolean contieneElNodo(NodoGrafo nodo)
    {
        return (this.nodos.get(nodo.getValorNodo()) != null);
    }

    /**
     * @param valor Distintivo de cada nodo
     * @return NodoGrafo Devuelve el nodo asociado a la etiqueta
     **/
    public NodoGrafo getNodo(String valor)
    {
        return this.nodos.get(valor);
    }

    /**
     * Inserta un nuevo nodo
     *
     * @param nodo Nodo a insertar
     * @return boolean True si el nodo se inserta con éxito
     **/
    public boolean insertarNodo(NodoGrafo nodo)
    {
        NodoGrafo actual = this.nodos.get(nodo.getValorNodo());
        if(actual != null) //existía previamente?
        {
            return false;
        }

        nodos.put((String) nodo.getValorNodo(), nodo);
        return true;
    }

    /**
     * Elimina el nodo especificado mediante la valor
     * distintiva por parámetro de entrada. Al eliminar el nodo
     * se elimina también todas las adyacencias que poseía este.
     *
     * @param valor Cadena distintiva de cada nodo
     * @return NodoGrafo Devuelve el nodo eliminado
     **/
    public NodoGrafo eliminarNodo(String valor)
    {
        NodoGrafo nodoAux = nodos.remove(valor);

        //while(nodoAux.getContarVecinos() >= 0 )
          //  this.eliminarArista(nodoAux.getVecino(0));

        return nodoAux;
    }

    /**
     * @return Set<String>. Devuelve  los valores, que son el distintivo
     * único de cada objeto Vertice en el Grafo
     **/
    public Set<String> nodosKeys()
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

    public HashMap<String, NodoGrafo> getNodos() {
        return nodos;
    }

    public void setNodos(HashMap<String, NodoGrafo> nodos) {
        this.nodos = nodos;
    }
}
