package co.uniquindio.ProyectoFinal.modelo;

import co.uniquindio.ProyectoFinal.estructuraDeDatos.ListaSimple;

import java.text.SimpleDateFormat;
import java.util.Date;

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


    /**
     * Metodo constructor de la clase Producto
     * @param nombre nombre del producto
     * @param categoria información de la categoria del producto
     */
    public Producto(String nombre, Categoria categoria) {
        this.nombre = nombre;
        this.categoria = categoria;
    }

    @Override
    public int compareTo(Producto o) {
        return 0;
    }

    public void generarFechaPublicacion() {
        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        SimpleDateFormat hourFormat = new SimpleDateFormat("HH:mm:ss");
        fecha = dateFormat.format(date);
        hora = hourFormat.format(date);
    }

    /**
     * Metodo para guardar los comentarios realizados a la publicación
     * @param comentario descripcion del comentario
     * @param vendedor informacion del vendedor que comentó
     */
    public void recibirComentario(String comentario, Vendedor vendedor)
    {
        Comentario comentario1 = new Comentario(vendedor, comentario);
        listaComentarios.agregarfinal(comentario1);
    }


    /**
     * Método para guardar los me gustas realizados
     * si el nombre del que realizó el me gusta ya esta registrado en la lista, lo borra.
     * @param meGusta infomación del me gusta recibido
     * @return true si guardó la información, false si la información ya estaba y la borro
     */
    public boolean recibirMeGusta(MeGusta meGusta)
    {
        boolean centinela = true;
        for(int i=0; i<listaMeGusta.getTamanio() && centinela; i++)
        {

            if(listaMeGusta.obtenerValorNodo(i).getNombre().equals(meGusta.getNombre()))
            {
                listaMeGusta.eliminar(meGusta);
                centinela = false;
            }
        }
        if(centinela)
        {
            listaMeGusta.agregarfinal(meGusta);
        }
        return centinela;
    }

    /**
     * Metodo para obtener la cantidad de me gustas en una publicacion
     * @return
     */
    public int obtenerTotalMeGustas() {
        return listaMeGusta.getTamanio();
    }

    /**
     * Metodo para obtener la cantidad de me comentarios en una publicacion
     * @return
     */
    public int obtenerTotalComentarios() {
        return listaComentarios.getTamanio();
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

