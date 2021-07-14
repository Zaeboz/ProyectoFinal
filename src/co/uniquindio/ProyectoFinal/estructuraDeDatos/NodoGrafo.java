package co.uniquindio.ProyectoFinal.estructuraDeDatos;

import java.io.Serializable;

public class NodoGrafo <T> implements Serializable {

    private ListaDoble<Arista> grupoNodos;
    private T valorNodo;

    public NodoGrafo (T valorNodo)
    {
        this.valorNodo = valorNodo;
        this.grupoNodos = new ListaDoble<Arista>();
    }

    /**
     * Añade un objeto Arista al array de lista vecindad
     * si y solo este no esté contenido en dicho array de lista
     * @param arista Objeto a añadir
     */
    public void insertarVecino(Arista arista)
    {
        if( !this.grupoNodos.contiene(arista))
            this.grupoNodos.agregarfinal(arista);
    }

    /**
     * Comprueba si la arista incide en este vertice
     * @param arista Objeto a evaluar
     * @return true. Si y solo si el objeto esta contenido en el array de lista vecindad
     */
    public boolean contieneUnVecino(Arista arista)
    {
        return this.grupoNodos.contiene(arista);
    }

    /**
     * @param indice Indica la posicion a extraer
     * @return Arista. La arista apuntada por el índice en el array de lista vecindad
     */
    public Arista getVecino(int indice)
    {
        return this.grupoNodos.obtener(indice);
    }

    /**
     * Se elimina el objeto Arista del array de lista vecindad mediante
     * el identificando la referencia del objeto Arista
     * @param arista indice el objeto Arista a eliminar
     */
    public void eliminarVecino(Arista arista)
    {
        this.grupoNodos.eliminar(arista);
    }

    /**
     * @return int. Se devuelve el número de aristas incidentes (o vecinos)
     * tiene el vértice en el array de lista vecindad
     */
    public int getContarVecinos()
    {
        return this.grupoNodos.getTamanio();
    }

    /**
     * @return String. Devuelve el valor de la cadena etiqueta
     */
    public T getValorNodo()
    {
        return this.valorNodo;
    }

    /**
     * Se comprueba si vertice2 es un objeto de tipo Vertice
     * En tal caso podemos convertirlo de Object a Vertice
     * Y por último evaluamos si contiene el mismo valor que el objeto vertice actual
     * Para ello las etiquetas deben coincidir(son únicas para cada objeto Vertice)
     * @param vertice2 Objeto que comparamos con el vertice para
     * evaluar si son el mismo objeto
     * @return true. Efectivamente son el mismo objeto
     */
    public boolean equals(Object vertice2)
    {
        if( !(vertice2 instanceof NodoGrafo))
            return false;

        NodoGrafo v = (NodoGrafo) vertice2;
        return this.valorNodo.equals(v.valorNodo);
    }

    /**
     * @return String. Representación del vértice en una cadena
     */
    public String toString()
    {
        return "Vertice: " + this.valorNodo;
    }


    /**
     * @return int. Código hash para este vértice
     **/
    public int hashCode()
    {
        return this.getValorNodo().hashCode();
    }

    /**
     * @return ArrayList<Arista>. Copia del array de lista vecindad
     */
    public ListaDoble<Arista> getVecinos()
    {
        return this.grupoNodos;
    }
}
