<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
  <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Menu</title>
<link rel="stylesheet" href="css/bootstrap.css">
<script src="https://code.jquery.com/jquery-3.4.1.slim.min.js" integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js" integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>
</head>
<body>
<header class="navbar sticky-top navbar-expand-lg navbar-light bg-light">
  <a class="navbar-brand" href="menuCliente?action=sesionIniciada">
  	<img src="imagenes/logo5.png" width="30" height="30" class="d-inline-block align-top" alt="">
    Trofi
  </a>
  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
    <span class="navbar-toggler-icon"></span>
  </button>
  <div class="collapse navbar-collapse" id="navbarNav">
    <ul class="navbar-nav ml-md-auto">      
      <li class="nav-item">
        <a class="nav-link" href="menuCliente?action=mostrarCategorias">Men�</a>
      </li>
      <li class="nav-item">
        <a class="nav-link" href="menuCliente?action=carrito">Carrito</a>
      </li>
      <li class="nav-item">
        <a class="nav-link" href="cuentaCliente?action=mostrarPerfil&correo=<c:out value="${sesionCliente}" />">Perfil</a>
      </li>
      <li class="nav-item">
        <a class="nav-link" href="menuCliente?action=cerrarSesion">Cerrar sesi�n</a>
      </li>
    </ul>
  </div>
</header>
<div class = "container-fluid" style="padding-bottom: 100px;">
	<div class = "container">
		<h1 class = "text-left" id = "title"> <c:out value="${categoria}"/> </h1>
		<hr>
		<div class="row" style="padding-bottom: 15px;">
			<div class="table-responsive" style="width: 80%; margin: 0 auto;">
				<table class="table-hover">
  					<c:forEach var = "alimento" items = "${lista}">
  						<tr>
  				<td>
					<img src="<c:out value="${alimento.foto}"/>" alt="..." class="img-thumbnail" style="width: 400px; height: 200px;">
			  	</td>
			  	<td>
					<div class="d-flex bd-highlight">
						<div class="p-2 w-75 bd-highlight">
							<div class="w-85 p-3 text-justify font-weight-bold"><c:out value="${alimento.nombre}"/></div>
							<div class="w-85 p-3 text-justify">
								<c:out value="${alimento.descripcion}"/>
							</div>
							<div class="w-85 p-3 text-justify">
								<a href="menuCliente?action=agregar&id=<c:out value="${alimento.idAlimento}" />&id_cat=<c:out value="${id_cat}" />" class="btn btn-success" role="button" aria-pressed="true">Seleccionar</a>
							</div>
						</div>
						<div class="p-2 bd-highlight">
							<div class="d-flex flex-column bd-highlight mb-3">
								<div class="p-4 bd-highlight"></div>
								<div class="p-2 bd-highlight"></div>
								<div class="p-2 bd-highlight"><c:out value="${alimento.costo}"/></div>
								<div class="p-2 bd-highlight"></div>
								<div class="p-2 bd-highlight"></div>
							</div>
						</div>
					  	</div>
				</td>
  						</tr>
  					</c:forEach>
				</table>
			</div>
		</div>
		
		<a href="menuCliente?action=mostrarCategorias" class="btn btn-info" role="button" aria-pressed="true">Volver a categorias</a>
	</div>
</div>	
</body>
</html>
