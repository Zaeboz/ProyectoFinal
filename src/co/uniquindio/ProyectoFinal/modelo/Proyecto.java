package co.uniquindio.ProyectoFinal.modelo;

import co.uniquindio.ProyectoFinal.estructuraDeDatos.GrafoNoDirigido;
import co.uniquindio.ProyectoFinal.estructuraDeDatos.ListaSimple;

import co.uniquindio.ProyectoFinal.excepciones.ErrorExisteNodo;
import co.uniquindio.ProyectoFinal.excepciones.NombreRepetidoException;

import co.uniquindio.ProyectoFinal.estructuraDeDatos.Nodo;
import co.uniquindio.ProyectoFinal.excepciones.*;


import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;

public class Proyecto implements Serializable {

     private static final long serialVersionUID = 1L;

     final int MAX_VENDEDORES=10;

     Vendedor vendedor;

     ListaSimple<Vendedor> listaVendedores = new ListaSimple<>();

     private GrafoNoDirigido<Vendedor> grafoVendedores ;



     public Proyecto() {
     }

     /**
      * Metodo para crear un nuevo vendedor, el limite de vendedores que
      * pueden ser creados es de 10
      * @param nombre nombre del vendedor a crear
      * @return
      * @throws NombreRepetidoException
      */
     public boolean crearVendedores(String nombre) throws NombreRepetidoException {

          Vendedor nuevoVendedor =  null;
          Vendedor vendedorExistente = buscarVendedor(nombre);
          boolean p =false;

          if(grafoVendedores.getSize() >= MAX_VENDEDORES) {
               throw new NombreRepetidoException("El numero de vendedores no puede ser mayor de 10");
          }
          else{
               if(vendedorExistente != null) {
                    throw new NombreRepetidoException("El nombre del vendedor  "+nombre+" no se puede crear. Ya existe");
               }
               else
               {
                    nuevoVendedor = new Vendedor(nombre);
                    getGrafoVendedores().agregar(nombre, nuevoVendedor);
                    p=true;
               }
          }
          return p;
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



     public void agregarAmigos (String nombre, Vendedor amigo) throws NombreRepetidoException {

          Vendedor vendedorExistente = buscarVendedor(nombre);
          Vendedor vendedores;
          if (vendedorExistente!=null ){
               throw new NombreRepetidoException(nombre+" este vendedor ya es tu amigo. Ya existe");
          }else {
               for (int i = 0; i < grafoVendedores.getSize(); i++) {
                    vendedores = grafoVendedores.getDato(i);
                    if (vendedores.getNombreVendedor().equals(nombre)) {
                         vendedor.agregarContactos(vendedores);
                         amigo.agregarContactos(vendedores);
                    }
               }
          }

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
}

