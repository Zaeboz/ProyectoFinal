package co.uniquindio.ProyectoFinal.estructuraDeDatos;

import java.io.Serializable;

public class NodoGrafo <T> implements Serializable {

    private ListaDoble<Arista> vecindad;
    private T valorNodo;

    public NodoGrafo ()
    {
        this.valorNodo = valorNodo;
        this.vecindad = new ListaDoble<Arista>();
    }

    public <T extends Comparable<T>> NodoGrafo(String nombre, T elemento) {
    }

    /**
     * Añade un objeto Arista al array de lista vecindad
     * si y solo este no esté contenido en dicho array de lista
     * @param arista Objeto a añadir
     */
    public void insertarVecino(Arista arista)
    {
        if( !this.vecindad.contiene(arista))
            this.vecindad.agregarfinal(arista);
    }

    /**
     * Comprueba si la arista incide en este nodo
     * @param arista Objeto a evaluar
     * @return true Si y solo si el objeto esta contenido en el array de lista vecindad
     */
    public boolean contieneUnVecino(Arista arista)
    {
        return this.vecindad.contiene(arista);
    }

    /**
     * @param indice Indica la posicion a extraer
     * @return Arista La arista apuntada por el índice en la lista doble de vecindad
     */
    public Arista getVecino(int indice)
    {
        return this.vecindad.obtener(indice);
    }

    /**
     * Se elimina el objeto Arista de la lista doble de vecindad mediante
     * el identificando la referencia del objeto Arista
     * @param arista indice el objeto Arista a eliminar
     */
    public void eliminarVecino(Arista arista)
    {
        this.vecindad.eliminar(arista);
    }

    /**
     * @return int Se devuelve el número de aristas incidentes (o vecinos)
     * tiene el nodo en la lista doble de vecindad
     */
    public int getContarVecinos()
    {
        return this.vecindad.getTamanio();
    }

    /**
     * @return T Devuelve el valor de la cadena etiqueta
     */
    public T getValorNodo()
    {
        return this.valorNodo;
    }

    /**
     * Se comprueba si nodo2 es un objeto de tipo NodoGrafo
     * En tal caso podemos convertirlo de Object a NodoGrafo
     * Y por último evaluamos si contiene el mismo valor que el objeto nodo actual
     * Para ello las etiquetas deben coincidir(son únicas para cada objeto NodoGrafo)
     * @param nodo2 Objeto que comparamos con el nodo para
     * evaluar si son el mismo objeto
     * @return true Efectivamente son el mismo objeto
     */
    public boolean equals(Object nodo2)
    {
        if( !(nodo2 instanceof NodoGrafo))
            return false;

        NodoGrafo v = (NodoGrafo) nodo2;
        return this.valorNodo.equals(v.valorNodo);
    }

    /**
     * @return String. Representación del nodo en una cadena
     */
    public String toString()
    {
        return "Nodo: " + this.valorNodo;
    }


    /**
     * @return int. Código hash para este nodo
     **/
    public int hashCode()
    {
        return this.getValorNodo().hashCode();
    }

    /**
     * @return ListaDoble<Arista> Copia de la lista doble de vecindad
     */
    public ListaDoble<Arista> getVecinos()
    {
        return this.vecindad;
    }

    public void setValorNodo(T valorNodo) {
        this.valorNodo = valorNodo;
    }
}
