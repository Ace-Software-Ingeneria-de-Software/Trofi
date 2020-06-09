package modelo;

/**
	* Clase del alimento
	* @version 1.3 4/5/2020
	* @author Orozco Viveros Carlos Eduardo
*/
public class Alimento {
	private String nombre; //Nombre del alimento
	private String descripcion; //Descripcion del alimento
	private double costo; //Costo del alimento
	private int idAlimento; //Identifacador del alimento
	private int idCategoria; //Identifacador del categoria que pertenece el alimento
	private String foto; //Ruta a la foto del alimento
	
	/**
	* Inicializa el alimento
	* @param String nombre Nombre del alimento
	* @param String descripcion Descripcion del alimento
	* @param double costo Costo del alimento
	* @param int id Identifacador del alimento
	* @param int cat_id Identifacador del categoria
	*/
	public Alimento(String nombre, String descripcion, double costo , int id, int cat_id, String foto) {
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.costo = costo;
		this.idAlimento = id;
		this.idCategoria = cat_id;
		this.foto = foto;
	}
	
	/**
	* @return Regresa el nombre del alimento
	*/
	public String getNombre() {
		return this.nombre;
	}
	
	/**
	* Actualiza el nombre del alimento
	* @param String nombre Nombre del alimento que se va a actualizar
	*/
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	/**
	* @return Regresa el descripcion del alimento
	*/
	public String getDescripcion() {
		return this.descripcion;
	}
	
	/**
	* Actualiza el descripcion del alimento
	* @param String descripcion Descripcion del alimento que se va a actualizar
	*/
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	/**
	* @return Regresa el costo del alimento
	*/
	public double getCosto() {
		return this.costo;
	}
	
	/**
	* Actualiza el costo del alimento
	* @param double costo Costo del alimento que se va a actualizar
	*/
	public void setCosto(double costo) {
		this.costo = costo;
	}
	
	/**
	* @return Regresa el identifacador del alimento
	*/
	public int getIdAlimento() {
		return this.idAlimento;
	}
	
	/**
	* Actualiza el identifacador del alimento
	* @param int id Identifacador del alimento que se va a actualizar
	*/
	public void setIdAlimento(int id) {
		this.idAlimento = id;
	}
	
	/**
	* @return Regresa el identifacador del categoria que pertenece el alimento
	*/
	public int getIdCategoria() {
		return this.idCategoria;
	}
	
	/**
	* Actualiza a que categoria pertenece el alimento
	* @param int id Identifacador del alimento que se va a actualizar
	*/
	public void setCat(int cat_id) {
		this.idCategoria = cat_id;
	}
	
	/**
	* @return Ruta de la foto del alimento.
	*/
	public String getFoto() {
		return this.foto;
	}
	
	/**
	* Actualiza la foto del alimento
	* @param String foto Ruta de la foto del alimento.
	*/
	public void setFoto(String foto) {
		this.foto = foto;
	}
	
	/**
	Compara si dos alimentos son iguales
	* @param Alimento alimento Alimento a comparar
	* @return Regresa true si los dos alimentos comparten el mismo identificador
	*/
	public boolean equals(Alimento compara) {
		return this.idAlimento == compara.idAlimento;
	}
}
