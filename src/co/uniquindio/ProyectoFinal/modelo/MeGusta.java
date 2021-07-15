package co.uniquindio.ProyectoFinal.modelo;

import java.io.Serializable;

public class MeGusta implements Serializable {
    private static final long serialVersionUID = 1L;
    Vendedor vendedorLiked = new Vendedor();
    String fecha = "";

    public MeGusta() {
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
}
