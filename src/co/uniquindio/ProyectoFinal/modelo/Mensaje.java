package co.uniquindio.ProyectoFinal.modelo;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Mensaje  implements Serializable {
    private static final long serialVersionUID = 1L;
    Vendedor vendedorFinal = new Vendedor();
    String mensaje = "";
    String fecha = "";
    String hora = "";
    int contadorMensajes = 0;

    public Mensaje() {
    }

    /**
     * Metodo constructor de la clase Mensaje
     * @param vendedorFinal
     * @param mensaje
     */
    public Mensaje(Vendedor vendedorFinal, String mensaje) {
        this.vendedorFinal = vendedorFinal;
        this.mensaje = mensaje;
    }


    public Date generarFechaMensaje() {
        Date date= new Date();
        SimpleDateFormat dateFormat=new SimpleDateFormat("dd/MM/yyyy");
        SimpleDateFormat hourFormat=new SimpleDateFormat("HH:mm:ss");
        fecha= dateFormat.format(date);
        hora= hourFormat.format(date);
        return date;
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
