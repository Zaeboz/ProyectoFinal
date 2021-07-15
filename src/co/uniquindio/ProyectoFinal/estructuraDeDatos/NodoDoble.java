package co.uniquindio.ProyectoFinal.estructuraDeDatos;

import java.io.Serializable;

/**
 * Clase nodo aplicando Generics
 *
 *
 *
 * **/


public class NodoDoble<T> implements Serializable{

	private static final long serialVersionUID = 1L;

	private NodoDoble<T> siguienteNodo = null;
	private NodoDoble<T> anteriorNodo = null;
	private T valorNodo = null;


	/**
	 * Constructor de la clase NodoArbolBinario
	 * @paramdatoElemento que se guarda en el NodoArbolBinario
	 */
	public NodoDoble(T valorNodo) {
		this.valorNodo = valorNodo;
	}

	public NodoDoble(){
		
	}


	/**
	 * Constructor de la clase NodoArbolBinario
	 * @param dato Elemento que se guarda en el NodoArbolBinario
	 * @param siguiente Enlace al siguiente NodoArbolBinario
	 */
	public NodoDoble(T dato, NodoDoble<T> siguiente,NodoDoble<T> anterior) {
		super();
		this.valorNodo = dato;
		this.siguienteNodo = siguiente;
		this.anteriorNodo = anterior;
	}


	//Metodos get y set de la clase NodoArbolBinario

	public NodoDoble<T> getSiguienteNodo() {
		return siguienteNodo;
	}


	public void setSiguienteNodo(NodoDoble<T> siguienteNodo) {
		this.siguienteNodo = siguienteNodo;
	}


	public T getValorNodo() {
		return valorNodo;
	}


	public void setValorNodo(T valorNodo) {
		this.valorNodo = valorNodo;
	}


	public NodoDoble<T> getAnteriorNodo() {
		return anteriorNodo;
	}


	public void setAnteriorNodo(NodoDoble<T> anteriorNodo) {
		this.anteriorNodo = anteriorNodo;
	}



}
