package co.uniquindio.ProyectoFinal.modelo;

import co.uniquindio.ProyectoFinal.estructuraDeDatos.ListaSimple;

import java.io.Serializable;

public class Producto implements Serializable, Comparable<Producto> {
    private static final long serialVersionUID = 1L;
    String nombre = "";
    String hora = "";
    String fecha = "";
    Categoria categoria = new Categoria();
    ListaSimple<Comentario> listaComentarios = new ListaSimple<>();
    ListaSimple<MeGusta> listaMeGusta = new ListaSimple<>();

    public Producto() {
    }

    @Override
    public int compareTo(Producto o) {
        return 0;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public ListaSimple<Comentario> getListaComentarios() {
        return listaComentarios;
    }

    public void setListaComentarios(ListaSimple<Comentario> listaComentarios) {
        this.listaComentarios = listaComentarios;
    }

    public ListaSimple<MeGusta> getListaMeGusta() {
        return listaMeGusta;
    }

    public void setListaMeGusta(ListaSimple<MeGusta> listaMeGusta) {
        this.listaMeGusta = listaMeGusta;
    }
}
