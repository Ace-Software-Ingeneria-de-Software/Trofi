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
        <a class="nav-link" href="menuCliente?action=mostrarCategorias">Menú</a>
      </li>
      <li class="nav-item">
        <a class="nav-link" href="menuCliente?action=carrito">Carrito</a>
      </li>
      <li class="nav-item">
        <a class="nav-link" href="cuentaCliente?action=mostrarPerfil&correo=<c:out value="${sesionCliente}" />">Perfil</a>
      </li>
      <li class="nav-item">
        <a class="nav-link" href="menuCliente?action=cerrarSesion">Cerrar sesión</a>
      </li>
    </ul>
  </div>
</header>
<div class = "container-fluid">
	<div class = "container">
		<h1 class = "text-left" id = "title"> Carrito de compras </h1>
		<hr>
		<div class="row">
			<div class="table-responsive">
				<table class="table table-striped">
					<thead>
				    	<tr>
				    		<th scope="col">Foto</th>
					    	<th scope="col">Nombre</th>
      					  	<th scope="col">Descripción</th>
      					  	<th scope="col">Costo</th>
      					  	<th scope="col">Acción</th>
    					</tr>
  					</thead>
  					<c:forEach var = "alimento" items = "${carrito.alimentos}">
  						<tr>
  							<td><img src="<c:out value="${alimento.foto}"/>" alt="..." class="img-thumbnail" style="width: 200px; height: 100px;"></td>
  							<td><c:out value="${alimento.nombre}"/></td>
							<td><c:out value="${alimento.descripcion}"/></td>
							<td><c:out value="${alimento.costo}"/></td>
							<td><a href="menuCliente?action=quitar&id=<c:out value="${alimento.idAlimento}" />&id_cat=<c:out value="${alimento.idCategoria}" />" class="btn btn-danger" role="button" aria-pressed="true">Quitar</a></td>
  						</tr>
  					</c:forEach>
  					
					  <tr>
						<td></td>
						<td class="font-weight-bold" >Costo Total</td>
						<td><c:out value="${carrito.total }"/></td>
						<td></td>
						<td></td>
					  </tr>
					
				</table>
			</div>
		</div>
		
		<a href="menuCliente?action=confirmarPedido" class="btn btn-info" role="button" aria-pressed="true">Confirmar pedido</a>
	</div>
</div>	
</body>
</html>