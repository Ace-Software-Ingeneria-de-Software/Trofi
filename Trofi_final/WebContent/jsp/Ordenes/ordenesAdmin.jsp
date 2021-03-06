<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
  <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Administarador - Trofi</title>
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
        <a class="nav-link" href="menu?action=index">Men�</a>
      </li>
      <li class="nav-item">
        <a class="nav-link" href="alta_repartidor?action=nuevoRepartidor">Registrar repartidor</a>
      </li>
      <li class="nav-item">
        <a class="nav-link" href="menu?action=abrirOrdenes">Ver ordenes</a>
      </li>
      <li class="nav-item">
        <a class="nav-link" href="menu?action=cerrarSesion">Cerrar sesi�n</a>
      </li>
    </ul>
  </div>
</header>
<div class = "container-fluid">
	<div class = "container">
		<h1 class = "text-left" id = "title"> Ordenes </h1>
		<hr>
		<div class="row">
			<div class="table-responsive">
				<table class="table table-striped">
					<thead>
				    	<tr>
					    	<th scope="col">Cliente</th>
      					  	<th scope="col">Id</th>
     					  	<th scope="col">Direcci�n de entrega</th>
     					  	<th scope="col">Estado de la orden</th>
      					  	<th colspan=2>Acciones</th>
    					</tr>
  					</thead>
  					<c:forEach var = "orden" items = "${lista}">
  						<tr>
  							<td><c:out value="${orden.correo_cl}"/></td>
							<td><c:out value="${orden.id_orden}"/></td>
							<td><c:out value="${orden.direccion_cliente}"/></td>
							<td><c:out value="${orden.estado_orden}"/></td>
							<td><a href="menu?action=cambiarEstado&id_orden=<c:out value="${orden.id_orden}" />&correoCliente=<c:out value="${orden.correo_cl}" />"  class="btn btn-success" role="button" aria-pressed="true">Cambiar Estado</a></td>
  						</tr>
  					</c:forEach>
				</table>
			</div>
		</div>
	</div>
</div>		
</body>
</html>