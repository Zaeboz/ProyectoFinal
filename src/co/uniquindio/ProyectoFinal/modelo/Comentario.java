package co.uniquindio.ProyectoFinal.modelo;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Comentario implements Serializable {
    private static final long serialVersionUID = 1L;
    Vendedor vendedorComentario = new Vendedor();
    String fecha = "";

    public Comentario() {
    }

    public void generarFechaComentario() {
        Date date= new Date();
        SimpleDateFormat dateFormat=new SimpleDateFormat("dd/MM/yyyy");
        fecha= dateFormat.format(date);
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public Vendedor getVendedorComentario() {
        return vendedorComentario;
    }

    public void setVendedorComentario(Vendedor vendedorComentario) {
        this.vendedorComentario = vendedorComentario;
    }
}
