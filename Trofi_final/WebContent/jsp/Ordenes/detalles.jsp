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
  <a class="navbar-brand" href="muestra_orden?action=verOrdenes">
  	<img src="imagenes/logo5.png" width="30" height="30" class="d-inline-block align-top" alt="">
    Trofi
  </a>
  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
    <span class="navbar-toggler-icon"></span>
  </button>
  <div class="collapse navbar-collapse" id="navbarNav">
    <ul class="navbar-nav ml-md-auto">      
      <li class="nav-item dropdown">
        <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
         Ordenes 
        </a>
        <div class="dropdown-menu" aria-labelledby="navbarDropdown">
          <a class="dropdown-item" href="muestra_orden?action=verOrdenes"> Ver todas las ordenes </a>
          <a class="dropdown-item" href="muestra_orden?action=verOrdenesAceptadas"> Ordenes aceptadas </a>
        </div>
      </li>
      <li class="nav-item">
        <a class="nav-link" href="muestra_orden?action=cerrarSesion">Cerrar sesión</a>
      </li>
    </ul>
  </div>
</header>
<div class = "container-fluid">
	<div class = "container">
		<h1 class = "text-left" id = "title">Detalles</h1>
		
		<h3 class = "text-left" id = "title">Cliente: <c:out value="${cliente}"/></h3>
		
		<h3 class = "text-left" id = "title">Orden ID: <c:out value="${id_orden}"/></h3>
		
		<hr>
		<div class="row">
			<div class="table-responsive">
				<table class="table table-striped">
					<thead>
				    	<tr>
				    		<th scope="col">Foto</th>
      					  	<th scope="col">Alimento</th>
     					  	<th scope="col">Descripción</th>
     					  	<th scope="col">Costo</th>
    					</tr>
  					</thead>
  					<c:forEach var = "alimento" items = "${lista}">
  						<tr>
  							<td><img src="<c:out value="${alimento.foto}"/>" alt="..." class="img-thumbnail" style="width: 200px; height: 100px;"></td>
							<td class="font-weight-bold"><c:out value="${alimento.nombre}"/></td>
							<td><c:out value="${alimento.descripcion}"/></td>
							<td><c:out value="${alimento.costo}"/></td>
  						</tr>
  					</c:forEach>
				</table>
			</div>
		</div>
		<a href="muestra_orden?action=verOrdenes" class="btn btn-info" role="button" aria-pressed="true">Volver</a>
	</div>
</div>	
</body>
</html>
