package co.uniquindio.ProyectoFinal.estructuraDeDatos;

/**
 * Clase que representa un NodoArbolBinario del �rbol binario
 * 
 *
 * @param <T>
 */
public class NodoArbolBinario<T extends Comparable<T>> {

	private NodoArbolBinario<T> izquierdo, derecho;
	private NodoArbolBinario<T> padre;
	private T elemento;
	
	/**
	 * Constructor de la clase
	 * @param elemento Dato del nodo
	 */
	public NodoArbolBinario(T elemento) {
		this.elemento = elemento;
	}
	
	public NodoArbolBinario(T elemento, NodoArbolBinario<T> padre) {
		this.elemento = elemento;
		this.padre = padre;
	}
	
	/**
	 * Agrega un nuevo elemento en el �rbol
	 * @param nuevo Nuevo dato
	 * @return true si lo pudo guardar
	 */
	public boolean agregar(T nuevo) 
	{
		if( nuevo.compareTo( elemento ) < 0 ) 
		{
			if(izquierdo==null) {
				izquierdo = new NodoArbolBinario<>(nuevo, this);
				return true;
			}else {
				return izquierdo.agregar(nuevo);
			}
		}
		else 
		{
			if( nuevo.compareTo( elemento ) > 0 ) 
			{
				if(derecho==null) {
					derecho = new NodoArbolBinario<>(nuevo, this);
					return true;
				}else {
					return derecho.agregar(nuevo);
				}
			}
		}
		
		return false;
	}
	
	/**
	 * Determina si un NodoArbolBinario es una Hoja
	 * @return true si es Hoja
	 */
	public boolean esHoja() {
		return izquierdo == null && derecho == null;
	}

	/**
	 * 
	 * @return
	 */
	public boolean tieneUnHijo() {
		return (izquierdo!=null && derecho==null) || (derecho!=null && izquierdo==null );
	}
	
	/**
	 * @return the izq
	 */
	public NodoArbolBinario<T> getIzquierdo() {
		return izquierdo;
	}

	/**
	 * @param izq the izq to set
	 */
	public void setIzquierdo(NodoArbolBinario<T> izq) {
		this.izquierdo = izq;
	}

	/**
	 * @return the der
	 */
	public NodoArbolBinario<T> getDerecho() {
		return derecho;
	}

	/**
	 * @param der the der to set
	 */
	public void setDerecho(NodoArbolBinario<T> der) {
		this.derecho = der;
	}

	/**
	 * @return the elemento
	 */
	public T getElemento() {
		return elemento;
	}

	/**
	 * @param elemento the elemento to set
	 */
	public void setElemento(T elemento) {
		this.elemento = elemento;
	}

	/**
	 * @return the padre
	 */
	public NodoArbolBinario<T> getPadre() {
		return padre;
	}

	/**
	 * @param padre the padre to set
	 */
	public void setPadre(NodoArbolBinario<T> padre) {
		this.padre = padre;
	}
	
}
