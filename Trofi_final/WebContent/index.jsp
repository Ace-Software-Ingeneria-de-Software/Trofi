<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Inicio</title>
<link rel="stylesheet" href="css/bootstrap.css">
<script src="https://code.jquery.com/jquery-3.4.1.slim.min.js" integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js" integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>
</head>
<body>
<header class="navbar sticky-top navbar-expand-lg navbar-light bg-light">
  <a class="navbar-brand" href="IniciarSesion?action=index">
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
         Iniciar sesión
        </a>
        <div class="dropdown-menu" aria-labelledby="navbarDropdown">
          <a class="dropdown-item" href="IniciarSesion?action=IniciarSesionCliente">Cliente</a>
          <a class="dropdown-item" href="IniciarSesion?action=IniciarSesionAdministrador">Administrador</a>
          <a class="dropdown-item" href="IniciarSesion?action=IniciarSesionRepartidor">Repartidor</a>
        </div>
      </li>
      <li class="nav-item">
        <a class="nav-link" href="cuentaCliente?action=nuevaCuenta">Crear cuenta</a>
      </li>
    </ul>
  </div>
</header>
<div id="carouselExampleControls" class="carousel slide" data-ride="carousel">
  	<div class="carousel-inner">
    	<div class="carousel-item active">
      		<img src="imagenes/comida-11.jpg" class="d-block w-100" alt="...">
    	</div>
    	<div class="carousel-item">
      		<img src="imagenes/comida-10.jpg" class="d-block w-100" alt="...">
    	</div>
    	<div class="carousel-item">
      		<img src="imagenes/comida-5.jpg" class="d-block w-100" alt="...">
    	</div>
    	<div class="carousel-item">
      		<img src="imagenes/comida-6.jpg" class="d-block w-100" alt="...">
    	</div>
    	<div class="carousel-item">
      		<img src="imagenes/comida-7.jpg" class="d-block w-100" alt="...">
    	</div>
    	<div class="carousel-item">
      		<img src="imagenes/comida-8.jpg" class="d-block w-100" alt="...">
    	</div>
    	<div class="carousel-item">
      		<img src="imagenes/comida-9.jpg" class="d-block w-100" alt="...">
    	</div>
  	</div>
  	<a class="carousel-control-prev" href="#carouselExampleControls" role="button" data-slide="prev">
    	<span class="carousel-control-prev-icon" aria-hidden="true"></span>
    	<span class="sr-only">Previous</span>
  	</a>
  	<a class="carousel-control-next" href="#carouselExampleControls" role="button" data-slide="next">
    	<span class="carousel-control-next-icon" aria-hidden="true"></span>
    	<span class="sr-only">Next</span>
 	</a>
</div>
</body>
</html>