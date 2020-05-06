package controlador;

import java.util.ArrayList;
import java.util.List;

import modelo.Alimento;

/**
 * Clase que controla el carrito de compras.
 * @author Carlos Eduardo Orozco Viveros.
 * @version 1.0
 */
public class Carrito {
	
	private List<Alimento> alimentos; // Lista de alimentos en el carrito.
	private double total; // Costo total de los alimentos del carrito.
	
	/**
	 * Construye un nuevo carrito.
	 */
	public Carrito() {
		this.alimentos = new ArrayList<Alimento>();
		this.total = 0.0;
	}
	
	/**
	 * Agrega un nuevo alimento al carrito.
	 * @param Alimento el alimento que sera agregado.
	 */
	public void agregarAlimento(Alimento alimento) {
		alimentos.add(alimento);
		this.total += alimento.getCosto();
	}
	
	/**
	 * Quita un alimento del carrito.
	 * @param Alimento el alimento que sera eliminado del carrito.
	 */
	public void quitarAlimento(Alimento alimento) {
		for(Alimento actual: alimentos) {
			if(actual.getIdAlimento() == alimento.getIdAlimento()) {
				alimentos.remove(actual);
				this.total -= actual.getCosto();
				break;
			}
		}
	}
	
	/**
	 * Calcua el total del costo de los alimento en el carrito.
	 * @return el costo total de los alimentos.
	 */
	public double calculaTotal() {
		double total = 0.0;
		for(Alimento actual: alimentos) {
			total += actual.getCosto();
		}
		return total;
	}
	
	/**
	 * Metodo auxiliar que muestra en consola los alimentos en el carrito. 
	 */
	public void mostrarCarrito() {
		for(Alimento actual: alimentos) {
			System.out.println(actual.getNombre());
		}
	}
	
	/**
	 * Obtiene los alimentos en el carrito.
	 * @return la lista de alimentos.
	 */
	public List<Alimento> getAlimentos(){
		return this.alimentos;
	}
	
	/**
	 * Regresa el costo total de los alimentos en el carrito
	 * @return costo total de los alimentos.
	 */
	public double getTotal() {
		return this.total;
	}
	
	/**
	 * Re-inicia los atributos del carrito.
	 */
	public void resetCarrito() {
		this.alimentos = new ArrayList<Alimento>();
		this.total = 0.0;
	}
	
	/**
	 * Nos dice si el carrito esta vacio.
	 * @return true si esta vacio, false en otro caso.
	 */
	public boolean estaVacio() {
		return this.alimentos.isEmpty();
	}
}
