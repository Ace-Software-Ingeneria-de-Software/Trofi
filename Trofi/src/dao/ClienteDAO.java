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

public class ClienteDAO {
	private Conexion con;
	private Connection connection;

	public ClienteDAO(String jdbcURL, String jdbcUsername, String jdbcPassword) throws SQLException {
		System.out.println(jdbcURL);
		con = new Conexion(jdbcURL, jdbcUsername, jdbcPassword);
	}
	
	// listar todos los productos
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

	// obtener por correo
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
}