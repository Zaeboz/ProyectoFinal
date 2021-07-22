package co.uniquindio.ProyectoFinal.modelo;

import co.uniquindio.ProyectoFinal.estructuraDeDatos.GrafoNoDirigido;
import co.uniquindio.ProyectoFinal.estructuraDeDatos.ListaSimple;
import co.uniquindio.ProyectoFinal.excepciones.NombreRepetidoException;

import java.io.Serializable;

public class Proyecto implements Serializable {

     private static final long serialVersionUID = 1L;

     final int MAX_VENDEDORES=10;

     ListaSimple<Vendedor> listaVendedores = new ListaSimple<>();
     private GrafoNoDirigido<Vendedor> grafoVendedores;


     public Proyecto() {
     }

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

     public Vendedor buscarVendedor(String nombre) throws NombreRepetidoException {

          int i;
          Vendedor vendedor;
          for(i=0;i<grafoVendedores.getSize();i++) {
               vendedor=grafoVendedores.getDato(i);
               if(vendedor.getNombreVendedor().equals(nombre)) {
                    return vendedor;
               }
          }

          return null;

     }

     public int comentarProducto(Producto producto, Comentario comentario){

          producto.getListaComentarios().agregarInicio(comentario);
          return -1;
     }

     public int darMeGusta(Producto producto, MeGusta meGusta){

          producto.getListaMeGusta().agregarInicio(meGusta);
          return -1;
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

