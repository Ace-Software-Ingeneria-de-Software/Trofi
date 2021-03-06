package controlador;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.ClienteDAO;
import dao.DireccionDAO;
import modelo.Cliente;
import modelo.Direccion;

/**
 * Clase representa el controlador de 
 * cuenta cliente.
 * @author Teresa Becerril Torres.
 * @version 1.0
 */
@WebServlet("/cuentaCliente")
public class CuentaCliente extends HttpServlet {
	private static final long serialVersionUID = 1L;
	ClienteDAO clienteDAO;
	DireccionDAO direccionDAO;
	String correo;
	
	/**
	 * Inicia el servidor.
	 */
	public void init() {
		String jdbcURL = getServletContext().getInitParameter("jdbcURL");
		String jdbcUsername = getServletContext().getInitParameter("jdbcUsername");
		String jdbcPassword = getServletContext().getInitParameter("jdbcPassword");
		try { 
			clienteDAO = new ClienteDAO(jdbcURL, jdbcUsername, jdbcPassword);
			direccionDAO = new DireccionDAO(jdbcURL, jdbcUsername, jdbcPassword);
		} catch (Exception e) { 
			
		}
	}
	
	/**
	 * Construye el controlador de cuenta cliente.
	 * @see HttpServlet#HttpServlet()
	 */
	public CuentaCliente() {
		super();
	}
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 * @param request proporciona informacion de solicitud para servlets HTTP.
	 * @param response proporciona una funcionalidad específica de HTTP al enviar una respuesta.
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("Hola ...");
		String action = request.getParameter("action");
		try {
			switch (action) {
			case "index":
				index(request, response);
				break;
			case "nuevaCuenta":
				nuevaCuenta(request, response);
				break;
			case "crearCuenta":
				crearCuenta(request, response);
				break;
			case "inicio":
				inicio(request, response);
				break;
			case "mostrarPerfil":
				mostrarPerfil(request, response);
				break;
			case "mostrarActualizarCuenta":
				mostrarActualizarCuenta(request, response);
				break;
			case "actualizarCuenta":
				actualizarCuenta(request, response);
				break;
			case "direcciones":
				direcciones(request, response);
				break;
			case "nuevaDireccion":
				nuevaDireccion(request, response);
				break;
			case "agregarDireccion":
				agregarDireccion(request, response);
				break;
			case "agregarDireccionPedido":
				agregarDireccionPedido(request, response);
				break;
			case "eliminarDireccion":
				eliminarDireccion(request, response);
				break;
			default:
				break;
			}
		} catch (SQLException e) {
			e.getStackTrace();
		}
	}
	
	/** 
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 * @param request proporciona informacion de solicitud para servlets HTTP.
	 * @param response proporciona una funcionalidad específica de HTTP al enviar una respuesta.
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("Hola ....");
		doGet(request, response);
	}
	
	/**
	 * Metodo para manejar la accion index.
	 * @param request proporciona informacion de solicitud para servlets HTTP.
	 * @param response proporciona una funcionalidad específica de HTTP al enviar una respuesta.
	 * @throws SQLException si ocurre un error al conectarse con la base de datos u otro error.
	 * @throws ServletException si ocurre un error en el servlet.
	 * @throws IOException si ocurre un error de entrada/salida.
	 */
	private void index(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
		RequestDispatcher dispatcher= request.getRequestDispatcher("index.jsp");
		dispatcher.forward(request, response);
	}
	
	/**
	 * Metodo para manejar la accion nuevaCuenta.
	 * @param request proporciona informacion de solicitud para servlets HTTP.
	 * @param response proporciona una funcionalidad específica de HTTP al enviar una respuesta.
	 * @throws SQLException si ocurre un error al conectarse con la base de datos u otro error.
	 * @throws ServletException si ocurre un error en el servlet.
	 * @throws IOException si ocurre un error de entrada/salida.
	 */
	private void nuevaCuenta(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
		RequestDispatcher dispatcher= request.getRequestDispatcher("/jsp/CuentaCliente/crearCuenta.jsp");
		dispatcher.forward(request, response);
	}
	
	/**
	 * Metodo para manejar la accion crearCuenta.
	 * @param request proporciona informacion de solicitud para servlets HTTP.
	 * @param response proporciona una funcionalidad específica de HTTP al enviar una respuesta.
	 * @throws SQLException si ocurre un error al conectarse con la base de datos u otro error.
	 * @throws ServletException si ocurre un error en el servlet.
	 * @throws IOException si ocurre un error de entrada/salida.
	 */
	private void crearCuenta(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
		Cliente cliente = new Cliente(request.getParameter("correo_e"), request.getParameter("nombre"), request.getParameter("a_paterno"), request.getParameter("a_materno"), request.getParameter("contrasenia"), request.getParameter("telefono"));
		clienteDAO.agregarCuenta(cliente);
		correo = cliente.getCorreoE();
		Direccion direccion = new Direccion(request.getParameter("correo_e"), request.getParameter("direccion"));
		direccionDAO.agregarDireccion(direccion);
		String mensaje = "Creación de cuenta exitosa"; 
		RequestDispatcher dispatcher = request.getRequestDispatcher("/jsp/CuentaCliente/mensaje.jsp");
		request.setAttribute("correo", correo);
		request.setAttribute("mensaje", mensaje);
		dispatcher.forward(request, response);
	}
	
	/**
	 * Metodo para manejar la accion inicio.
	 * @param request proporciona informacion de solicitud para servlets HTTP.
	 * @param response proporciona una funcionalidad específica de HTTP al enviar una respuesta.
	 * @throws SQLException si ocurre un error al conectarse con la base de datos u otro error.
	 * @throws ServletException si ocurre un error en el servlet.
	 * @throws IOException si ocurre un error de entrada/salida.
	 */
	private void inicio(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
		RequestDispatcher dispatcher= request.getRequestDispatcher("/jsp/CuentaCliente/inicio.jsp");
		dispatcher.forward(request, response);
	}
	
	/**
	 * Metodo para manejar la accion mostrarPerfil.
	 * @param request proporciona informacion de solicitud para servlets HTTP.
	 * @param response proporciona una funcionalidad específica de HTTP al enviar una respuesta.
	 * @throws SQLException si ocurre un error al conectarse con la base de datos u otro error.
	 * @throws ServletException si ocurre un error en el servlet.
	 * @throws IOException si ocurre un error de entrada/salida.
	 */
	private void mostrarPerfil(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
		if(request.getParameter("correo") != null) {
			correo = request.getParameter("correo");
		}
		RequestDispatcher dispatcher = request.getRequestDispatcher("/jsp/CuentaCliente/perfilCliente.jsp");
        Cliente cliente = clienteDAO.obtenerPorCorreo(correo);
        request.setAttribute("nombre", cliente.getNombre());
        request.setAttribute("paterno", cliente.getAPaterno());
        request.setAttribute("materno", cliente.getAMaterno());
        request.setAttribute("correo", cliente.getCorreoE());
        request.setAttribute("telefono", cliente.getTelefono());
        dispatcher.forward(request, response);
	}
	
	/**
	 * Metodo para manejar la accion actualizarCuentaCliente.
	 * @param request proporciona informacion de solicitud para servlets HTTP.
	 * @param response proporciona una funcionalidad específica de HTTP al enviar una respuesta.
	 * @throws SQLException si ocurre un error al conectarse con la base de datos u otro error.
	 * @throws ServletException si ocurre un error en el servlet.
	 * @throws IOException si ocurre un error de entrada/salida.
	 */
	private void mostrarActualizarCuenta(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
		Cliente cliente = clienteDAO.obtenerPorCorreo(correo);
        request.setAttribute("nombre", cliente.getNombre());
        request.setAttribute("paterno", cliente.getAPaterno());
        request.setAttribute("materno", cliente.getAMaterno());
        request.setAttribute("telefono", cliente.getTelefono());
		RequestDispatcher dispatcher= request.getRequestDispatcher("/jsp/CuentaCliente/actualizarCuenta.jsp");
		dispatcher.forward(request, response);
	}
	
	/**
	 * Metodo para manejar la accion actualizarCuenta.
	 * @param request proporciona informacion de solicitud para servlets HTTP.
	 * @param response proporciona una funcionalidad específica de HTTP al enviar una respuesta.
	 * @throws SQLException si ocurre un error al conectarse con la base de datos u otro error.
	 * @throws ServletException si ocurre un error en el servlet.
	 * @throws IOException si ocurre un error de entrada/salida.
	 */
	private void actualizarCuenta(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
		Cliente cliente = new Cliente(correo, request.getParameter("nombre"), request.getParameter("paterno"), request.getParameter("materno"), " ", request.getParameter("telefono"));
		clienteDAO.actualizarCuenta(cliente);
		String mensaje = "Actualización de cuenta exitosa"; 
		RequestDispatcher dispatcher = request.getRequestDispatcher("/jsp/CuentaCliente/mensaje.jsp");
		request.setAttribute("correo", correo);
		request.setAttribute("mensaje", mensaje);
		dispatcher.forward(request, response);
	}
	
	/**
	 * Metodo para manejar la accion direcciones.
	 * @param request proporciona informacion de solicitud para servlets HTTP.
	 * @param response proporciona una funcionalidad específica de HTTP al enviar una respuesta.
	 * @throws SQLException si ocurre un error al conectarse con la base de datos u otro error.
	 * @throws ServletException si ocurre un error en el servlet.
	 * @throws IOException si ocurre un error de entrada/salida.
	 */
	private void direcciones(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("/jsp/CuentaCliente/direcciones.jsp");
        List<Direccion> listaDirecciones= direccionDAO.obtenerDirecciones(correo);
		request.setAttribute("lista", listaDirecciones);
		dispatcher.forward(request, response);
	}
	
	/**
	 * Metodo para manejar la accion nuevaDireccion.
	 * @param request proporciona informacion de solicitud para servlets HTTP.
	 * @param response proporciona una funcionalidad específica de HTTP al enviar una respuesta.
	 * @throws SQLException si ocurre un error al conectarse con la base de datos u otro error.
	 * @throws ServletException si ocurre un error en el servlet.
	 * @throws IOException si ocurre un error de entrada/salida.
	 */
	private void nuevaDireccion(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
		boolean desdePedido = Boolean.parseBoolean(request.getParameter("desdePedido"));
		String sesionCorreo = request.getParameter("correo");
		if(sesionCorreo != null) {
			correo = sesionCorreo;
		}
		System.out.println("desde pedido valor: " + desdePedido);
		if(desdePedido) {
			System.out.println("desde pedido cuentacliente");
			RequestDispatcher dispatcher= request.getRequestDispatcher("/jsp/CuentaCliente/agregarDireccionPedido.jsp");
			dispatcher.forward(request, response);
		}else {
			System.out.println("desde cuentacliente");
			RequestDispatcher dispatcher= request.getRequestDispatcher("/jsp/CuentaCliente/agregarDireccion.jsp");
			dispatcher.forward(request, response);
		}
		
	}
	
	/**
	 * Metodo para manejar la accion agregarDireccion.
	 * @param request proporciona informacion de solicitud para servlets HTTP.
	 * @param response proporciona una funcionalidad específica de HTTP al enviar una respuesta.
	 * @throws SQLException si ocurre un error al conectarse con la base de datos u otro error.
	 * @throws ServletException si ocurre un error en el servlet.
	 * @throws IOException si ocurre un error de entrada/salida.
	 */
	private void agregarDireccion(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
		Direccion direccion = new Direccion(correo, request.getParameter("direccion"));
		direccionDAO.agregarDireccion(direccion);
		String mensaje = "Direccion agregada con exito"; 
		RequestDispatcher dispatcher = request.getRequestDispatcher("/jsp/CuentaCliente/mensaje.jsp");
		request.setAttribute("correo", correo);
		request.setAttribute("mensaje", mensaje);
		dispatcher.forward(request, response);
	}
	
	/**
	 * Metodo para manejar la accion agregarDireccionPedido.
	 * @param request proporciona informacion de solicitud para servlets HTTP.
	 * @param response proporciona una funcionalidad específica de HTTP al enviar una respuesta.
	 * @throws SQLException si ocurre un error al conectarse con la base de datos u otro error.
	 * @throws ServletException si ocurre un error en el servlet.
	 * @throws IOException si ocurre un error de entrada/salida.
	 */
	private void agregarDireccionPedido(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
		Direccion direccion = new Direccion(correo, request.getParameter("direccion"));
		System.out.println("nueva dir: "+ direccion.getDireccion());
		direccionDAO.agregarDireccion(direccion);
		String mensaje = "Direccion agregada con exito"; 
		RequestDispatcher dispatcher = request.getRequestDispatcher("/jsp/CuentaCliente/mensaje.jsp");
		request.setAttribute("correo", correo);
		request.setAttribute("mensaje", mensaje);
		request.setAttribute("desdePedido", true);
		request.setAttribute("direccion", direccion.getDireccion());
		dispatcher.forward(request, response);
	}
	
	/**
	 * Metodo para manejar la accion eliminarDireccion. 
	 * @param request proporciona informacion de solicitud para servlets HTTP.
	 * @param response proporciona una funcionalidad específica de HTTP al enviar una respuesta.
	 * @throws SQLException si ocurre un error al conectarse con la base de datos u otro error.
	 * @throws ServletException si ocurre un error en el servlet.
	 * @throws IOException si ocurre un error de entrada/salida.
	 */
	private void eliminarDireccion(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
		Direccion direccion = new Direccion(correo, request.getParameter("direccion"));
		direccionDAO.eliminarDireccion(direccion);
		mostrarPerfil(request, response);
	}
	
}
