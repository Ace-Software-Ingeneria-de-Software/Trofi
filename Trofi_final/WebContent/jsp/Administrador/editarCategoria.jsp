<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
	<title>Editar categoría</title>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<link rel="stylesheet" type="text/css" href="css/util.css">
	<link rel="stylesheet" type="text/css" href="css/main.css">
</head>
<body>
	
	<div class="limiter">
		<div class="container-login100">
			<div class="wrap-login100">
				<form class="login100-form" action="menu?action=editarCategoria&id_categoria=<c:out value="${idCat}"/>" method="post">
					<span class="login100-form-title p-b-26">
						Editar categoría
					</span>
					<div class="wrap-input100">
						<label>Nombre</label>
						<input class="input100" type="text" name="nombre" title="Ingresa un nombre" required="" value="<c:out value="${categoria.nombre}"></c:out>">
						<span class="focus-input100"></span>
					</div>
					
					<div class="container-login100-form-btn">
						<div class="wrap-login100-form-btn">
							<div class="login100-form-bgbtn"></div>
							<button type="submit" class="login100-form-btn">
								Editar
							</button>
						</div>
					</div>
					<div class="text-center p-t-115">
						<a class="txt2" href="menu?action=mostrarCategorias">
							Cancelar
						</a>
					</div>
				</form>
			</div>
		</div>
	</div>
</body>
</html>