package co.uniquindio.ProyectoFinal.modelo;

import co.uniquindio.ProyectoFinal.estructuraDeDatos.GrafoNoDirigido;
import co.uniquindio.ProyectoFinal.estructuraDeDatos.ListaSimple;
import co.uniquindio.ProyectoFinal.excepciones.ErrorExisteNodo;
import co.uniquindio.ProyectoFinal.excepciones.NombreRepetidoException;

import java.io.Serializable;

public class Proyecto implements Serializable {

     private static final long serialVersionUID = 1L;

     final int MAX_VENDEDORES=10;
     Vendedor vendedor;
     ListaSimple<Vendedor> listaVendedores = new ListaSimple<>();
     private GrafoNoDirigido<Vendedor> grafoVendedores ;




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

     public ListaSimple<Vendedor> sugerirVendedor(String nombreOrigen) {

          ListaSimple<Vendedor>vendedores= new ListaSimple<>();
          try {
               for(int i=0;i<grafoVendedores.getSizeNodo(nombreOrigen);i++) {
                    vendedores.agregarfinal(grafoVendedores.seguirEnlace(nombreOrigen, i).getValorNodo());
               }
               return vendedores;
          } catch (ErrorExisteNodo e) {
               e.printStackTrace();
               return null;
          }
     }



     public void agregarAmigos (String nombre) throws NombreRepetidoException {

          Vendedor vendedorExistente = buscarVendedor(nombre);
          ListaSimple<Vendedor>sugerenciasVendedores= new ListaSimple<>();
          sugerenciasVendedores= sugerirVendedor(nombre);
          Vendedor vendedores;
          if (vendedorExistente!=null ){
               throw new NombreRepetidoException(nombre+" este vendedor ya es tu amigo. Ya existe");
          }else{
               for (int i = 0; i < sugerenciasVendedores.getTamanio(); i++) {
                    vendedores=sugerenciasVendedores.obtenerValorNodo(i);
                         if(vendedores.getNombreVendedor().equals(nombre)) {
                              vendedor.agregarContactos(vendedores);
                         }
               }
          }



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

