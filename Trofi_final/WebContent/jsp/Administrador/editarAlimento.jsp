<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
	<title>Editar alimento</title>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<link rel="stylesheet" type="text/css" href="css/util.css">
	<link rel="stylesheet" type="text/css" href="css/main.css">
	<script src="https://code.jquery.com/jquery-3.4.1.slim.min.js" integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n" crossorigin="anonymous"></script>
	<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
	<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js" integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>
</head>
<body>
	
	<div class="limiter">
		<div class="container-login100">
			<div class="wrap-login100">
				<form class="login100-form" action="menu?action=editarAlimento&id_categoria=<c:out value="${idCat}" />&id_alimento=<c:out value="${idAlim}"/>" method="post">
					<span class="login100-form-title p-b-26">
						Editar: <c:out value="${alimento.nombre}"/>
					</span>
					<div class="wrap-input100">
						<label>Nombre</label>
						<input class="input100" type="text" name="nombre" title="Ingresa un nombre" required="" value="<c:out value="${nombre}"></c:out>">
						<span class="focus-input100"></span>
					</div>
					<div class="wrap-input100">
						<label>Descripción</label>
						<input class="input100" type="text" name="descripcion" title="descripcion" title="Ingresa una descripción" maxlength="255" required="" value='<c:out value="${descripcion}"></c:out>'>
						<span class="focus-input100"></span>
					</div>
					<div class="wrap-input100">
						<label>Costo</label>
						<input class="input100" type="text" name="costo" title="Ingresa un costo" required="" value='<c:out value="${costo}"></c:out>'>
						<span class="focus-input100"></span>
					</div>
					
					<span class="p-b-48">Foto</span>
					<span class="login100-form-title p-b-20"></span>
					<span class="login100-form-title p-b-20">
						<input class="input100" type="file" name="foto" title="foto" title="Ingresa una foto" required="">
					</span>
					
					<div class="container-login100-form-btn">
						<div class="wrap-login100-form-btn">
							<div class="login100-form-bgbtn"></div>
							<button type="submit" class="login100-form-btn">
								Editar
							</button>
						</div>
					</div>
					<div class="text-center p-t-115">
						<a class="txt2" href="menu?action=mostrarAlimentos&id=<c:out value="${idCat}" />"">
							Cancelar
						</a>
					</div>
				</form>
			</div>
		</div>
	</div>
</body>
</html>
