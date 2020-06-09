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

import java.math.BigInteger; 
import java.security.MessageDigest; 
import java.security.NoSuchAlgorithmException;

import dao.AdministradorDAO;
import modelo.Administrador;
import dao.ClienteDAO;
import modelo.Cliente;
import dao.RepartidorDAO;
import modelo.Repartidor;
import modelo.IniciarSesionIH;

/**
 * Una clase que nos ayuda a modelar un inicio de sesion en la aplicacion
 * @author Quintero Villeda Erik
 */

@WebServlet("/IniciarSesion")
public class IniciarSesion extends HttpServlet{
	private static final long serialVersionUID = 1L;
	AdministradorDAO administradorDAO; //Objeto de la clase AdministradorDAO
	ClienteDAO clienteDAO; //Objeto de la clase ClienteDAO
	RepartidorDAO repartidorDAO; //Objeto de la clase RepartidorDAO
	String sesionCliente; 
	String sesionRepartidor;
	String sesionAdministrador;
	
	/**
	 * Inicializa los parametros de la clae
	 */
	public void init() {
		String jdbcURL = getServletContext().getInitParameter("jdbcURL");
		String jdbcUsername = getServletContext().getInitParameter("jdbcUsername");
		String jdbcPassword = getServletContext().getInitParameter("jdbcPassword");
		try {
			administradorDAO = new AdministradorDAO(jdbcURL, jdbcUsername, jdbcPassword);
			clienteDAO = new ClienteDAO(jdbcURL, jdbcUsername, jdbcPassword);
			repartidorDAO = new RepartidorDAO(jdbcURL, jdbcUsername, jdbcPassword);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	/**
	 * Crea un objeto iniciar sesion
	 */
	public IniciarSesion() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * Ejecuta el metodo dependiendo de la cadena recibida en la vista
	 * @param request
	 * @param response
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {
		System.out.println("Hola Servlet..");
		String action = request.getParameter("action");
		System.out.println(action);
		try {
			switch (action){
			case "index":
				index(request, response);
				break;
			case "IniciarSesionAdministrador":
				iniciarSesionAdministrador(request, response);
				break;
			case "verificarAdministrador":
				verificarAdministrador(request, response);
				break;
			case "IniciarSesionCliente":
				iniciarSesionCliente(request, response);
				break;
			case "verificarCliente":
				verificarCliente(request, response);
				break;	
			case "IniciarSesionRepartidor":
				iniciarSesionRepartidor(request, response);
				break;
			case "verificarRepartidor":
				verificarRepartidor(request, response);
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
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Hola Servlet..");
		doGet(request, response);
	}
	
	/**
	 * Muestra el index de la aplicacion
	 * @param request
	 * @param response
	 */
	private void index (HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException{
		RequestDispatcher dispatcher= request.getRequestDispatcher("index.jsp");
		dispatcher.forward(request, response);
	}
	
	/**
	 * Muestra la pagina iniciar sesion administrador de la aplicacion
	 * @param request
	 * @param response
	 */
	private void iniciarSesionAdministrador(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException , ServletException{
		RequestDispatcher dispatcher= request.getRequestDispatcher("/jsp/IniciarSesion/IniciarSesion.jsp");
		dispatcher.forward(request, response);
	}
	
	/**
	 * Muestra la pagina iniciar sesion cliente de la aplicacion
	 * @param request
	 * @param response
	 */
	private void iniciarSesionCliente(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException , ServletException{
		RequestDispatcher dispatcher= request.getRequestDispatcher("/jsp/IniciarSesion/IniciarSesionCliente.jsp");
		dispatcher.forward(request, response);
	}
	
	/**
	 * Muestra la pagina iniciar sesion repartidor de la aplicacion
	 * @param request
	 * @param response
	 */
	private void iniciarSesionRepartidor(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException , ServletException{
		RequestDispatcher dispatcher= request.getRequestDispatcher("/jsp/IniciarSesion/IniciarSesionRepartidor.jsp");
		dispatcher.forward(request, response);
	}
	
	/**
	 * Verifica la informacion pasada en la vista para saber si iniciamos la sesion del administrador o no
	 * @param request
	 * @param response
	 */
	private void verificarAdministrador(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException{
		if(verificarAdministradorAux(request)) {
			RequestDispatcher dispatcher = request.getRequestDispatcher("/jsp/IniciarSesion/SesionIniciadaAdministrador.jsp");
			request.setAttribute("sesionAdministrador", sesionAdministrador);
			dispatcher.forward(request, response);
		}else {
			mostrarErrorAdministrador(request,response, errorAdministrador(request));
		}
	}
	
	/**
	 * Metodo auxiliar que ayuda a verificar la informacion pasada en la vista para saber si iniciamos la sesion del administrador o no
	 * @param request
	 * @param response
	 * @return true si se puede iniciar sesion
	 */
	private boolean verificarAdministradorAux(HttpServletRequest request) throws SQLException, ServletException, IOException{
		IniciarSesionIH iniciar = new IniciarSesionIH(request.getParameter("correo"), request.getParameter("contrasenia"));
		List<Administrador> lista = administradorDAO.listarAdministradores();
		for (Administrador admin : lista){ 
			if((validarCorreo(admin, iniciar)) && (validarContrasenia(admin, iniciar))) {
				sesionAdministrador = admin.getCorreo();
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Verifica que el correo proporcionado en la vista esta en la base de datos
	 * @param admin administrador que esta en la base 
	 * @param login objeto IniciarSesionIH que junta la informacion recopilada en la vista
	 * @return si true si el correo es valido
	 */
	public boolean validarCorreo(Administrador admin, IniciarSesionIH login){
		if (admin.getCorreo().equals(login.getEmail())){
			return true;
		}else {
			return false;
		}  
	}
	
	/**
	 * Metodo que encripta una contrasenia con SHA-1.
	 * @param input cadena a encriptar
	 * @return la cadena encriptada
	 */
	private String encriptarContrasenia(String input){ 
        try { 
            MessageDigest md = MessageDigest.getInstance("SHA-1");
            byte[] messageDigest = md.digest(input.getBytes());
            BigInteger no = new BigInteger(1, messageDigest); 
            String hashtext = no.toString(16); 
            
            while (hashtext.length() < 32) { 
                hashtext = "0" + hashtext; 
            } 
            
            return hashtext; 
        } 
  
        catch (NoSuchAlgorithmException e) { 
            throw new RuntimeException(e); 
        } 
    } 
	
	/**
	 * Verifica si la contrasenia ecrita es correcta
	 * @param admin administrador a comparar en la base de datos 
	 * @param login objeto de la clase IniciarSesionIH que recolecta la informacion proporcionada en la vista
	 * @return true si la contrase�a es valida
	 */
	public boolean validarContrasenia(Administrador admin, IniciarSesionIH login){
		if (admin.getContrasenia().equals(encriptarContrasenia(login.getPassword()))){
			return true;
		}else {
			return false;
		}  
	}
	
	/**
	 * Verifica la informacion pasada en la vista para saber si iniciamos la sesion del cliente o no
	 * @param request
	 * @param response
	 */
	private void verificarCliente(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException{
		if(verificarClienteAux(request)) {
			RequestDispatcher dispatcher = request.getRequestDispatcher("/jsp/IniciarSesion/SesionIniciadaCliente.jsp");
			request.setAttribute("sesionCliente", sesionCliente);
			dispatcher.forward(request, response);
		}else {
			mostrarErrorCliente(request,response, errorCliente(request));
		}
	}

	/**
	 * Metodo auxiliar que ayuda a verificar la informacion pasada en la vista para saber si iniciamos la sesion del cliente o no
	 * @param request
	 * @param response
	 * @return true si se puede iniciar sesion
	 */
	private boolean verificarClienteAux(HttpServletRequest request) throws SQLException, ServletException, IOException{
		IniciarSesionIH iniciar = new IniciarSesionIH(request.getParameter("correo"), request.getParameter("contrasenia"));
		List<Cliente> lista = clienteDAO.listarClientes();
		for (Cliente cliente : lista){ 
			if((validarCorreo(cliente, iniciar)) && (validarContrasenia(cliente, iniciar))) {
				sesionCliente = cliente.getCorreoE();
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Verifica si el correo proporcionado esta en la base de datos
	 * @param admin administrador a comparar en la base de datos 
	 * @param login objeto de la clase IniciarSesionIH que recolecta la informacion proporcionada en la vista
	 * @return true si el correo es valido
	 */
	public boolean validarCorreo(Cliente cliente, IniciarSesionIH login){
		if (cliente.getCorreoE().equals(login.getEmail())){
			return true;
		}else {
			return false;
		}  
	}
	
	/**
	 * Verifica si la contrasenia ecrita es correcta
	 * @param admin administrador a comparar en la base de datos 
	 * @param login objeto de la clase IniciarSesionIH que recolecta la informacion proporcionada en la vista
	 * @return true si la contrasenia es correcta
	 */
	public boolean validarContrasenia(Cliente cliente, IniciarSesionIH login){
		if (cliente.getContrasenia().equals(encriptarContrasenia(login.getPassword()))){
			return true;
		}else {
			return false;
		}  
	}

	/**
	 * Verifica la informacion pasada en la vista para saber si iniciamos la sesion del repartidor o no
	 * @param request
	 * @param response
	 */
	private void verificarRepartidor(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException{
		if(verificarRepartidorAux(request)) {
			RequestDispatcher dispatcher = request.getRequestDispatcher("/jsp/IniciarSesion/SesionIniciadaRepartidor.jsp");
			request.setAttribute("sesionRepartidor", sesionRepartidor);
			dispatcher.forward(request, response);
		}else {
			mostrarErrorRepartidor(request,response, errorRepartidor(request));
		}
	}
	
	/**
	 * Metodo auxiliar que nos ayuda a verificar la informacion pasada en la vista para saber si iniciamos la sesion del repartidor o no
	 * @param request
	 * @param response
	 * @return true si se puede iniciar sesion
	 */
	private boolean verificarRepartidorAux(HttpServletRequest request) throws SQLException, ServletException, IOException{
		IniciarSesionIH iniciar = new IniciarSesionIH(request.getParameter("correo"), request.getParameter("contrasenia"));
		List<Repartidor> lista = repartidorDAO.listarRepartidores();
		for (Repartidor repartidor : lista){ 
			if((validarCorreoRe(repartidor, iniciar)) && (validarContraseniaRe(repartidor, iniciar))) {
				sesionRepartidor = repartidor.getCorreo();
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Verifica si el correo proporcionado esta en la base de datos
	 * @param admin repartidor a comparar en la base de datos 
	 * @param login objeto de la clase IniciarSesionIH que recolecta la informacion proporcionada en la vista
	 * @return true si la contrasenia es correcta
	 */
	public boolean validarCorreoRe(Repartidor repartidor, IniciarSesionIH login){
		if (repartidor.getCorreo().equals(login.getEmail())){
			return true;
		}else {
			return false;
		}  
	}
	
	/**
	 * Verifica si la contrasenia proporcionada es correcta
	 * @param admin repartidor a comparar en la base de datos 
	 * @param login objeto de la clase IniciarSesionIH que recolecta la informacion proporcionada en la vista
	 * @return true si la contrasenia es correcta
	 */
	public boolean validarContraseniaRe(Repartidor repartidor, IniciarSesionIH login){
		if (repartidor.getContrasenia().equals(encriptarContrasenia(login.getPassword()))){
			return true;
		}else {
			return false;
		}  
	}
	
	/**
	 * Metodo que auxiliar que nos ayuda a mostrar el un mensaje de error para el cliente
	 * @param request
	 * @return el tipo de error a mostrar
	 */
	public String errorCliente(HttpServletRequest request) throws SQLException, ServletException, IOException{
		IniciarSesionIH iniciar = new IniciarSesionIH(request.getParameter("correo"), request.getParameter("contrasenia"));
		List<Cliente> lista = clienteDAO.listarClientes();
		for (Cliente cliente : lista){ 
			if(validarCorreo(cliente, iniciar)){
				return "contrasenia";
			}
		}
		if(correoValido(iniciar.getEmail())){
			return "cvalido";
		}else {
			return "correo";
		}
	}
	
	/**
	 * Metodo que auxiliar que nos ayuda a mostrar el un mensaje de error para el administrador
	 * @param request
	 * @return el tipo de error a mostrar
	 */
	public String errorAdministrador(HttpServletRequest request) throws SQLException, ServletException, IOException{
		IniciarSesionIH iniciar = new IniciarSesionIH(request.getParameter("correo"), request.getParameter("contrasenia"));
		List<Administrador> lista = administradorDAO.listarAdministradores();
		for (Administrador admin : lista){ 
			if(validarCorreo(admin, iniciar)){
				return "contrasenia";
			}
		}
		if(correoValido(iniciar.getEmail())){
			return "cvalido";
		}else {
			return "correo";
		}
	}
	
	/**
	 * Metodo que auxiliar que nos ayuda a mostrar el un mensaje de error para el repartidor
	 * @param request
	 * @return el tipo de error a mostrar
	 */
	public String errorRepartidor(HttpServletRequest request) throws SQLException, ServletException, IOException{
		IniciarSesionIH iniciar = new IniciarSesionIH(request.getParameter("correo"), request.getParameter("contrasenia"));
		List<Repartidor> lista = repartidorDAO.listarRepartidores();
		for (Repartidor repartidor : lista){ 
			if(validarCorreoRe(repartidor, iniciar)){
				return "contrasenia";
			}
		}
		if(correoValido(iniciar.getEmail())){
			return "cvalido";
		}else {
			return "correo";
		}
	}
	
	/**
	 * Metodo que muestra el un mensaje de error para el cliente
	 * @param request
	 */
	public void mostrarErrorCliente(HttpServletRequest request, HttpServletResponse response, String s) throws SQLException, ServletException, IOException{
		if (s.equals("correo")) {
			RequestDispatcher dispatcher = request.getRequestDispatcher("/jsp/IniciarSesion/IniciarSesionClienteEC.jsp");
			dispatcher.forward(request, response);
		}else if(s.equals("contrasenia")) {
			RequestDispatcher dispatcher = request.getRequestDispatcher("/jsp/IniciarSesion/IniciarSesionClienteEP.jsp");
			dispatcher.forward(request, response);
		}else if(s.equals("cvalido")) {
			RequestDispatcher dispatcher = request.getRequestDispatcher("/jsp/IniciarSesion/IniciarSesionClienteEV.jsp");
			dispatcher.forward(request, response);
		}
	}
	
	/**
	 * Metodo que muestra el un mensaje de error para el administrador
	 * @param request
	 */
	public void mostrarErrorAdministrador(HttpServletRequest request, HttpServletResponse response, String s) throws SQLException, ServletException, IOException{
		if (s.equals("correo")) {
			RequestDispatcher dispatcher = request.getRequestDispatcher("/jsp/IniciarSesion/IniciarSesionAdministradorEC.jsp");
			dispatcher.forward(request, response);
		}else if(s.equals("contrasenia")) {
			RequestDispatcher dispatcher = request.getRequestDispatcher("/jsp/IniciarSesion/IniciarSesionAdministradorEP.jsp");
			dispatcher.forward(request, response);
		}else if(s.equals("cvalido")) {
			RequestDispatcher dispatcher = request.getRequestDispatcher("/jsp/IniciarSesion/IniciarSesionAdministradorEV.jsp");
			dispatcher.forward(request, response);
		}
	}
	
	/**
	 * Metodo que muestra el un mensaje de error para el repartidor
	 * @param request
	 */
	public void mostrarErrorRepartidor(HttpServletRequest request, HttpServletResponse response, String s) throws SQLException, ServletException, IOException{
		if (s.equals("correo")) {
			RequestDispatcher dispatcher = request.getRequestDispatcher("/jsp/IniciarSesion/IniciarSesionRepartidorEC.jsp");
			dispatcher.forward(request, response);
		}else if(s.equals("contrasenia")) {
			RequestDispatcher dispatcher = request.getRequestDispatcher("/jsp/IniciarSesion/IniciarSesionRepartidorEP.jsp");
			dispatcher.forward(request, response);
		}else if(s.equals("cvalido")) {
			RequestDispatcher dispatcher = request.getRequestDispatcher("/jsp/IniciarSesion/IniciarSesionRepartidorEV.jsp");
			dispatcher.forward(request, response);
		}
	}
	
	/**
	 * Metodo que nos ayuda a verificar el correo proporcionado
	 * @param s correo a verificar
	 * @return true si el correo es valido
	 */
	public boolean correoValido(String s) {
		int resultado = s.indexOf("@");
		if(resultado == -1) {
			return true;
	    }else {
	    	return false;
	    } 
	}
}