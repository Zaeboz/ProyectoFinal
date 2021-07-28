package co.uniquindio.ProyectoFinal.estructuraDeDatos;

/*
 * Un objeto Arista modela una arista no dirigida que representa la relación de adyacencia
 * entre dos vértices. Por tanto una arista tiene dos vértices.
 * Si el peso de una arista no se especifica, su valor es 1. Esto permite representar grafos uniformes.
 */

import java.io.Serializable;

public class Arista implements Comparable<Arista>, Serializable {

    private NodoGrafo nodo1, nodo2;

    /**
     * @param nodo1 Extremo o nodo de la arista
     * @param nodo2 Segundo nodo para formar la arista
     **/
    public Arista(NodoGrafo nodo1, NodoGrafo nodo2)
    {
        this.nodo1 = nodo1;
        this.nodo2 = nodo2;
    }


    /**
     * Dado un nodo válido como parámentro de entrada
     * se devuelve su adyecente. Estos se conectan mediante una arista
     *
     * @param actual
     * @return el vecino adyecente mediante este objeto Arista
     **/
    public NodoGrafo getVecinoDe(NodoGrafo actual)
    {
        if (actual.equals(this.nodo1))
            return this.nodo2;
        else if( actual.equals(this.nodo2))
            return this.nodo1;
        else
            return null;
    }

    /**
     * @return el contenido del atributo vertice1 de tipo Vertice
     **/
    public NodoGrafo getNodo1()
    {
        return this.nodo1;
    }

    /**
     * @return el contenido del atributo vertice2 de tipo Vertice
     **/
    public NodoGrafo getNodo2()
    {
        return this.nodo2;
    }

    /**
     * Comparamos los nodos de la arista2 con los nodos de la arista actual
     *
     * @param arista2 Arista con la que comparamos nuestra arista actual
     * @return int. Se devuelve 0 en caso de que ambas tengan el mismo peso
     **/
    public int compareTo(Arista arista2)
    {
        if(arista2.getNodo1() != this.nodo1 && arista2.getNodo2() != this.nodo2)
            return 0;
        else return 1;
    }

    /**
     * @return String. Representación mediante una cadena de este objeto Arista
     **/
    public String toString()
    {
        return "({" + this.nodo1 + ", " + this.nodo2 + "}";
    }


    /**
     * Se comparan el objeto Arista actual y otro que pasamos como parámetro de entrada
     * Queremos saber si son idénticos. Para ello sus vértices definidos como atributos
     * han de de ser iguales. Por lo que en el último if la responsabilidad se delega a
     * al método equals de la clase Vertice.
     *
     * @param dato Se comprueba si es de tipo Arista. Y si lo es, se compara e identifica.
     * @return true. Si y solo si ambos objetos son idénticos(extremos iguales).
     **/
    public boolean equals(Arista dato)
    {
        if(!(dato instanceof Arista))
            return false;

        Arista arista2 = (Arista) dato;

        if(arista2.nodo1.equals(this.nodo1) && arista2.nodo2.equals(this.nodo2))
            return true;

        return false;
    }
}
