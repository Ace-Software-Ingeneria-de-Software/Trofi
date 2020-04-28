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
import modelo.Alimento;
import modelo.Categoria;
import modelo.Cliente;


@WebServlet("/menuCliente")
public class MenuCliente extends HttpServlet {
	private static final long serialVersionUID = 1L;
	AlimentoDAO alimentoDAO;
	CategoriaDAO categoriaDAO;
	ClienteDAO clienteDAO;
	Cliente cliente;

	public void init() {
		String jdbcURL = getServletContext().getInitParameter("jdbcURL");
		String jdbcUsername = getServletContext().getInitParameter("jdbcUsername");
		String jdbcPassword = getServletContext().getInitParameter("jdbcPassword");
		try {

			alimentoDAO = new AlimentoDAO(jdbcURL, jdbcUsername, jdbcPassword);
			categoriaDAO = new CategoriaDAO(jdbcURL, jdbcUsername, jdbcPassword);
			clienteDAO = new ClienteDAO(jdbcURL, jdbcUsername, jdbcPassword);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	public MenuCliente() {
		super();
		// TODO Auto-generated constructor stub
	}

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
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("Hola Servlet..");
		doGet(request, response);
	}
	
	private void index (HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException{
		//mostrar(request, response);
		RequestDispatcher dispatcher= request.getRequestDispatcher("/jsp/ComprarComida/menuCliente.jsp");
		dispatcher.forward(request, response);
	}
	
	private void setSesion (HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException{
		String sesionCliente = request.getParameter("sesionCliente");
		cliente = clienteDAO.obtenerPorCorreo(sesionCliente);
		RequestDispatcher dispatcher= request.getRequestDispatcher("/jsp/ComprarComida/menuCliente.jsp");
		dispatcher.forward(request, response);
	}
	
	private void mostrarCategorias(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException , ServletException{
		RequestDispatcher dispatcher = request.getRequestDispatcher("/jsp/ComprarComida/categorias.jsp");
		System.out.println("dispatcher ready..");
		List<Categoria> listaCategorias= categoriaDAO.listarCategorias();
		request.setAttribute("lista", listaCategorias);
		System.out.println("regreso..");
		dispatcher.forward(request, response);
	}
	
	private void mostrarAlimentos(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException , ServletException{
		int id_cat = Integer.parseInt(request.getParameter("id"));
		RequestDispatcher dispatcher = request.getRequestDispatcher("/jsp/ComprarComida/alimentos.jsp");
		List<Alimento> listaAlimentos= alimentoDAO.listarAlimentos(id_cat);
		request.setAttribute("lista", listaAlimentos);
		request.setAttribute("id_cat", id_cat);
		dispatcher.forward(request, response);
	}
	
	private void agregar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
		String mensaje = "Alimento agregado al carrito de compras con exito!!!";
		int id_cat = Integer.parseInt(request.getParameter("id_cat"));
		Alimento alimento = alimentoDAO.obtenerPorId(Integer.parseInt(request.getParameter("id")));
		System.out.println(alimento.getNombre());
		cliente.getCarrito().agregarAlimento(alimento);
		//carrito.mostrarCarrito();
		RequestDispatcher dispatcher = request.getRequestDispatcher("/jsp/ComprarComida/mensaje.jsp");
		request.setAttribute("id_cat", id_cat);
		request.setAttribute("mensaje", mensaje);
		dispatcher.forward(request, response);
	}
	
	private void quitar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
		Alimento alimento = alimentoDAO.obtenerPorId(Integer.parseInt(request.getParameter("id")));
		System.out.println(alimento.getNombre());
		cliente.getCarrito().quitarAlimento(alimento);
		//carrito.mostrarCarrito();
		RequestDispatcher dispatcher = request.getRequestDispatcher("/jsp/ComprarComida/carrito.jsp");
		request.setAttribute("carrito", cliente.getCarrito());
		dispatcher.forward(request, response);
	}
	
	private void abrirCarrito(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException{
		RequestDispatcher dispatcher= request.getRequestDispatcher("/jsp/ComprarComida/carrito.jsp");
		request.setAttribute("carrito", cliente.getCarrito());
		dispatcher.forward(request, response);
	}
	
	private void confirmarPedido(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException{
		if(cliente.getCarrito().estaVacio()) {
			String error = "Por favor agrega algo a tu carrito antes de confirmar tu pedido.";
			RequestDispatcher dispatcher = request.getRequestDispatcher("/jsp/ComprarComida/error.jsp");
			request.setAttribute("error", error);
			dispatcher.forward(request, response);
			
		}else {
		//Carrito pedido = cliente.getCarrito();
		cliente.getCarrito().resetCarrito();
		RequestDispatcher dispatcher= request.getRequestDispatcher("/jsp/ComprarComida/menuCliente.jsp");
		dispatcher.forward(request, response);

		}
	}
}
