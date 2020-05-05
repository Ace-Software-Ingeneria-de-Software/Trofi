package dao;
/**
	* Clase que se encarga de los funciones que involucran un alimento
	* @version 1.3 4/5/2020
	* @author Ruiz Melo Jean Paul
*/
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import modelo.Categoria;
import modelo.Conexion;

public class CategoriaDAO {
	private Conexion con;
	private Connection connection;

	/**
	* Inicializa la conexion al servidor para el categoria
	*/
	public CategoriaDAO(String jdbcURL, String jdbcUsername, String jdbcPassword) throws SQLException {
		System.out.println(jdbcURL);
		con = new Conexion(jdbcURL, jdbcUsername, jdbcPassword);
	}

	/**
	* Toma un categoria y lo agrega al base de datos
	* @param Categoria categoria Categoria a agregar
	* @return Regresa true si fue agregado, y false en cualquier otro caso
	*/
		public boolean agregarCategoria(Categoria categoria) throws SQLException {
			String sql = "INSERT INTO categoria (id_categoria, nombre) VALUES (?, ?)";
			con.conectar();
			connection = con.getJdbcConnection();
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setInt(1, categoria.getId());
			statement.setString(2, categoria.getNombre());
			boolean rowInserted = statement.executeUpdate() > 0;
			statement.close();
			con.desconectar();
			return rowInserted;

		}

		/**
		* Regresa una lista de los categorias
		* @return Regresa una lista de los categorias
		*/
	public List<Categoria> listarCategorias() throws SQLException {

		List<Categoria> listaCategorias = new ArrayList<Categoria>();
		String sql = "SELECT * FROM categoria";
		con.conectar();
		connection = con.getJdbcConnection();
		Statement statement = connection.createStatement();
		ResultSet resulSet = statement.executeQuery(sql);
		System.out.println("BD cat ready..");

		while (resulSet.next()) {
			int id = resulSet.getInt("id_categoria");
			String nombre = resulSet.getString("nombre");
			Categoria categoria = new Categoria(id, nombre);
			listaCategorias.add(categoria);
		}
		con.desconectar();
		System.out.println("List cat ready..");
		return listaCategorias;
	}

	/**
	* Regresa un categoria dado su identifacador
	* @param int id Identifacador del categoria
	* @return Regresa un categoria que se buscaba por su identifacador
	*/
	public Categoria obtenerPorId(int id) throws SQLException {
		Categoria categoria = null;

		String sql = "SELECT * FROM categoria WHERE id_categoria= ? ";
		con.conectar();
		connection = con.getJdbcConnection();
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setInt(1, id);

		ResultSet res = statement.executeQuery();
		if (res.next()) {
			categoria = new Categoria(res.getInt("id_categoria"), res.getString("nombre"));
		}
		res.close();
		con.desconectar();

		return categoria;
	}

	/**
	* Actualiza un categoria usando su identifacador
	* @param Categoria categoria Categoria a actualizar
	* @return Regresa true si se pudo actualizar y false en otro caso
	*/
		public boolean editarCategoria(Categoria categoria) throws SQLException {
			boolean rowActualizar = false;
			String sql = "UPDATE categoria SET nombre=? WHERE id_categoria=?";
			con.conectar();
			connection = con.getJdbcConnection();
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, categoria.getNombre());
			statement.setInt(2, categoria.getId());
			rowActualizar = statement.executeUpdate() > 0;
			statement.close();
			con.desconectar();
			return rowActualizar;
		}

		/**
		* Eliminar un categoria usando su identifacador
		* @param Categoria categoria Categoria a eliminar
		* @return Regresa true si se pudo eliminar y false en otro caso
		*/
		public boolean eliminarCategoria(Categoria categoria) throws SQLException {
			boolean rowEliminar = false;
			String sql = "DELETE FROM categoria WHERE id_categoria=?";
			con.conectar();
			connection = con.getJdbcConnection();
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setInt(1, categoria.getId());

			rowEliminar = statement.executeUpdate() > 0;
			statement.close();
			con.desconectar();

			return rowEliminar;
		}
}
