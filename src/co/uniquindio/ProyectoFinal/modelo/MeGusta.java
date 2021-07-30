package co.uniquindio.ProyectoFinal.modelo;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MeGusta implements Serializable {
    private static final long serialVersionUID = 1L;
    Vendedor vendedorLiked = new Vendedor();
    String nombre;
    String fecha = "";

    public MeGusta() {
    }

    public MeGusta(Vendedor vendedorLiked) {
        nombre= vendedorLiked.getNombreVendedor();
        this.vendedorLiked = vendedorLiked;
        this.fecha = fecha;
    }


    public void generarFechaMeGusta() {
        Date date= new Date();
        SimpleDateFormat dateFormat=new SimpleDateFormat("dd/MM/yyyy");
        fecha= dateFormat.format(date);
    }

    public Vendedor getVendedorLiked() {
        return vendedorLiked;
    }

    public void setVendedorLiked(Vendedor vendedorLiked) {
        this.vendedorLiked = vendedorLiked;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
