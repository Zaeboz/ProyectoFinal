package co.uniquindio.ProyectoFinal.estructuraDeDatos;

import java.util.HashMap;

public class GrafoNoDirigido <T> {

    private HashMap<T, NodoGrafo> vertices;
    private HashMap<Integer, Arista> aristas;
}
