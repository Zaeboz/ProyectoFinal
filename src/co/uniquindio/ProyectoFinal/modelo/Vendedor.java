package co.uniquindio.ProyectoFinal.modelo;

import co.uniquindio.ProyectoFinal.estructuraDeDatos.ArbolBinario;
import co.uniquindio.ProyectoFinal.estructuraDeDatos.ListaSimple;
import co.uniquindio.ProyectoFinal.excepciones.NombreRepetidoException;

import java.io.Serializable;

public class Vendedor implements Serializable, Comparable<Vendedor> {
    private static final long serialVersionUID = 1L;
    int MAX_VENDEDORES = 9;
    private String nombreVendedor = new String();
    private String cedula = new String();
    ListaSimple<Vendedor> contactos = new ListaSimple<>();
    ListaSimple<Comentario> comentariosHechos = new ListaSimple<>();
    ListaSimple<MeGusta> meGustasRealizados = new ListaSimple<>();
    ListaSimple<Mensaje> listaMensajes = new ListaSimple<>();
    ListaSimple<Vendedor> sugerenciasContactos = new ListaSimple<>();
    ArbolBinario<Producto> arbolProductos = new ArbolBinario<>();

    public Vendedor(){
    }

    public void eliminarMensajes(Vendedor vendedor){
        for(Mensaje mensaje: listaMensajes){
            if(mensaje.getVendedorFinal()==vendedor){
                listaMensajes.eliminar(mensaje);
            }
        }
    }

    public void recibirMensaje(Mensaje mensaje)
    {
        listaMensajes.agregarfinal(mensaje);
    }

    public boolean crearProductos(String nombre, String nombreCategoria) throws NombreRepetidoException {
        Producto nuevoProducto =  null;
        Producto productoExistente = buscarProducto(nombre);
        boolean p =false;

        if(productoExistente != null) {
            throw new NombreRepetidoException("El nombre del producto  "+nombre+" no se puede crear. Ya existe");
        }
        else
        {
            Categoria categoria = new Categoria(nombreCategoria);
            nuevoProducto = new Producto(nombre,categoria);
            getArbolProductos().agregar(nuevoProducto);
            p=true;

        }

        return p;
    }

    public Producto buscarProducto(String nombre) throws NombreRepetidoException {

        Producto producto = null;
        for(int i=0;i<arbolProductos.getPeso();i++) {
            producto=arbolProductos.busquedaInorden(producto);
            if(arbolProductos.equals(nombre))  {
                return producto;
            }
        }

        return null;
    }
    /**
     * Metodo constructor de la clase Vendedor
     * @param nombreVendedor nombre del vendedor
     * @param arbolProductos lista de productos del vendedor
     */
    public Vendedor(String nombreVendedor, ArbolBinario<Producto> arbolProductos) {
        this.nombreVendedor = nombreVendedor;
        this.arbolProductos = arbolProductos;
    }

    public void agregarMensajes( Vendedor vendedorFinal, String mensaj){
        Mensaje mensajes = new Mensaje(vendedorFinal,mensaj);
        listaMensajes.agregarfinal(mensajes);
    }

    /**
     * metodo para agregar contactos
     * @param vendedor contacto a agregar
     */
    public void agregarContactos (Vendedor vendedor){

        getContactos().agregarfinal(vendedor);
    }

    public void agregarComentariosHechos (){

        Comentario comentario = new Comentario();
        getComentariosHechos().agregarfinal(comentario);
    }

    public void agregarMeGustaRealizados (){

        MeGusta meGusta= new MeGusta();

    }

    public void agregarListaMensajes (){

    }

    public void agregarSugerenciasContactos(Vendedor vendedor) {

        getSugerenciasContactos().agregarfinal(vendedor);

    }

    public int obtenerTotalProductos() {
        return arbolProductos.getPeso();
    }


    /**
     * Metodo para saber el top 10 de los productos con más me gusta
     * Este metodo es para lo de las estadisticas pero aun no lo hago
     */
    public void retornarTopDiezProductos(){
          arbolProductos.inorden();
    }


    //public void publicarProducto(Producto producto){
    //    arbolProductos.agregar(producto);
    //}

    public void comentarProductos(Comentario comentario){
        comentariosHechos.agregarfinal(comentario);
    }

    public void darMeGusta(MeGusta meGusta){
        meGustasRealizados.agregarfinal(meGusta);
    }

    public Producto buscarProductos(Producto producto) {
        return arbolProductos.busquedaInorden(producto);
    }


    public Vendedor(String nombreVendedor) {
        this.nombreVendedor = nombreVendedor;
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

    public void setComentariosHechos(ListaSimple<Comentario> comentariosHechos) {
        this.comentariosHechos = comentariosHechos;
    }

    public ListaSimple<MeGusta> getMeGustasRealizados() {
        return meGustasRealizados;
    }

    public void setMeGustasRealizados(ListaSimple<MeGusta> meGustasRealizados) {
        this.meGustasRealizados = meGustasRealizados;
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

    public ListaSimple<Comentario> getComentariosHechos() {
        return comentariosHechos;
    }

    @Override
    public int compareTo(Vendedor o) {
        return 0;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public void guardarMensaje(Mensaje mensaje) {
        listaMensajes.agregarfinal(mensaje);
    }

    public ListaSimple<Mensaje> getListaMensajes() {
        return listaMensajes;
    }
}
