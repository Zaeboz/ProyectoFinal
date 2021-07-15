package co.uniquindio.ProyectoFinal.modelo;

import co.uniquindio.ProyectoFinal.estructuraDeDatos.GrafoNoDirigido;
import co.uniquindio.ProyectoFinal.estructuraDeDatos.ListaSimple;

import java.io.Serializable;

public class Proyecto implements Serializable {

     private static final long serialVersionUID = 1L;

     final int MAX_VENDEDORES=10;

     ListaSimple<Vendedor> listaVendedores = new ListaSimple<>();

     GrafoNoDirigido grafoVendedores;

     public Proyecto() {
     }

     public int crearVendedores(String nombre){return 0;}

     public Vendedor buscarVendedor(String nombre){return null;}

     public int comentarProducto(Producto producto){return -1;}

     public int darMeGusta(Producto producto){return -1;}

     public ListaSimple<Vendedor> getListaVendedores() {
          return listaVendedores;
     }

     public void setListaVendedores(ListaSimple<Vendedor> listaVendedores) {
          this.listaVendedores = listaVendedores;
     }

     public GrafoNoDirigido getGrafoVendedores() {
          return grafoVendedores;
     }

     public void setGrafoVendedores(GrafoNoDirigido grafoVendedores) {
          this.grafoVendedores = grafoVendedores;
     }

     public int getMAX_VENDEDORES() {
          return MAX_VENDEDORES;
     }
}

