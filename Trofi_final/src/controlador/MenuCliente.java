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

import dao.AlimentoDAO;
import dao.CategoriaDAO;
import dao.ClienteDAO;
import dao.DireccionDAO;
import dao.OrdenDAO;
import modelo.Alimento;
import modelo.Categoria;
import modelo.Cliente;
import modelo.Direccion;

/**
 * Clase que controla el menu presentado al cliente, 
 * el carrito de compras y la creacion de pedidos.
 * @author Carlos Eduardo Orozco Viveros.
 * @version 1.0
 */
@WebServlet("/menuCliente")
public class MenuCliente extends HttpServlet {
	private static final long serialVersionUID = 1L;
	AlimentoDAO alimentoDAO;
	CategoriaDAO categoriaDAO;
	DireccionDAO direccionDAO;
	OrdenDAO ordenDAO;
	ClienteDAO clienteDAO;
	Cliente cliente;
	Carrito pedido;

	/**
	 * Inicia el servidor.
	 */
	public void init() {
		String jdbcURL = getServletContext().getInitParameter("jdbcURL");
		String jdbcUsername = getServletContext().getInitParameter("jdbcUsername");
		String jdbcPassword = getServletContext().getInitParameter("jdbcPassword");
		try {

			alimentoDAO = new AlimentoDAO(jdbcURL, jdbcUsername, jdbcPassword);
			direccionDAO = new DireccionDAO(jdbcURL, jdbcUsername, jdbcPassword);
			ordenDAO = new OrdenDAO(jdbcURL, jdbcUsername, jdbcPassword);
			categoriaDAO = new CategoriaDAO(jdbcURL, jdbcUsername, jdbcPassword);
			clienteDAO = new ClienteDAO(jdbcURL, jdbcUsername, jdbcPassword);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	/**
	 * Construye el controlador del menu del cliente.
	 * @see HttpServlet#HttpServlet()
	 */
	public MenuCliente() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 * @param request proporciona informacion de solicitud para servlets HTTP.
	 * @param response proporciona una funcionalidad específica de HTTP al enviar una respuesta.
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("Hola Servlet..");
		String action = request.getParameter("action");
		System.out.println(action);
		try {
			switch (action) {
			case "index":
				index(request, response);
				break;
			case "sesionIniciada":
				setSesion(request, response);
				break;
			case "cerrarSesion":
				cerrarSesion(request, response);
				break;
			case "mostrarCategorias":
				mostrarCategorias(request, response);
				break;
			case "mostrarAlimentos":
				mostrarAlimentos(request, response);
				break;
			case "agregar":
				agregar(request, response);
				break;
			case "quitar":
				quitar(request, response);
				break;
			case "carrito":
				abrirCarrito(request, response);
				break;
			case "confirmarPedido":
				confirmarPedido(request, response);
				break;
			case "crearPedido":
				crearPedido(request, response);
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
		System.out.println("Hola Servlet..");
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
	private void index (HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException{
		//RequestDispatcher dispatcher= request.getRequestDispatcher("/jsp/ComprarComida/menuCliente.jsp");
		//dispatcher.forward(request, response);
		mostrarCategorias(request, response);
	}

	/**
	 * Metodo para manejar la accion sesionIniciada.
	 * @param request proporciona informacion de solicitud para servlets HTTP.
	 * @param response proporciona una funcionalidad específica de HTTP al enviar una respuesta.
	 * @throws SQLException si ocurre un error al conectarse con la base de datos u otro error.
	 * @throws ServletException si ocurre un error en el servlet.
	 * @throws IOException si ocurre un error de entrada/salida.
	 */
	private void setSesion (HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException{
		String sesionCliente = request.getParameter("sesionCliente");
		System.out.println("Sesion: "+ sesionCliente);
		if(sesionCliente != null) {
			System.out.println("Cliente: "+ sesionCliente);	
			cliente = clienteDAO.obtenerPorCorreo(sesionCliente);
		}
		boolean desdePedido = Boolean.parseBoolean(request.getParameter("desdePedido"));
		if(desdePedido) {
			System.out.println("desde pedido menu cliente");
			String direccion = request.getParameter("direccion");
			request.setAttribute("direccion", direccion);
			crearPedido(request, response);
		}else {
			System.out.println("Vamos a menu cliente");
			request.setAttribute("sesionCliente", cliente.getCorreoE());
			mostrarCategorias(request, response);
		}
		
	}
	
	/**
	 * Metodo para manejar la cerrarSesion.
	 * @param request proporciona informacion de solicitud para servlets HTTP.
	 * @param response proporciona una funcionalidad específica de HTTP al enviar una respuesta.
	 * @throws SQLException si ocurre un error al conectarse con la base de datos u otro error.
	 * @throws ServletException si ocurre un error en el servlet.
	 * @throws IOException si ocurre un error de entrada/salida.
	 */
	private void cerrarSesion (HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException{
		cliente = null;
		RequestDispatcher dispatcher= request.getRequestDispatcher("/index.jsp");
		dispatcher.forward(request, response);
	}
	
	/**
	 * Metodo para manejar la accion mostrarCategorias.
	 * @param request proporciona informacion de solicitud para servlets HTTP.
	 * @param response proporciona una funcionalidad específica de HTTP al enviar una respuesta.
	 * @throws SQLException si ocurre un error al conectarse con la base de datos u otro error.
	 * @throws ServletException si ocurre un error en el servlet.
	 * @throws IOException si ocurre un error de entrada/salida.
	 */
	private void mostrarCategorias(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException , ServletException{
		RequestDispatcher dispatcher = request.getRequestDispatcher("/jsp/ComprarComida/categorias.jsp");
		System.out.println("dispatcher ready..");
		List<Categoria> listaCategorias= categoriaDAO.listarCategorias();
		request.setAttribute("sesionCliente", cliente.getCorreoE());
		request.setAttribute("lista", listaCategorias);
		System.out.println("regreso..");
		dispatcher.forward(request, response);
	}
	
	/**
	 * Metodo para manejar la accion mostrarAlimentos.
	 * @param request proporciona informacion de solicitud para servlets HTTP.
	 * @param response proporciona una funcionalidad específica de HTTP al enviar una respuesta.
	 * @throws SQLException si ocurre un error al conectarse con la base de datos u otro error.
	 * @throws ServletException si ocurre un error en el servlet.
	 * @throws IOException si ocurre un error de entrada/salida.
	 */
	private void mostrarAlimentos(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException , ServletException{
		int id_cat = Integer.parseInt(request.getParameter("id"));
		RequestDispatcher dispatcher = request.getRequestDispatcher("/jsp/ComprarComida/alimentos.jsp");
		List<Alimento> listaAlimentos= alimentoDAO.listarAlimentos(id_cat);
		Categoria categoria = categoriaDAO.obtenerPorId(id_cat);
		request.setAttribute("categoria", categoria.getNombre());
		request.setAttribute("sesionCliente", cliente.getCorreoE());
		request.setAttribute("lista", listaAlimentos);
		request.setAttribute("id_cat", id_cat);
		dispatcher.forward(request, response);
	}
	
	/**
	 * Metodo para manejar la accion agregar.
	 * @param request proporciona informacion de solicitud para servlets HTTP.
	 * @param response proporciona una funcionalidad específica de HTTP al enviar una respuesta.
	 * @throws SQLException si ocurre un error al conectarse con la base de datos u otro error.
	 * @throws ServletException si ocurre un error en el servlet.
	 * @throws IOException si ocurre un error de entrada/salida.
	 */
	private void agregar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
		String mensaje = "Alimento agregado al carrito de compras con exito!!!";
		System.out.println("Error: " + request.getParameter("id_cat"));
		int id_cat = Integer.parseInt(request.getParameter("id_cat"));
		Alimento alimento = alimentoDAO.obtenerPorId(Integer.parseInt(request.getParameter("id")), id_cat);
		System.out.println(alimento.getNombre());
		cliente.getCarrito().agregarAlimento(alimento);
		//carrito.mostrarCarrito();
		RequestDispatcher dispatcher = request.getRequestDispatcher("/jsp/ComprarComida/mensaje.jsp");
		request.setAttribute("id_cat", id_cat);
		request.setAttribute("mensaje", mensaje);
		dispatcher.forward(request, response);
	}
	
	/**
	 * Metodo para manejar la accion quitar.
	 * @param request proporciona informacion de solicitud para servlets HTTP.
	 * @param response proporciona una funcionalidad específica de HTTP al enviar una respuesta.
	 * @throws SQLException si ocurre un error al conectarse con la base de datos u otro error.
	 * @throws ServletException si ocurre un error en el servlet.
	 * @throws IOException si ocurre un error de entrada/salida.
	 */
	private void quitar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
		Alimento alimento = alimentoDAO.obtenerPorId(Integer.parseInt(request.getParameter("id")),Integer.parseInt(request.getParameter("id_cat")));
		System.out.println(alimento.getNombre());
		cliente.getCarrito().quitarAlimento(alimento);
		//carrito.mostrarCarrito();
		RequestDispatcher dispatcher = request.getRequestDispatcher("/jsp/ComprarComida/carrito.jsp");
		request.setAttribute("sesionCliente", cliente.getCorreoE());
		request.setAttribute("carrito", cliente.getCarrito());
		dispatcher.forward(request, response);
	}
	
	/**
	 * Metodo para manejar la accion abrirCarrito.
	 * @param request proporciona informacion de solicitud para servlets HTTP.
	 * @param response proporciona una funcionalidad específica de HTTP al enviar una respuesta.
	 * @throws SQLException si ocurre un error al conectarse con la base de datos u otro error.
	 * @throws ServletException si ocurre un error en el servlet.
	 * @throws IOException si ocurre un error de entrada/salida.
	 */
	private void abrirCarrito(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException{
		RequestDispatcher dispatcher= request.getRequestDispatcher("/jsp/ComprarComida/carrito.jsp");
		request.setAttribute("sesionCliente", cliente.getCorreoE());
		request.setAttribute("carrito", cliente.getCarrito());
		dispatcher.forward(request, response);
	}
	
	/**
	 * Metodo para manejar la accion confirmarPedido.
	 * @param request proporciona informacion de solicitud para servlets HTTP.
	 * @param response proporciona una funcionalidad específica de HTTP al enviar una respuesta.
	 * @throws SQLException si ocurre un error al conectarse con la base de datos u otro error.
	 * @throws ServletException si ocurre un error en el servlet.
	 * @throws IOException si ocurre un error de entrada/salida.
	 */
	private void confirmarPedido(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException{
		if(cliente.getCarrito().estaVacio()) {
			String error = "Por favor agrega algo a tu carrito antes de confirmar tu pedido.";
			RequestDispatcher dispatcher = request.getRequestDispatcher("/jsp/ComprarComida/error.jsp");
			request.setAttribute("error", error);
			dispatcher.forward(request, response);
		}else {
		
			pedido = cliente.getCarrito();
			List<Direccion> listaDirecciones= direccionDAO.obtenerDirecciones(cliente.getCorreoE());
			
			RequestDispatcher dispatcher= request.getRequestDispatcher("/jsp/ComprarComida/elegirDireccion.jsp");
			request.setAttribute("lista", listaDirecciones);
			request.setAttribute("desdePedido", true);
			request.setAttribute("correo", cliente.getCorreoE());
			dispatcher.forward(request, response);

		}
	}
	
	/**
	 * Metodo para manejar la accion crearPedido.
	 * @param request proporciona informacion de solicitud para servlets HTTP.
	 * @param response proporciona una funcionalidad específica de HTTP al enviar una respuesta.
	 * @throws SQLException si ocurre un error al conectarse con la base de datos u otro error.
	 * @throws ServletException si ocurre un error en el servlet.
	 * @throws IOException si ocurre un error de entrada/salida.
	 */
	private void crearPedido(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException{
		String direccion = request.getParameter("direccion");
		String mensaje = "Si lees esto, alguien no hizo bien su trabajo...";
		if(ordenDAO.crearOrden(pedido.getAlimentos(), direccion, cliente)) {
			cliente.getCarrito().resetCarrito();
			pedido = null;
			mensaje = "Pedido realizado con exito!!!";
		}else {
			mensaje = "Ocurrio un error con la base de datos :(";
		}

		RequestDispatcher dispatcher= request.getRequestDispatcher("/jsp/ComprarComida/error.jsp"); 
		request.setAttribute("error", mensaje);
		dispatcher.forward(request, response);
		
	}

}
