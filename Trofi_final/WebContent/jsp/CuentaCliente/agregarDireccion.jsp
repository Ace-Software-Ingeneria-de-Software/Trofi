<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<html lang="es">
<head>
	<title>Agregar dirección</title>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<link rel="stylesheet" type="text/css" href="css/util.css">
	<link rel="stylesheet" type="text/css" href="css/main.css">
</head>
<body>
<div class="limiter">
		<div class="container-login100">
			<div class="wrap-login100">
				<form class="login100-form" action="cuentaCliente?action=agregarDireccion" method="post">
					<span class="login100-form-title p-b-26">
						Agregar dirección
					</span>
					<span class="login100-form-title p-b-48"></span>

					<div class="wrap-input100">
						<label>Dirección</label>
						<input class="input100" type="text" name="direccion" title="Ingresa una dirección" required="" >
						<span class="focus-input100"></span>
					</div>

					<div class="container-login100-form-btn">
						<div class="wrap-login100-form-btn">
							<div class="login100-form-bgbtn"></div>
							<button type="submit" class="login100-form-btn">
								Agregar
							</button>
						</div>
					</div>
					<div class="text-center p-t-115">
						<a class="txt2" href="cuentaCliente?action=direcciones">
							Volver a direcciones
						</a>
					</div>
				</form>
			</div>
		</div>
	</div>
</body>
</html>