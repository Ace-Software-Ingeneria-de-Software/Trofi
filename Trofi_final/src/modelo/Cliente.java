package modelo;

import controlador.Carrito;

/**
 * Clase que representa un cliente.
 * @author Teresa Becerril Torres.
 * @version 1.0
 */
public class Cliente {
	
	/* Nombre del cliente. */
	private String nombre;
	/* Apellido paterno del cliente. */
	private String a_paterno;
	/* Apellido materno del cliente. */
	private String a_materno;
	/* Correo electronico. */
	private String correo_e;
	/* Contrasenia. */
	private String contrasenia;
	/* Numero telefonico. */
	private String telefono;
	/* Carrito del cliente. */
	private Carrito carrito;
	
	/**
	 * Construye un cliente con todas sus propiedades.
	 * @param correo_e El correo electronico del cliente.
	 * @param nombre El nombre del cliente
	 * @param a_paterno El apellido paterno del cliente.
	 * @param a_materno El apellido materno del cliente.
	 * @param contrasenia La contrasenia del cliente.
	 * @param telefono El numero telefonico del cliente.
	 */
	public Cliente(String correo_e, String nombre, String a_paterno, String a_materno, String contrasenia, String telefono) {
		this.nombre = nombre;
		this.a_paterno = a_paterno;
		this.a_materno = a_materno;
		this.correo_e = correo_e;
		this.contrasenia = contrasenia;
		this.telefono = telefono;
		this.carrito = new Carrito();
	}
	
	/**
	 * Regresa el nombre del cliente.
	 * @return el nombre del cliente.
	 */
	public String getNombre() {
		return nombre;
	}
	
	/**
	 * Define el nombre del cliente.
	 * @param nombre el nuevo nombre del cliente.
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	/**
	 * Regresa el apellido paterno del cliente.
	 * @return el apellido paterno del cliente.
	 */
	public String getAPaterno() {
		return a_paterno;
	}
	
	/**
	 * Define el apellido paterno del cliente.
	 * @param a_paterno el nuevo apellido paterno del cliente.
	 */
	public void setAPaterno(String a_paterno) {
		this.a_paterno = a_paterno;
	}
	
	/**
	 * Regresa el apellido materno del cliente.
	 * @return el apellido materno del cliente.
	 */
	public String getAMaterno() {
		return a_materno;
	}
	
	/**
	 * Define el apellido materno del cliente.
	 * @param a_materno el nuevo apellido materno del cliente.
	 */
	public void setAMaterno(String a_materno) {
		this.a_materno = a_materno;
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
	 * Regresa la contrasenia del cliente.
	 * @return la contrasenia del cliente.
	 */
	public String getContrasenia() {
		return contrasenia;
	}
	
	/**
	 * Define la contrasenia del cliente.
	 * @param contrasenia la nueva contrasenia del cliente.
	 */
	public void setContrasenia(String contrasenia) {
		this.contrasenia = contrasenia;
	}
	
	/**
	 * Regresa el numero telefonico del cliente.
	 * @return el numero telefonico del cliente.
	 */
	public String getTelefono() {
		return telefono;
	}
	
	/**
	 * Define el numero telefonico del cliente.
	 * @param telefono el nuevo numero telefonico del cliente.
	 */
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	
	/**
	 * Regresa el carrito del cliente.
	 * @return el carrito del cliente.
	 */
	public Carrito getCarrito() {
		return this.carrito;
	}
}
