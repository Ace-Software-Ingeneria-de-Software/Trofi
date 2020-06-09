package modelo;

/**
 * Una clase que nos ayuda a modelar un administrador
 * @author Quintero Villeda Erik
 */
public class Administrador {
	private String correo_e; //Correo del administrador
	private String nombre; //Nombre del administrador
	private String aPaterno; //Apellido Paterno del administrador
	private String aMaterno; //Apellido Materno del administrador
	private String contrasenia; //Contrasenia del administrador

	/**
	 * Metodo contructor que inicializa un administrador
	 */
	public Administrador() {}
	
	/**
	 * Metodo contructor que inicializa un adminitrador
	 * @param email correo del administrador
	 * @param nombre nombre del administrador
	 * @param apellido1 apellido paterno del administrador
	 * @param apellido2 apellido materno del administrador
	 * @param password contrasenia del administrador
	 */
	public Administrador( String email, String nombre, String apellido1,String apellido2,String password) {
		this.correo_e = email;
		this.nombre=nombre;
		this.aPaterno = apellido1;
		this.aMaterno = apellido2;
		this.contrasenia = password;
	}
	
	/**
	 * Metodo que devuelve el nombre del administrador
	 * @return String el nombre del administrador
	 */
	public String getNombre() {
		return nombre;
	}
	
	/**
	 * Metodo que asigna un nombre al administrador
	 * @param String el nombre del administrador
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	/**
	 * Metodo que devuelve el apellido paterno del administrador
	 * @return String el apellido paterno del administrador
	 */
	public String getApaterno() {
		return aPaterno;
	}
	
	/**
	 * Metodo que asigna un apellido paterno al administrador
	 * @param String el apellido paterno del administrador
	 */
	public void setApaterno(String apellido1) {
		this.aPaterno = apellido1;
	}
	
	/**
	 * Metodo que devuelve el apellido materno del administrador
	 * @return String el apellido materno del administrador
	 */
	public String getAmaterno() {
		return aMaterno;
	}
	
	/**
	 * Metodo que asigna un apellido materno al administrador
	 * @param String el apellido materno del administrador
	 */
	public void setAmaterno(String apellido2) {
		this.aMaterno = apellido2;
	}
	
	/**
	 * Metodo que devuelve el correo del administrador
	 * @return String el correo del administrador
	 */
	public String getCorreo() {
		return correo_e;
	}
	
	/**
	 * Metodo que asigna un correo al administrador
	 * @param String el correo del administrador
	 */
	public void setCorreo(String email) {
		this.correo_e = email;
	}
	
	/**
	 * Metodo que devuelve la contrasenia del administrador
	 * @return String la contrasenia del administrador
	 */
	public String getContrasenia() {
		return contrasenia;
	}
	
	/**
	 * Metodo que asigna una contrasenia al administrador
	 * @param String la contrasenia del administrador
	 */
	public void setContrasenia(String password) {
		this.contrasenia = password;
	}
}