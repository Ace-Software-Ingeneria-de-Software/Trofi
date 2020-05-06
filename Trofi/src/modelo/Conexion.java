package modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Clase que se encarga de establecer la conexion con la BD.
 * @author Carlos Eduardo Orozco Viveros.
 * @version 1.0
 */
public class Conexion {
    private Connection jdbcConnection;
    private String jdbcURL;
    private String jdbcUsername;
    private String jdbcPassword;
    
    /**
	 * Construye una conexion.
	 * @param jsbcURL URL de la BD.
     * @param jsbcUsername nombre del usuario de la BD.
     * @param jsbcPassword contraseña del usuario para acceder a la BD.
	 */
    public Conexion(String jdbcURL, String jdbcUsername, String jdbcPassword) {
		this.jdbcURL = jdbcURL;
		this.jdbcUsername = jdbcUsername;
		this.jdbcPassword = jdbcPassword;
	}

    /**
	 * Establece la conexion con la BD.
	 */
	public void conectar() throws SQLException {
        if (jdbcConnection == null || jdbcConnection.isClosed()) {
            try {
                Class.forName("com.mysql.jdbc.Driver");
            } catch (ClassNotFoundException e) {
                throw new SQLException(e);
            }
            jdbcConnection = DriverManager.getConnection(
                                        jdbcURL, jdbcUsername, jdbcPassword);
        }
    }

    /**
	 * Se desconecta de la BD.
	 */
    public void desconectar() throws SQLException {
        if (jdbcConnection != null && !jdbcConnection.isClosed()) {
            jdbcConnection.close();
        }
    }

    /**
	 * Regresa la conexion con la BD.
	 * @return la conexion con la BD.
	 */
	public Connection getJdbcConnection() {
		return jdbcConnection;
	}  
}
