<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Editar Categoria</title>
</head>
<body>
    <h1>Editar Categoria</h1>
    <form action="menu?action=editarCategoria" method="post">
        <table>
            <tr>
                <td><label>Id categoria</label></td>
                <td><input type="text" name="id_categoria" title="Id categoria" required=""></td>
            </tr>
            <tr>
                <td><label>Nombre</label></td>
                <td><input type="text" name="nombre" title="nombre" required=""></td>
            </tr>
        </table>
        <input type="submit" name="editar" value="Editar">
    </form>
</body>
</html>