package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import modelo.Direccion;
import modelo.Conexion;

/**
 * Clase que se conecta con la base de datos y obtiene datos 
 * de la tabla Direccion.
 * @author Teresa Becerril Torres.
 * @vision 1.0
 */
public class DireccionDAO {
	private Conexion con;
	private Connection connection;
	
	/**
	 * Construye una direccionDAO con todas sus propiedades.
	 * @param jdbcURL direccion de donde se encuentra la base de datos.
	 * @param jdbcUsername nombre del usuario.
	 * @param jdbcPassword contrasenia del usuario.
	 * @throws SQLException si ocurre un error al conectarse con la base de datos u otro error.
	 */
	public DireccionDAO(String jdbcURL, String jdbcUsername, String jdbcPassword) throws SQLException {
		System.out.println(jdbcURL);
		con = new Conexion(jdbcURL, jdbcUsername, jdbcPassword);
	}
	
	/**
	 * Agregar una direccion a la base de datos.
	 * @param direccion la direccion que se va agregar.
	 * @return un booleano que indica que la instruccion fue ejecutada.
	 * @throws SQLException si ocurre un error al conectarse con la base de datos u otro error.
	 */
	public boolean agregarDireccion(Direccion direccion) throws SQLException {
		String sql = "INSERT INTO direccion_cliente (correo_e, direccion) VALUES (?, ?)";
		System.out.println("Conectando");
		con.conectar();
		connection = con.getJdbcConnection();
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setString(1, direccion.getCorreoE());
		statement.setString(2, direccion.getDireccion());
		System.out.println("Agregando nueva direccion...");
		boolean agregar =  statement.executeUpdate() > 0;
		statement.close();
		con.desconectar();
		return agregar;
	}
	
	/**
	 * Lista todas las direcciones de un cliente.
	 * @param correo_e el correo electronico del cliente del que se quiere obtener las direcciones.
	 * @return una lista con todos las direcciones de un cliente.
	 * @throws SQLException si ocurre un error al conectarse con la base de datos u otro error.
	 */
	public List<Direccion> obtenerDirecciones(String correo_e) throws SQLException {
		List<Direccion> direcciones = new ArrayList<Direccion>();
		String sql = "SELECT * FROM direccion_cliente WHERE correo_e = ?";
		con.conectar();
		connection = con.getJdbcConnection();
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setString(1, correo_e);
		ResultSet resultSet = statement.executeQuery();
		while(resultSet.next()) {
			String correo = resultSet.getString("correo_e");
			String direccion = resultSet.getString("direccion");
			Direccion dir = new Direccion(correo, direccion);
			direcciones.add(dir);
		}
		con.desconectar();
		return direcciones;
	}
	
	/**
	 * Eliminar una direccion de la base de datos.
	 * @param direccion la direccion que se quiere eliminar.
	 * @return un booleano que indica que la instruccion fue ejecutada.
	 * @throws SQLException si ocurre un error al conectarse con la base de datos u otro error.
	 */
	public boolean eliminarDireccion(Direccion direccion) throws SQLException {
		boolean eliminar = false;
		String sql = "DELETE FROM direccion_cliente WHERE correo_e = ? and direccion = ?";
		con.conectar();
		connection = con.getJdbcConnection();
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setString(1, direccion.getCorreoE());
		statement.setString(2, direccion.getDireccion());
		eliminar = statement.executeUpdate() > 0;
		statement.close();
		con.desconectar();
		return eliminar;
	}
}
