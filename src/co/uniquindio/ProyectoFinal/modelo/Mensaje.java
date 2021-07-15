package co.uniquindio.ProyectoFinal.modelo;

import java.io.Serializable;

public class Mensaje  implements Serializable {
    private static final long serialVersionUID = 1L;
    Vendedor vendedorFinal = new Vendedor();
    String mensaje = "";
    String fecha = "";
    String hora = "";
    int contadorMensajes = 0;

    public Mensaje() {
    }

    public Vendedor getVendedorFinal() {
        return vendedorFinal;
    }

    public void setVendedorFinal(Vendedor vendedorFinal) {
        this.vendedorFinal = vendedorFinal;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public int getContadorMensajes() {
        return contadorMensajes;
    }

    public void setContadorMensajes(int contadorMensajes) {
        this.contadorMensajes = contadorMensajes;
    }
}
