package co.uniquindio.ProyectoFinal.modelo;

import java.io.Serializable;

public class Producto implements Serializable, Comparable<Producto> {
    String nombre;

    @Override
    public int compareTo(Producto o) {
        return 0;
    }
}
