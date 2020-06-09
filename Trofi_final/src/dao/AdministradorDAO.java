package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import modelo.Administrador;
import modelo.Conexion;

/**
 * Una clase que nos ayuda a obtener informacion de los administradores en la base de datos
 * @author Quintero Villeda Erik
 */
public class AdministradorDAO {
	private Conexion con; //Objeto conexion para conectar la base de datos
	private Connection connection; //Objeto connection para conectar 

	/**
	 * Metodo constructor que inicializa un objeto AdministradorDAO
	 * @param jdbcURL
	 * @param jdbcUsername
	 * @param jdbcPassword
	 */
	public AdministradorDAO(String jdbcURL, String jdbcUsername, String jdbcPassword) throws SQLException {
		System.out.println(jdbcURL);
		con = new Conexion(jdbcURL, jdbcUsername, jdbcPassword);
	}
	
	/**
	 * Metodo que obtiene la lista de administradores que estan en la base de datos
	 * @return la lista de administradores en la base de datos
	 */
	public List<Administrador> listarAdministradores() throws SQLException {
		List<Administrador> listaAdministradores = new ArrayList<Administrador>();
		String sql = "SELECT * FROM administrador";
		con.conectar();
		connection = con.getJdbcConnection();
		Statement statement = connection.createStatement();
		ResultSet resulSet = statement.executeQuery(sql);
		while (resulSet.next()) {
			String correo_e = resulSet.getString("correo_e");
			String nombre = resulSet.getString("nombre");
			String a_paterno = resulSet.getString("a_paterno");
			String a_materno = resulSet.getString("a_materno");
			String contrasenia = resulSet.getString("contrasenia");
			Administrador admin = new Administrador(correo_e, nombre, a_paterno, a_materno, contrasenia);
			listaAdministradores.add(admin);
		}
		con.desconectar();
		return listaAdministradores;
	}

	/**
	 * Metodo que regresa un administrador que esta en la base de datos
	 * @param correo correo a buscar en la base de datos
	 * @return el administrador que tenga el correo proporcionado
	 */
	public Administrador obtenerPorCorreo(String correo) throws SQLException {
		Administrador admin = null;
		String sql = "SELECT * FROM administrador WHERE correo_e = ? ";
		con.conectar();
		connection = con.getJdbcConnection();
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setString(1, correo);
		ResultSet res = statement.executeQuery();
		if (res.next()){
			admin = new Administrador(res.getString("correo_e"), res.getString("nombre"), res.getString("a_paterno"),
					res.getString("a_materno"), res.getString("contrasenia"));
		}
		res.close();
		con.desconectar();
		return admin;
	}
}