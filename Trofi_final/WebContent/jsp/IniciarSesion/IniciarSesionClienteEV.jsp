<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<head>
	<title>Iniciar sesión</title>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<link rel="stylesheet" type="text/css" href="css/util.css">
	<link rel="stylesheet" type="text/css" href="css/main.css">
</head>
<body>
	
	<div class="limiter">
		<div class="container-login100">
			<div class="wrap-login100">
				<form class="login100-form" action="IniciarSesion?action=verificarCliente" method="post">
					<span class="login100-form-title p-b-26">
						Bienvenido
					</span>
					<span class="login100-form-title p-b-48">
						<img src="imagenes/logo5.png" width="30" height="30" alt="">
					</span>

					<div class="text-center p-b-10">
						<span  class="txt3">
							El correo ingresado no es valido.
						</span>
					</div>

					<div class="wrap-input100">
						<label>Correo electrónico</label>
						<input class="input100" type="email" name="correo">
						<span class="focus-input100"></span>
					</div>

					<div class="wrap-input100">
						<label>Contraseña</label>
						<input class="input100" type="password" name="contrasenia">
						<span class="focus-input100"></span>
					</div>

					<div class="container-login100-form-btn">
						<div class="wrap-login100-form-btn">
							<div class="login100-form-bgbtn"></div>
							<button type="submit" class="login100-form-btn">
								Iniciar sesión
							</button>
						</div>
					</div>

					<div class="text-center p-t-115">
						<span class="txt1">
							¿No tienes una cuenta?
						</span>

						<a class="txt2" href="cuentaCliente?action=nuevaCuenta">
							Crear cuenta
						</a>
					</div>
				</form>
			</div>
		</div>
	</div>
</body>
</html>