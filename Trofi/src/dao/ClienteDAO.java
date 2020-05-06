package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import modelo.Cliente;
import modelo.Conexion;

/**
 * Clase que se conecta con la base de datos y obtiene datos 
 * de la tabla Cliente.
 * @author Teresa Becerril Torres.
 * @version 1.0
 */
public class ClienteDAO {
	private Conexion con;
	private Connection connection;
	
	/**
	 * Construye un clienteDAO con todas sus propiedades.
	 * @param jdbcURL direccion de donde se encuentra la base de datos.
	 * @param jdbcUsername nombre del usuario.
	 * @param jdbcPassword contrasenia del usuario.
	 * @throws SQLException si ocurre un error al conectarse con la base de datos u otro error.
	 */
	public ClienteDAO(String jdbcURL, String jdbcUsername, String jdbcPassword) throws SQLException {
		System.out.println(jdbcURL);
		con = new Conexion(jdbcURL, jdbcUsername, jdbcPassword);
	}
	
	/**
	 * Lista todos los clientes de la base de datos.
	 * @return una lista con todos los clientes en la base de datos.
	 * @throws SQLException si ocurre un error al conectarse con la base de datos u otro error. 
	 */
	public List<Cliente> listarClientes() throws SQLException {
		List<Cliente> listaClientes = new ArrayList<Cliente>();
		String sql = "SELECT * FROM cliente";
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
			String telefono = resulSet.getString("telefono");
			Cliente cliente = new Cliente(correo_e, nombre, a_paterno, a_materno, contrasenia, telefono);
			listaClientes.add(cliente);
		}
		con.desconectar();
		return listaClientes;
	}

	/**
	 * Obtiene los datos de un cliente a partir del correo electronico.
	 * @param correo el correo del cliente del que se quiere obtener sus datos.
	 * @return el cliente con los datos correspondientes.
	 * @throws SQLException si ocurre un error al conectarse con la base de datos u otro error. 
	 */
	public Cliente obtenerPorCorreo(String correo) throws SQLException {
		Cliente cliente = null;
		String sql = "SELECT * FROM cliente WHERE correo_e = ? ";
		con.conectar();
		connection = con.getJdbcConnection();
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setString(1, correo);
		ResultSet res = statement.executeQuery();
		if (res.next()){
			String correo_e = res.getString("correo_e");
			String nombre = res.getString("nombre");
			String a_paterno = res.getString("a_paterno");
			String a_materno = res.getString("a_materno");
			String contrasenia = res.getString("contrasenia");
			String telefono = res.getString("telefono");
			cliente = new Cliente(correo_e, nombre, a_paterno, a_materno, contrasenia, telefono);
			System.out.println("lo tengo");
		}
		
		res.close();
		con.desconectar();
		return cliente;
	}
	
	/**
	 * Agrega un cliente a la base de datos.
	 * @param cliente el cliente que se quiere agregar a la base de datos.
	 * @return un booleano que indica que la instruccion fue ejecutada.
	 * @throws SQLException si ocurre un error al conectarse con la base de datos u otro error. 
	 */
		public boolean agregarCuenta(Cliente cliente) throws SQLException {
			String sql = "INSERT INTO cliente (correo_e, nombre, a_paterno, a_materno, contrasenia, telefono) VALUES (?, ?, ?, ?, SHA(?), ?)";
			System.out.println("conectando");
			con.conectar();
			connection = con.getJdbcConnection();
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, cliente.getCorreoE());
			statement.setString(2, cliente.getNombre());
			statement.setString(3, cliente.getAPaterno());
			statement.setString(4, cliente.getAMaterno());
			statement.setString(5, cliente.getContrasenia());
			statement.setString(6, cliente.getTelefono());
			System.out.println("agregado");
			boolean agregar = statement.executeUpdate() > 0;
			statement.close();
			con.desconectar();
			return agregar;
		}
		
		/**
		 * Actualiza los datos de un cliente en la base de datos.
		 * @param cliente el cliente que contiene los datos actualizados.
		 * @return un booleano que indica que la instruccion fue ejecutada.
		 * @throws SQLException si ocurre un error al conectarse con la base de datos u otro error. 
		 */
		public boolean actualizarCuenta(Cliente cliente) throws SQLException {
			boolean actualizar = false;
			String sql = "UPDATE cliente SET nombre = ?, a_paterno = ?, a_materno = ?, telefono = ? WHERE correo_e = ?";
			con.conectar();
			connection = con.getJdbcConnection();
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, cliente.getNombre());
			statement.setString(2, cliente.getAPaterno());
			statement.setString(3, cliente.getAMaterno());
			statement.setString(4, cliente.getTelefono());
			statement.setString(5, cliente.getCorreoE());
			actualizar = statement.executeUpdate() > 0;
			statement.close();
			con.desconectar();
			return actualizar;
		}
		
		/**
		 * Obtiene los datos de un cliente a partir del correo electronico.
		 * @param correo_e  el correo del cliente del que se quiere obtener sus datos.
		 * @return un booleano que indica que la instruccion fue ejecutada.
		 * @throws SQLException si ocurre un error al conectarse con la base de datos u otro error. 
		 */
		public Cliente obtenerCuenta(String correo_e) throws SQLException {
			Cliente cliente = null;
			String sql = "SELECT * FROM cliente WHERE correo_e = ?";
			con.conectar();
			connection = con.getJdbcConnection();
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, correo_e);
			
			ResultSet res = statement.executeQuery();
			if(res.next()) {
				cliente = new Cliente(res.getString("nombre"), res.getString("a_paterno"), res.getString("a_materno"), 
									  res.getString("correo_e"), res.getString("contrasenia"), res.getString("telefono"));
			}
			res.close();
			con.desconectar();
			return cliente;
		}
}