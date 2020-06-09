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
					<form class="login100-form" action="muestra_orden?action=verOrdenesAceptadas" method="post">
						<span class="login100-form-title p-b-26">
							Aviso
						</span>
						<div class="text-center p-t-10">
							<span  class="p">
								<c:out value="${ mensaje }"/>
							</span>
						</div>
						<span class="login100-form-title p-b-20"></span>
						<div class="container-login100-form-btn">
							<div class="wrap-login100-form-btn">
								<div class="login100-form-bgbtn"></div>
								<button type = "submit" class="login100-form-btn" >
									Aceptar
								</button>
							</div>
						</div>
					</form>
				</div>
			</div>
		</div>
</body>
</html>
