package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import modelo.Alimento;
import modelo.Conexion;

/**
* Clase que se encarga de los funciones que involucran un alimento
* @version 1.3 4/5/2020
* @author Ruiz Melo Jean Paul
*/
public class AlimentoDAO {
	private Conexion con;
	private Connection connection;

	/**
	* Inicializa la conexion al servidor para el alimento
	*/
	public AlimentoDAO(String jdbcURL, String jdbcUsername, String jdbcPassword) throws SQLException {
		System.out.println(jdbcURL);
		con = new Conexion(jdbcURL, jdbcUsername, jdbcPassword);
	}
	
	/**
	* Toma un alimento y lo agrega al base de datos
	* @param Alimento alimento Alimento a agregar
	* @return Regresa true si fue agregado, y false en cualquier otro caso
	*/
	public boolean agregarAlimento(Alimento alimento) throws SQLException {
		String sql = "INSERT INTO alimento (id_categoria, nombre, descripcion, costo, foto) VALUES (?, ?, ?, ?, ?)";
		System.out.println("hola");
		System.out.println(alimento.getFoto());
		System.out.println("hola");
		con.conectar();
		connection = con.getJdbcConnection();
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setInt(1, alimento.getIdCategoria());
		statement.setString(2, alimento.getNombre());
		statement.setString(3, alimento.getDescripcion());
		statement.setDouble(4, alimento.getCosto());
		statement.setString(5, alimento.getFoto());
		
		System.out.println("hola");
		System.out.println(alimento.getDescripcion());
		
		boolean rowInserted = statement.executeUpdate() > 0;
		statement.close();
		con.desconectar();
		System.out.println("hola2");
		return rowInserted;
		
	}

	/**
	* Regresa una lista de los alimentos que pertenecen a una categoria
	* @param int id_cat Identifacador del categoria
	* @return Regresa una lista de los alimentos que pertenecen a una categoria
	*/
	public List<Alimento> listarAlimentos(int id_cat) throws SQLException {
		System.out.println("IDCATIN: "+ id_cat);
		List<Alimento> listaAlimentos = new ArrayList<Alimento>();
		String sql = "SELECT * FROM alimento WHERE id_categoria= ? ";
		con.conectar();
		connection = con.getJdbcConnection();
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setInt(1, id_cat);
		ResultSet resulSet = statement.executeQuery();
		System.out.println("BD alim ready..");
		System.out.println("Id cat: "+id_cat);
		while (resulSet.next()) {
			int id = resulSet.getInt("id_alimento");
			String nombre = resulSet.getString("nombre");
			System.out.println("alimento: "+ nombre);
			String descripcion = resulSet.getString("descripcion");
			Double costo = resulSet.getDouble("costo");
			String foto = resulSet.getString("foto");
			
			Alimento alimento = new Alimento(nombre, descripcion, costo, id, id_cat, foto);
			listaAlimentos.add(alimento);
		}
		con.desconectar();
		System.out.println("List alim ready..");
		return listaAlimentos;
	}
	
	/**
	* Regresa un alimento dado su identifacador
	* @param int id Identifacador del alimento
	* @return Regresa un alimento que se buscaba por su identifacador
	*/
	public Alimento obtenerPorId(int id, int id_cat) throws SQLException {
		Alimento alimento = null;

		String sql = "SELECT * FROM alimento WHERE id_alimento= ? AND id_categoria=?";
		con.conectar();
		connection = con.getJdbcConnection();
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setInt(1, id);
		statement.setInt(2, id_cat);
		ResultSet res = statement.executeQuery();
		if (res.next()) {
			alimento = new Alimento(res.getString("nombre"), res.getString("descripcion"), 
					res.getDouble("costo"), res.getInt("id_alimento"), res.getInt("id_categoria"), res.getString("foto"));
		}
		res.close();
		con.desconectar();

		return alimento;
	}
		
	/**
	* Actualiza un alimento usando su identifacador
	* @param Alimento alimento Alimento a actualizar
	* @return Regresa true si se pudo actualizar y false en otro caso
	*/
	public boolean editarAlimento(Alimento alimento) throws SQLException {
		boolean rowActualizar = false;
		String sql = "UPDATE alimento SET nombre=?,descripcion=?, costo=?, foto=? WHERE id_alimento=? AND id_categoria=? ";
		con.conectar();
		connection = con.getJdbcConnection();
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setString(1, alimento.getNombre());
		statement.setString(2, alimento.getDescripcion());
		System.out.println(alimento.getCosto());
		statement.setDouble(3, alimento.getCosto());
		statement.setString(4, alimento.getFoto());
		statement.setInt(5, alimento.getIdAlimento());
		statement.setInt(6, alimento.getIdCategoria());
		rowActualizar = statement.executeUpdate() > 0;
		statement.close();
		con.desconectar();
		
		return rowActualizar;
	}
		
	/**
	* Eliminar un alimento usando su identifacador
	* @param Alimento alimento Alimento a eliminar
	* @return Regresa true si se pudo eliminar y false en otro caso
	*/
	public boolean eliminarAlimento(Alimento alimento) throws SQLException {
		boolean rowEliminar = false;
		String sql = "DELETE FROM alimento WHERE id_alimento=?";
		con.conectar();
		connection = con.getJdbcConnection();
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setInt(1, alimento.getIdAlimento());

		rowEliminar = statement.executeUpdate() > 0;
		statement.close();
		con.desconectar();

		return rowEliminar;
	}
}
