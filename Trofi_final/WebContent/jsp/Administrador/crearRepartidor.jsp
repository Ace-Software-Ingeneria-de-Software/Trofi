<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<html>
<head>
	<title>Crear cuenta</title>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<link rel="stylesheet" type="text/css" href="css/util.css">
	<link rel="stylesheet" type="text/css" href="css/main.css">
</head>
<body>
	
	<div class="limiter">
		<div class="container-login100">
			<div class="wrap-login100">
				<form class="login100-form" action="alta_repartidor?action=dardeAlta" method="post">
					<span class="login100-form-title p-b-26">
						Registrar repartidor
					</span>
					<div class="wrap-input100">
						<label>Nombre</label>
						<input class="input100" type="text" name="nombre" title="Ingresa su nombre" required="">
						<span class="focus-input100"></span>
					</div>
					<div class="wrap-input100">
						<label>Apellido paterno</label>
						<input class="input100" type="text" name="a_paterno" title="Ingresa su apellido paterno" required="">
						<span class="focus-input100"></span>
					</div>
					<div class="wrap-input100">
						<label>Apellido materno</label>
						<input class="input100" type="text" name="a_materno" title="Ingresa su apellido materno" required="">
						<span class="focus-input100"></span>
					</div>
					<div class="wrap-input100">
						<label>Correo electrónico</label>
						<input class="input100" type="email" name="correo_e" placeholder="ejemplo@correo.com" title="Ingresa tu correo electrónico"required="">
						<span class="focus-input100"></span>
					</div>

					<div class="container-login100-form-btn">
						<div class="wrap-login100-form-btn">
							<div class="login100-form-bgbtn"></div>
							<button type="submit" class="login100-form-btn">
								Crear
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