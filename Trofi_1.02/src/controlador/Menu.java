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
import modelo.Alimento;
import modelo.Categoria;


@WebServlet("/menu")
public class Menu extends HttpServlet {
	private static final long serialVersionUID = 1L;
	AlimentoDAO alimentoDAO;
	CategoriaDAO categoriaDAO;

	public void init() {
		String jdbcURL = getServletContext().getInitParameter("jdbcURL");
		String jdbcUsername = getServletContext().getInitParameter("jdbcUsername");
		String jdbcPassword = getServletContext().getInitParameter("jdbcPassword");
		try {

			alimentoDAO = new AlimentoDAO(jdbcURL, jdbcUsername, jdbcPassword);
			categoriaDAO = new CategoriaDAO(jdbcURL, jdbcUsername, jdbcPassword);
			//carrito = new Carrito();
		} catch (Exception e) {
			System.out.println("Hubo un error");
			// TODO: handle exception
		}
	}

	public Menu() {
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
			case "mostrarCategorias":
				mostrarCategorias(request, response);
				break;
			case "mostrarAlimentos":
				mostrarAlimentos(request, response);
				break;
			case "editarCategoria":
				editarCategoria(request, response);
				break;
			case "editarAlimento":
				editarAlimento(request, response);
				break;
			case "eliminarCategoria":
				eliminarCategoria(request, response);
				break;
			case "eliminarAlimento":
				eliminarAlimento(request, response);
				break;
			case "agregarCategoria":
				agregarCategoria(request, response);
				break;
			case "agregarAlimento":
				agregarAlimento(request, response);
				break;
			case "nuevoAlimento":
				nuevoAlimento(request, response);
				break;
			case "nuevaCategoria":
				nuevaCategoria(request, response);
				break;
			case "showEditarAlimento":
				showEditarAlimento(request, response);
				break;
			case "showEditarCategoria":
				showEditarCategoria(request, response);
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
		RequestDispatcher dispatcher= request.getRequestDispatcher("/jsp/Administrador/menu.jsp");
		dispatcher.forward(request, response);
	}
	
	private void mostrarCategorias(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException , ServletException{
		RequestDispatcher dispatcher = request.getRequestDispatcher("/jsp/Administrador/categorias.jsp");
		System.out.println("dispatcher ready..");
		List<Categoria> listaCategorias= categoriaDAO.listarCategorias();
		request.setAttribute("lista", listaCategorias);
		System.out.println("regreso..");
		dispatcher.forward(request, response);
	}
	
	private void mostrarAlimentos(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException , ServletException{
		int cat_id = Integer.parseInt(request.getParameter("id"));
		RequestDispatcher dispatcher = request.getRequestDispatcher("/jsp/Administrador/alimentos.jsp");
		List<Alimento> listaAlimentos= alimentoDAO.listarAlimentos(cat_id);
		request.setAttribute("lista", listaAlimentos);
		request.setAttribute("cat_id", cat_id);
		dispatcher.forward(request, response);
	}
	
	private void showEditarAlimento(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
		Alimento alimento = alimentoDAO.obtenerPorId(Integer.parseInt(request.getParameter("id")));
		request.setAttribute("alimento", alimento);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/jsp/Administrador/editarAlimento.jsp");
		dispatcher.forward(request, response);
	}
	
	private void showEditarCategoria(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
		Categoria categoria = categoriaDAO.obtenerPorId(Integer.parseInt(request.getParameter("id")));
		request.setAttribute("categoria", categoria);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/jsp/Administrador/editarCategoria.jsp");
		dispatcher.forward(request, response);
	}
	
	private void editarAlimento(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException{
		Alimento alimento = new Alimento(request.getParameter("nombre"), request.getParameter("descripcion"), Double.parseDouble(request.getParameter("costo")), Integer.parseInt(request.getParameter("id_alimento")), Integer.parseInt(request.getParameter("id_categoria")));
		alimentoDAO.editarAlimento(alimento);
		index(request, response);
	}
	
	private void editarCategoria(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException{
		Categoria categoria = new Categoria(Integer.parseInt(request.getParameter("id_categoria")), request.getParameter("nombre"));
		categoriaDAO.editarCategoria(categoria);
		index(request, response);
	}
	
	private void eliminarCategoria(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException{
		Categoria categoria = categoriaDAO.obtenerPorId(Integer.parseInt(request.getParameter("id")));
		categoriaDAO.eliminarCategoria(categoria);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/jsp/Administrador/menu.jsp");
		dispatcher.forward(request, response);
	}
	
	private void eliminarAlimento(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException{
		int id = Integer.parseInt(request.getParameter("id"));
		//System.out.println("id_Alimento: " + id);
		Alimento alimento = alimentoDAO.obtenerPorId(id);
		alimentoDAO.eliminarAlimento(alimento);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/jsp/Administrador/menu.jsp");
		dispatcher.forward(request, response);
		
	}
	
	private void nuevaCategoria(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("/jsp/Administrador/agregarCategoria.jsp");
		dispatcher.forward(request, response);
	}
	
	private void agregarCategoria(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
		Categoria categoria = new Categoria(Integer.parseInt(request.getParameter("id_categoria")), request.getParameter("nombre"));
		categoriaDAO.agregarCategoria(categoria);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/jsp/Administrador/menu.jsp");
		dispatcher.forward(request, response);
	}
	
	private void nuevoAlimento(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("/jsp/Administrador/agregarAlimento.jsp");
		dispatcher.forward(request, response);
	}
	
	private void agregarAlimento(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
		Alimento alimento = new Alimento(request.getParameter("nombre"), request.getParameter("descripcion"), Double.parseDouble(request.getParameter("costo")), Integer.parseInt(request.getParameter("id_alimento")), Integer.parseInt(request.getParameter("id_categoria")));
		alimentoDAO.agregarAlimento(alimento);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/jsp/Administrador/menu.jsp");
		dispatcher.forward(request, response);
	}
	
}