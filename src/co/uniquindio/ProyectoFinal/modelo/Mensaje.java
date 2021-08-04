package co.uniquindio.ProyectoFinal.modelo;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class Mensaje  implements Serializable {
    private static final long serialVersionUID = 1L;
    Vendedor vendedorFinal = new Vendedor();
    LocalDateTime fecha;
    String fechaString;
    String mensaje = "";
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
        generarFechaMensaje();
    }


    public void generarFechaMensaje() {
        LocalDate hoy = LocalDate.now();
        DateTimeFormatter f = DateTimeFormatter.ofPattern("yyyy'/'MM'/'dd '-' kk':'mm");
        LocalTime ahora = LocalTime.now();
        fecha = LocalDateTime.of(hoy, ahora);
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

    public int getContadorMensajes() {
        return contadorMensajes;
    }

    public void setContadorMensajes(int contadorMensajes) {
        this.contadorMensajes = contadorMensajes;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public String getFechaString() {
        DateTimeFormatter f = DateTimeFormatter.ofPattern("yyyy'/'MM'/'dd '-' kk':'mm");
        return fechaString = fecha.format(f);
    }
}
