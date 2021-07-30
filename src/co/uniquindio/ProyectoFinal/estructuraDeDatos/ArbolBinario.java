package co.uniquindio.ProyectoFinal.estructuraDeDatos;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Clase que representa una estructura de datos tipo �rbol Binario de B�squeda
 * 
 *
 * @param <T>
 */
public class ArbolBinario<T extends Comparable<T>> {

	private NodoArbolBinario<T> raiz;
	private int peso;
	
	/**
	 * Verifica si un �rbol est� vac�o
	 * @return true si est� vac�o
	 */
	public boolean estaVacio() {
		return raiz==null;
	}
	
	/**
	 * Agrega un nuevo elemento al �rbol
	 * @param elemento Nuevo dato
	 * @return true si lo pudo guardar
	 */
	public void agregar(T elemento) {		
		if(estaVacio()) {
			raiz = new NodoArbolBinario<>(elemento);
			peso++;
		}else if(raiz.agregar(elemento)){			
			peso++;
		}		
	}
	
	/**
	 * Realiza el recorrido inorden en el �rbol binario
	 */
	public void inorden() {
		inorden(raiz);
		System.out.println();
	}
	
	/**
	 * Realiza el recorrido inorden en el �rbol binario
	 * @param n NodoArbolBinario ra�z
	 */
	private void inorden(NodoArbolBinario<T> n) {
		if(n!=null) {
			inorden(n.getIzquierdo());
			System.out.print(n.getElemento()+"\t");
			inorden(n.getDerecho());
		}
	}



	/**
	 * Realiza el recorrido preorden en el �rbol binario
	 */
	public void preorden() {
		preorden(raiz);
		System.out.println();
	}
	
	/**
	 * Realiza el recorrido preorden en el �rbol binario
	 * @param n NodoArbolBinario ra�z
	 */
	private void preorden(NodoArbolBinario<T> n) {
		if(n!=null) {
			System.out.println(n.getElemento());
			preorden(n.getIzquierdo());			
			preorden(n.getDerecho());
		}
	}

	/**
	 * Realiza el recorrido postorden en el �rbol binario
	 */
	public void postorden() {
		postorden(raiz);
		System.out.println();
	}
	
	/**
	 * Realiza el recorrido postorden en el �rbol binario
	 * @param n NodoArbolBinario ra�z
	 */
	private void postorden(NodoArbolBinario<T> n) {
		if(n!=null) {			
			postorden(n.getIzquierdo());			
			postorden(n.getDerecho());
			System.out.println(n.getElemento());
		}
	}

	public T busquedaInorden(T dato) {
		return busInorden(raiz,dato);
	}

	/**
	 * Realiza el recorrido inorden en el árbol binario
	 * @param n Nodo raíz
	 */
	private T busInorden(NodoArbolBinario<T> n,T dato) {
		if(n!=null) {
			if(n.getElemento().equals(dato)) {
				return n.getElemento();
			}
			T var;
			var=busInorden(n.getIzquierdo(),dato);
			if(var!=null) {
				return var;
			}else {
				var=busInorden(n.getDerecho(),dato);
				if(var!=null) {
					return var;
				}else {
					return null;
				}
			}
		}else {
			return null;
		}
	}


	/**
	 * Vetifica si un elemento existe en el �rbol binario
	 * @param n NodoArbolBinario ra�z
	 * @param elemento Elemento a buscar
	 * @return true si lo encuentra
	 */
	public boolean existe(NodoArbolBinario<T> n, T elemento) {
		if(n!=null) {
			if( elemento.compareTo(n.getElemento()) == 0 ) {
				return true;
			}else if( elemento.compareTo(n.getElemento()) < 0 ) {
				return existe(n.getIzquierdo(), elemento);
			}else if( elemento.compareTo(n.getElemento()) > 0 ) {
				return existe(n.getDerecho(), elemento);
			}
		}
		return false;
	}
	
	/**
	 * Cuenta todos los elementos que hay en el �rbol
	 * @param n NodoArbolBinario ra�z
	 * @return Peso del �rbol
	 */
	public int obtenerPeso(NodoArbolBinario<T> n) {
		
		if(n!=null) {
			return 1+obtenerPeso(n.getIzquierdo())+obtenerPeso(n.getDerecho());
		}
		
		return 0;
	}
	
	/**
	 * Devuelve la altura del �rbol
	 * @param n NodoArbolBinario ra�z
	 * @return Altura
	 */
	public int obtenerAltura(NodoArbolBinario<T> n, int prof) {
		if(n!=null) {
			int profIzq = obtenerAltura(n.getIzquierdo(), prof+1);
			int profDer = obtenerAltura(n.getDerecho(), prof+1);
			
			if(profIzq>=profDer) {
				return profIzq;
			}else {
				return profDer;
			}
		}		
		return prof;
	}
	
	/**
	 * Retorna el nivel de un elemento dentro del �rbol
	 * @param n NodoArbolBinario ra�z
	 * @param elemento Elemento a buscar
	 * @param nivel Nivel inicial
	 * @return Nivel del elemento
	 */
	public int obtenerNivel(NodoArbolBinario<T> n, T elemento, int nivel) {
		if(n!=null) {
			if(elemento.compareTo(n.getElemento())==0) {
				return nivel;
			}else if(elemento.compareTo(n.getElemento())<0) {
				return obtenerNivel(n.getIzquierdo(), elemento, nivel+1);
			}else if(elemento.compareTo(n.getElemento())>0) {
				return obtenerNivel(n.getDerecho(), elemento, nivel+1);
			}
		}
		return -1;
	}
	
	/**
	 * Cuenta el n�mero de Hojas del �rbol
	 * @param n NodoArbolBinario ra�z
	 * @return hojas del �rbol
	 */
	public int contarHojas(NodoArbolBinario<T> n) {
		
		if(n!=null) {
			int c = 0;
			if(n.esHoja()) {
				c = 1;
			}
			return c+contarHojas(n.getIzquierdo())+contarHojas(n.getDerecho());
		}
		return 0;
	}
	
	/**
	 * Retorna el valor m�s peque�o del �rbol
	 * @return Menor
	 */
	public T obtenerMenor() {
		
		NodoArbolBinario<T> aux = raiz;
		
		while(aux.getIzquierdo()!=null) {
			aux = aux.getIzquierdo();
		}
		
		return aux.getElemento();
	}
	
	/**
	 * Retorna el valor m�s peque�o del �rbol
	 * @param n NodoArbolBinario ra�z
	 * @return Menor
	 */
	public T obtenerMenor(NodoArbolBinario<T> n) {
		if(n.getIzquierdo()!=null) {
			return obtenerMenor(n.getIzquierdo());
		}
		return n.getElemento();
	}
	
	/**
	 * Imprime el �rbol de manera horizontal
	 * @param n NodoArbolBinario ra�z
	 * @param nivel Nivel de cada nodo, determina el n�mero de espacios
	 */
	public void imprimirHorizontal(NodoArbolBinario<T> n, int nivel) {
		
		if(n!=null) {
			
			imprimirHorizontal(n.getDerecho(), nivel+1);
			
			for (int i = 0; i < nivel; i++) {
				System.out.print("\t");
			}
			
			System.out.println(n.getElemento());
			
			imprimirHorizontal(n.getIzquierdo(), nivel+1);
			
		}
		
	}
	
	/**
	 * Elimina un elemento del �rbol
	 * @param elemento Elemento a borrar
	 */
	public void eliminar(T elemento) {
		eliminar(raiz, elemento);
	}
	
	/**
	 * Elimina un elemento del �rbol
	 * @param n NodoArbolBinario ra�z
	 * @param elemento Elemento a buscar para borrar
	 */
	private void eliminar(NodoArbolBinario<T> n, T elemento) {
		
		if(n!=null) {
			if(elemento.compareTo(n.getElemento())==0) {
				eliminar(n);
			}else if(elemento.compareTo(n.getElemento())<0) {
				eliminar(n.getIzquierdo(), elemento);
			}else if(elemento.compareTo(n.getElemento())>0) {
				eliminar(n.getDerecho(), elemento);
			}
		}
		
	}
	
	/**
	 * Elimina un NodoArbolBinario del �rbol
	 * @param n NodoArbolBinario a eliminar
	 */
	private void eliminar(NodoArbolBinario<T> n) {
		
		NodoArbolBinario<T> padre = n.getPadre();
		
		//Caso 1
		if( n.esHoja() ) {
			if(padre==null) {
				raiz = null;
			}else if( n.getElemento().compareTo(padre.getElemento())>0 ) {
				padre.setDerecho(null);
			}else {
				padre.setIzquierdo(null);
			}
			peso--;
		//Caso 2
		}else if( n.tieneUnHijo() ) {
			if(padre==null) {
				if(n.getIzquierdo()!=null) {
					raiz = n.getIzquierdo();
				}else {
					raiz = n.getDerecho();
				}
				raiz.setPadre(null);
			}else if( n.getElemento().compareTo(padre.getElemento())>0 ) {
				if( n.getIzquierdo()!=null ) {
					padre.setDerecho( n.getIzquierdo() );
				}else {
					padre.setDerecho( n.getDerecho() );
				}
				padre.getDerecho().setPadre(padre);
			}else {
				if( n.getIzquierdo()!=null ) {
					padre.setIzquierdo( n.getIzquierdo() );
				}else {
					padre.setIzquierdo( n.getDerecho() );
				}
				padre.getIzquierdo().setPadre(padre);
			}
			peso--;
		//Caso 3-tiene dos hijos
		}else {
			NodoArbolBinario<T> mayor = obtenerNodoMayor( n.getIzquierdo() );
			T aux = mayor.getElemento();			
			eliminar(mayor);
			n.setElemento( aux );
		}
		
	}
	
	/**
	 * Imprime el �rbol usando el recorrido en amplitud (por niveles)
	 */
	public void imprimirAmplitud() {
		
		Queue<NodoArbolBinario<T>> cola = new LinkedList<>();
		NodoArbolBinario<T> aux = raiz;
		cola.add(aux);
		
		while( !cola.isEmpty() ) {
			
			NodoArbolBinario<T> primero = cola.poll();
			System.out.print( primero.getElemento()+"\t" );
			
			if(primero.getIzquierdo()!=null) {
				cola.add( primero.getIzquierdo() );
			}
			
			if(primero.getDerecho()!=null) {
				cola.add( primero.getDerecho() );
			}
			
		}
		
	}
	
	/**
	 * Cuenta el n�mero de hojas del �rbol sin recursividad
	 * @return Hojas
	 */
	public int contarHojas() {
		
		Queue<NodoArbolBinario<T>> cola = new LinkedList<>();
		NodoArbolBinario<T> aux = raiz;
		cola.add(aux);
		int cont = 0;
		
		while(!cola.isEmpty()) {
			
			NodoArbolBinario<T> primero = cola.poll();
			
			if( primero.esHoja() ) {
				cont++;
			}
			
			if(primero.getIzquierdo()!=null ) {
				cola.add(primero.getIzquierdo());
			}
			
			if(primero.getDerecho()!=null ) {
				cola.add(primero.getDerecho());
			}
			
		}
		
		return cont;
	}
	
	/**
	 * Obtiene la altura del �rbol sin recursividad
	 * @return altura
	 */
	public int obtenerAltura() {
		
		Queue<NodoArbolBinario<T>> cola = new LinkedList<>();
		NodoArbolBinario<T> aux = raiz;
		cola.add(aux);
		int cont1=1, cont2=1;
		
		while(!cola.isEmpty()) {
			
			NodoArbolBinario<T> primero = cola.poll();
			
			if(primero.getIzquierdo()!=null ) {
				cont1++;
				cola.add(primero.getIzquierdo());
			}
			
			if(primero.getDerecho()!=null ) {
				cont2++;
				cola.add(primero.getDerecho());
			}
			
		}				
		
		return cont1 > cont2 ? cont1 : cont2;
	}
	
	
	/**
	 * Obtiene el nodo m�s grande de un sub�rbol
	 * @param n NodoArbolBinario Izquierdo del subarbol
	 * @return NodoArbolBinario m�s grande
	 */
	public NodoArbolBinario<T> obtenerNodoMayor(NodoArbolBinario<T> n){
		while(n.getDerecho()!=null) {
			n = n.getDerecho();
		}
		return n;
	}
	
	/**
	 * Obtiene el nodo m�s peque�o de un sub�rbol
	 * @param n NodoArbolBinario Derecho del subarbol
	 * @return NodoArbolBinario m�s peque�o
	 */
	public NodoArbolBinario<T> obtenerNodoMenor(NodoArbolBinario<T> n){
		while(n.getIzquierdo()!=null) {
			n = n.getIzquierdo();
		}
		return n;
	}
	
	/**
	 * @return the raiz
	 */
	public NodoArbolBinario<T> getRaiz() {
		return raiz;
	}
	/**
	 * @param raiz the raiz to set
	 */
	public void setRaiz(NodoArbolBinario<T> raiz) {
		this.raiz = raiz;
	}
	/**
	 * @return the peso
	 */
	public int getPeso() {
		return peso;
	}
	
	
}
