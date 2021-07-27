package co.uniquindio.ProyectoFinal.modelo;

import java.io.Serializable;

public class Categoria implements Serializable {
    private static final long serialVersionUID = 1L;
    String nombre;

    public Categoria() {
    }

    public Categoria(String nombre) {
        this.nombre = nombre;
    }


    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}

