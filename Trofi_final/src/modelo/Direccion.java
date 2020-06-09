package modelo;

/**
 * Clase que representa una direccion de un cliente.
 * @author Teresa Becerril Torres.
 * @version 1.0
 */
public class Direccion {
	/* Correo electronico del cliente.*/
	private String correo_e;
	/* Direccion del cliente. */
	private String direccion;
	
	/**
	 * Construye una direccion de un cliente con todas sus propiedades.
	 * @param correo_e El correo electronico del cliente.
	 * @param direccion Una direccion del cliente.
	 */
	public Direccion(String correo_e, String direccion) {
		this.correo_e = correo_e;
		this.direccion = direccion;
	}
	
	/**
	 * Regresa el correo electronico del cliente.
	 * @return el correo electronico del cliente.
	 */
	public String getCorreoE() {
		return correo_e;
	}
	
	/**
	 * Define el correo electronico del cliente.
	 * @param correo_e el nuevo correo electronico del cliente.
	 */
	public void setCorreoE(String correo_e) {
		this.correo_e = correo_e;
	}
	
	/**
	 * Regresa la direccion del cliente.
	 * @return la direccion del cliente.
	 */
	public String getDireccion() {
		return direccion;
	}
	
	/**
	 * Define la direccion del cliente.
	 * @param direccion la nueva direccion del cliente.
	 */
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
}
