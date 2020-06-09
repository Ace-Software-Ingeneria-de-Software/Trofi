package modelo;

/**
 * Una clase que nos ayuda a modelar objetos iniciarSesionIH que contendran los datos obtenidos de la vista
 * @author Quintero Villeda Erik
 */
public class IniciarSesionIH{
	
	private String email; //correo electronico
	private String password; // contrasenia proporcionada
	
	/**
	 * Metodo contructor que inicializa un objeto IniciarSesionIH
	 */
	public IniciarSesionIH(){}
	
	/**
	 * Metodo contructor que inicializa un objeto IniciarSesionIH
	 * @param email correo electronico
	 * @param password contrasenia
	 */
	public IniciarSesionIH (String email, String password){
		super();
		this.email = email;
		this.password = password;
	}

	/**
	 * Metodo que devuelve un correo electronico
	 * @return String un correo electronico
	 */
	public String getEmail(){
		return email;
	}

	/**
	 * Metodo que asigna un correo electronico
	 * @param email un correo electronico
	 */
	public void setEmail(String email){
		this.email = email;
	}

	/**
	 * Metodo que devuelve una contrasenia
	 * @return String una contrasenia
	 */
	public String getPassword(){
		return password;
	}

	/**
	 * Metodo que asigna una contrasenia
	 * @param password una contrasenia
	 */
	public void setPassword(String password){
		this.password = password;
	}	
}