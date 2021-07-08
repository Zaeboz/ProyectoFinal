package co.uniquindio.ProyectoFinal.modelo;

import co.uniquindio.ProyectoFinal.estructuraDeDatos.ArbolBinario;

import java.io.Serializable;

public class Vendedor implements Serializable {

    String nombreVendedor = new String();
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


}
