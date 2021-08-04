package co.uniquindio.ProyectoFinal.modelo;

import co.uniquindio.ProyectoFinal.appliacation.Principal;
import co.uniquindio.ProyectoFinal.estructuraDeDatos.GrafoNoDirigido;
import co.uniquindio.ProyectoFinal.estructuraDeDatos.ListaSimple;
import co.uniquindio.ProyectoFinal.excepciones.ErrorExisteNodo;
import co.uniquindio.ProyectoFinal.excepciones.NombreRepetidoException;
import co.uniquindio.ProyectoFinal.persistencia.Persistencia;

import java.io.Serializable;
import java.util.List;
import co.uniquindio.ProyectoFinal.estructuraDeDatos.NodoGrafo;
import co.uniquindio.ProyectoFinal.excepciones.ErrorExisteNodo;
import co.uniquindio.ProyectoFinal.excepciones.NombreRepetidoException;

import co.uniquindio.ProyectoFinal.estructuraDeDatos.Nodo;
import co.uniquindio.ProyectoFinal.excepciones.*;


import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
public class Proyecto implements Serializable {

     private static final long serialVersionUID = 1L;
     private static final int MAX_VENDEDORES=10;
     private Vendedor vendedor = new Vendedor();
     private ListaSimple<Vendedor> listaVendedores = new ListaSimple<>();
     private GrafoNoDirigido<Vendedor> grafoVendedores = new GrafoNoDirigido<>() ;

     public Proyecto() {
     }

     /**
      * Metodo para crear un nuevo vendedor, el limite de vendedores que
      * pueden ser creados es de 10
      * @param vendedorNuevo nombre del vendedor a crear
      * @return
      * @throws NombreRepetidoException
      */
     public Vendedor crearVendedores(Vendedor vendedorNuevo) throws NombreRepetidoException {
          String nombreVededorNuevo = vendedorNuevo.getNombreVendedor();
          Vendedor vendedorExistente = buscarVendedor(nombreVededorNuevo);

          if(grafoVendedores.getSize() >= MAX_VENDEDORES) {
               throw new NombreRepetidoException("El numero de vendedores no puede ser mayor de 10");
          }
          else if(vendedorExistente != null) {
               throw new NombreRepetidoException("El vendedor "+nombreVededorNuevo+" no se puede crear porque ya existe");
          } else {
               grafoVendedores.agregar(nombreVededorNuevo, vendedorNuevo);
          }
          Persistencia.guardarRecursoProyectoXML(Principal.proyecto);
          return null;
     }

     /**
      * Metodo para buscar un vendedor en la lista de vendedores creados
      * @param nombreV nombre del vendedor a buscar
      * @return
      */
     public Vendedor buscarVendedor(String nombreV) {
          try {
               return grafoVendedores.getDatoN(nombreV);
          } catch (ErrorExisteNodo e) {
               e.printStackTrace();
               return null;
          }
     }


     /**
      * Metodo para la sugerencia de amigo
      * @param nombre
      * @return
      */
     public ListaSimple<Vendedor> sugerirVendedor(String nombre) {

          ListaSimple<Vendedor>vendedores= new ListaSimple<>();
          try {
               for(int i=0;i<grafoVendedores.getSizeNodo(nombre);i++) {
                    vendedores.agregarfinal(grafoVendedores.seguirEnlace(nombre, i).getValorNodo());
               }
               return vendedores;
          } catch (ErrorExisteNodo e) {
               e.printStackTrace();
               return null;
          }
     }

     public void agregarAmigos (String nombre) throws NombreRepetidoException {

          Vendedor vendedorExistente = buscarAmigo(nombre);
          ListaSimple<Vendedor> sugerenciasVendedores;
          sugerenciasVendedores = sugerirVendedor(nombre);
          Vendedor vendedores;
          if (vendedorExistente != null) {
               throw new NombreRepetidoException(nombre + " este vendedor ya es tu amigo. Ya existe");
          } else {
               for (int i = 0; i < sugerenciasVendedores.getTamanio(); i++) {
                    vendedores = sugerenciasVendedores.obtenerValorNodo(i);
                    if (vendedores.getNombreVendedor().equals(nombre)) {
                         vendedor.agregarContactos(vendedores);
                    }
               }
          }
     }

    /**
     * Metodo que permite agregar un amigo
     * @param vendedorOrigen vendedor origen
     * @param amigo amigo que se va a agregar
     * @throws NombreRepetidoException
     */
     public void agregarAmigos (Vendedor vendedorOrigen, Vendedor amigo) throws NombreRepetidoException{

          Vendedor vendedorExistente = buscarVendedor(amigo.getNombreVendedor());

          if (vendedorExistente == null ){
               throw new NombreRepetidoException(amigo.getNombreVendedor()+" este vendedor no esta disponible. No existe");
          }else{

              amigo.agregarContactos(vendedorOrigen);
              vendedorOrigen.agregarContactos(amigo);
              //grafoVendedores.insertarArista(vendedorOrigen, amigo);
          }
     }

     /**
      * Metodo para buscar a un amigo
      * @param nombre nombre del amigo a buscar
      * @return vendedor
      * @throws NombreRepetidoException
      */
     public Vendedor buscarAmigo(String nombre) throws NombreRepetidoException {
          ListaSimple<Vendedor>sugerenciasVendedores;
          sugerenciasVendedores= sugerirVendedor(nombre);
          Vendedor vendedores;
          for(int i=0;i<sugerenciasVendedores.getTamanio();i++) {
               vendedores=sugerenciasVendedores.obtenerValorNodo(i);
               if(vendedores.getNombreVendedor().equals(nombre))  {
                    return vendedor;
               }
          }
          return null;
     }

     /**
      * Metodo para saber la cantidad de productos publicados por vendedor
      * Metodo para las estadisticas
      * @param nombre nombre del vendedor del cual se quiere saber la cantidad de publicaciones
      * @return
      */
     public int retornarCantidadProductosPorVendedor(String nombre){
         Vendedor vendedorA = buscarVendedor(nombre);
         return vendedorA.arbolProductos.getPeso();
     }

     /**
      * Metodo para saber el porcentaje de productos publicados por vendedor
      * Metodo para las estadisticas
      * @param nombre nombre del vendedor del cual se quiere saber la cantidad de publicaciones
      * @return
      */
     public int retornarPorcentajeProductosPorVendedor(String nombre){
          int total = vendedor.obtenerTotalProductos();

          Vendedor vendedorA = buscarVendedor(nombre);
          int porcentaje= (vendedorA.arbolProductos.getPeso() * total)/100;

          return porcentaje;
     }

     /**
      * Metodo para saber la cantidad de contactos por usuario
      * Metodo para las estadisticas
      * @param nombre nombre del vendedor del cual se quiere saber la cantidad de contactos
      * @return
      */
     public int retornarCantidadContactosPorVendedor(String nombre){
          Vendedor vendedorA = buscarVendedor(nombre);
          return vendedorA.contactos.getTamanio();
     }

     public ListaSimple<Vendedor> getListaVendedores() {
          return listaVendedores;
     }

     public void setListaVendedores(ListaSimple<Vendedor> listaVendedores) {
          this.listaVendedores = listaVendedores;

     }

     public int getMAX_VENDEDORES() {
          return MAX_VENDEDORES;
     }

     public GrafoNoDirigido<Vendedor> getGrafoVendedores() {
          return grafoVendedores;
     }

     public void setGrafoVendedores(GrafoNoDirigido<Vendedor> grafoVendedores) {
          this.grafoVendedores = grafoVendedores;
     }

     /**
      * Edita un vendedor ya creado
      * @param key Es el nombre del anterior vendedor la cual hace de llave en el hashmap
      *            de nodos del grafo
      * @param vendedorNuevo El nuevo vendedor ya con los datos actualizados
      */
     public void editarVendedor(String key, Vendedor vendedorNuevo) {
          try {
               Vendedor vendedor = grafoVendedores.getDatoN(key);
               vendedor.setCedula(vendedorNuevo.getCedula());
               vendedor.setNombreVendedor(vendedor.getNombreVendedor());
               grafoVendedores.editarNodo(key, vendedorNuevo, vendedorNuevo.getNombreVendedor());
          } catch (ErrorExisteNodo errorExisteNodo) {
               errorExisteNodo.printStackTrace();
          }
          Persistencia.guardarRecursoProyectoXML(Principal.proyecto);
     }

     /**
      * Elimina un vendedor por su nombre, tambien busca en la lista de vendedores
      * todos los mensajes que estan relacionados al vendedor que se eliminara para
      * tambien eliminarlo de alli
      * @param nombreVendedor Nombre del vendedor a eliminar
      */
     public void eliminarVendedor(String nombreVendedor) {
          Vendedor vendedorEliminar = buscarVendedor(nombreVendedor);

          if(nombreVendedor!=null){
               for(String key: grafoVendedores.getNodos().keySet()){
                    try {
                         Vendedor vendedor = grafoVendedores.getDatoN(key);
                         vendedor.eliminarMensajes(vendedorEliminar);
                    } catch (ErrorExisteNodo errorExisteNodo) {
                         errorExisteNodo.printStackTrace();
                    }
               }
          }
          grafoVendedores.eliminarNodo(nombreVendedor);
          Persistencia.guardarRecursoProyectoXML(Principal.proyecto);
     }

     /**
      * Obtiene todos los mensajes que se han enviado entre 2 vendedores en especifico
      * @param vendedorPrincipal Es el vendedor origen
      * @param vendedor2 Es el vendedor final
      * @return Todos los mensajes enviados entre 2 vendedores de forma ordenada de acuerdo
      * a la fecha de envio del mensaje
      */
     public ListaSimple<Mensaje> obtenerMensajes(Vendedor vendedorPrincipal, Vendedor vendedor2){

          ListaSimple<Mensaje> mensajes = new ListaSimple<>();
          for(Mensaje mensaje: vendedorPrincipal.getListaMensajes()){
               if(mensaje.getVendedorFinal()==vendedor2 || mensaje.getVendedorFinal()==vendedorPrincipal){
                    mensajes.agregarfinal(mensaje);
               }
          }

          for(int i = 0; i < mensajes.getTamanio(); i++){
               for(int j = 0; j < mensajes.getTamanio()-i-1; j++){
                    Mensaje mensaje1 = mensajes.obtenerValorNodo(j);
                    Mensaje mensaje2 = mensajes.obtenerValorNodo(j+1);
                    if(mensaje1.getFecha().isAfter(mensaje2.getFecha())){
                         Mensaje temp = mensajes.obtenerValorNodo(j+1);
                         mensajes.modificarNodo(j+1, mensaje1);
                         mensajes.modificarNodo(j, temp);
                    }
               }
          }
          return mensajes;
     }

     /**
      * Metodo para enviar un mensaje de un vendedor a otro vendedor
      * @param vendedorPrincipal El vendedor origen del mensaje
      * @param mensaje El mensaje, hay que recordar que el objeto de tipo Mensaje ya tiene dentro de
      *                el al vendedor destino.
      */
     public void enviarMensaje(Vendedor vendedorPrincipal, Mensaje mensaje) {
          vendedorPrincipal.guardarMensaje(mensaje);
          Persistencia.guardarRecursoProyectoXML(Principal.proyecto);
     }


     public void generarMegusta(Producto productoSeleccionado) {

     }
}
