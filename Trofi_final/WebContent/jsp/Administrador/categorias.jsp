<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
  <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Menu - Trofi</title>
<link rel="stylesheet" href="css/bootstrap.css">
<script src="https://code.jquery.com/jquery-3.4.1.slim.min.js" integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js" integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>
</head>
<body>
	<header class="navbar sticky-top navbar-expand-lg navbar-light bg-light">
  <a class="navbar-brand" href="menu?action=index">
  	<img src="imagenes/logo5.png" width="30" height="30" class="d-inline-block align-top" alt="">
    Trofi
  </a>
  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
    <span class="navbar-toggler-icon"></span>
  </button>
  <div class="collapse navbar-collapse" id="navbarNav">
    <ul class="navbar-nav ml-md-auto">      
      <li class="nav-item">
        <a class="nav-link" href="menu?action=index">Menú</a>
      </li>
      <li class="nav-item">
        <a class="nav-link" href="alta_repartidor?action=nuevoRepartidor">Registrar repartidor</a>
      </li>
      <li class="nav-item">
        <a class="nav-link" href="menu?action=abrirOrdenes">Ver ordenes</a>
      </li>
      <li class="nav-item">
        <a class="nav-link" href="menu?action=cerrarSesion">Cerrar sesión</a>
      </li>
    </ul>
  </div>
</header>
	
<div class = "container-fluid">
	<div class = "container">
		<h1 class = "text-left" id = "title"> Categorias </h1>
		<hr>
		<div class="row">
			<div class="table-responsive">
				<table class="table table-striped">
					<thead>
				    	<tr>
					    	<th scope="col">Categoria</th>
      					  	<th scope="col">Ver alimentos</th>
      					  	<th scope="col">Editar</th>
      					  	<th scope="col">Eliminar</th>
    					</tr>
  					</thead>
  					<c:forEach var = "categoria" items = "${lista}">
  						<tr>
  							<td><c:out value="${categoria.nombre}"/></td>
							<td><a href="menu?action=mostrarAlimentos&id=<c:out value="${categoria.id}" />" class="btn btn-info" role="button" aria-pressed="true" >Seleccionar</a> </td>
							<td><a href="menu?action=showEditarCategoria&id=<c:out value="${categoria.id}" />" class="btn btn-success" role="button" aria-pressed="true" >Editar</a> </td>
							<td><a href="menu?action=eliminarCategoria&id=<c:out value="${categoria.id}" />" class="btn btn-danger" role="button" aria-pressed="true" >Eliminar</a> </td>
  						</tr>
  					</c:forEach>
				</table>
			</div>
		</div>
		<a href="menu?action=nuevaCategoria" class="btn btn-success" role="button" aria-pressed="true">Agregar categoria</a>
	</div>
</div>
	
</body>
</html>