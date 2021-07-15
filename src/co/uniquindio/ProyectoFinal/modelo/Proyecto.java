package co.uniquindio.ProyectoFinal.modelo;

import co.uniquindio.ProyectoFinal.estructuraDeDatos.GrafoNoDirigido;

import java.io.Serializable;

public class Proyecto implements Serializable {

     final int MAX=10;
     GrafoNoDirigido<Vendedor> grafoVendedores;

     public int crearVendedores(String nombre){return 0;}

     public Vendedor buscarVendedor(String nombre){return null;}

     public int comentarProducto(Producto producto){return -1;}

     public int darMeGusta(Producto producto){return -1;}
}

