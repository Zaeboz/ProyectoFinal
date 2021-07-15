package co.uniquindio.ProyectoFinal.modelo;

import co.uniquindio.ProyectoFinal.estructuraDeDatos.ArbolBinario;
import co.uniquindio.ProyectoFinal.estructuraDeDatos.ListaSimple;

import java.io.Serializable;

public class Vendedor implements Serializable {
    private static final long serialVersionUID = 1L;
    int MAX_VENDEDORES = 9;
    String nombreVendedor = new String();
    ListaSimple<Vendedor> contactos = new ListaSimple<>();
    ListaSimple<Comentario> comentariosHechos = new ListaSimple<>();
    ListaSimple<MeGusta> meGustasRealizados = new ListaSimple<>();
    ListaSimple<Mensaje> listaMensajes = new ListaSimple<>();
    ListaSimple<Vendedor> sugerenciasContactos = new ListaSimple<>();
    ArbolBinario<Producto> arbolProductos = new ArbolBinario<>();

    public Vendedor(){

    }

    public String getNombreVendedor() {
        return nombreVendedor;
    }

    public void setNombreVendedor(String nombreVendedor) {
        this.nombreVendedor = nombreVendedor;
    }

    public ArbolBinario<Producto> getArbolProductos() {
        return arbolProductos;
    }

    public void setArbolProductos(ArbolBinario<Producto> arbolProductos) {
        this.arbolProductos = arbolProductos;
    }


    public ListaSimple<Vendedor> getContactos() {
        return contactos;
    }

    public void setContactos(ListaSimple<Vendedor> contactos) {
        this.contactos = contactos;
    }

    public ListaSimple<Comentario> getComentariosHechos() {
        return comentariosHechos;
    }

    public void setComentariosHechos(ListaSimple<Comentario> comentariosHechos) {
        this.comentariosHechos = comentariosHechos;
    }

    public ListaSimple<MeGusta> getMeGustasRealizados() {
        return meGustasRealizados;
    }

    public void setMeGustasRealizados(ListaSimple<MeGusta> meGustasRealizados) {
        this.meGustasRealizados = meGustasRealizados;
    }

    public ListaSimple<Mensaje> getListaMensajes() {
        return listaMensajes;
    }

    public void setListaMensajes(ListaSimple<Mensaje> listaMensajes) {
        this.listaMensajes = listaMensajes;
    }

    public ListaSimple<Vendedor> getSugerenciasContactos() {
        return sugerenciasContactos;
    }

    public void setSugerenciasContactos(ListaSimple<Vendedor> sugerenciasContactos) {
        this.sugerenciasContactos = sugerenciasContactos;
    }

    public int getMAX_VENDEDORES() {
        return MAX_VENDEDORES;
    }
}
