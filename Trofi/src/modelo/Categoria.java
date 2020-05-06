package modelo;

/**
	* Clase del categoria
	* @version 1.3 4/5/2020
	* @author Ruiz Melo Jean Paul
*/
public class Categoria {
	
	private int id; //Identifacador del categoria
	private String nombre; //Nombre del alimento
	
	/**
	* Inicializa el categoria
	* @param String nombre Nombre del categoria
	* @param int id Identifacador del categoria
	*/
	public Categoria(int id, String nombre) {
		this.id = id;
		this.nombre = nombre;
	}
	
	/**
	* @return Regresa el nombre del categoria
	*/
	public String getNombre() {
		return this.nombre;
	}
	
	/**
	* @return Regresa el identifacador del categoria
	*/
	public int getId() {
		return this.id;
	}
	
	/**
	* Actualiza el nombre del alimento
	* @param String nombre Nombre del alimento que se va a actualizar
	*/
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	/**
	* Actualiza el identifacador de la categoria
	* @param int id Identifacador del categoria a que se va actualizar
	*/
	public void setId(int id) {
		this.id = id;
	}
	
}
