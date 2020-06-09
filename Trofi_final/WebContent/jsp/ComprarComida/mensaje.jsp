<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
	
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Mensaje - Trofi</title>
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<link rel="stylesheet" type="text/css" href="css/util.css">
	<link rel="stylesheet" type="text/css" href="css/main.css">
</head>
<body>
	<div class="limiter">	
		<div class="container-login100">
				<div class="wrap-login100">
					<form class="login100-form">
						<span class="login100-form-title p-b-26">
							Aviso
						</span>
						<div class="text-center p-t-10">
							<span  class="p">
								Alimento agregado.
							</span>
						</div>
						<span class="login100-form-title p-b-20"></span>
						<div class="container-login100-form-btn">
							<div class="wrap-login100-form-btn">
								<a href="menuCliente?action=sesionIniciada" class="btn">
									Ir al menú
								</a>
								<a href="menuCliente?action=mostrarAlimentos&id=<c:out value="${id_cat}" />" class="btn">
									Volver
								</a>
							</div>
						</div>
					</form>
				</div>
			</div>
		</div>
</body>
</html>