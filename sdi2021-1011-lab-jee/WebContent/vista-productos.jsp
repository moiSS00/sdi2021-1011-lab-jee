<%@ page language="java" contentType="text/html;	charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Productos</title>
</head>
<body>

	<h2>Lista de productos</h2>
	<ul>
		<c:forEach var="producto" items="${productosTienda}">
			<tr>
				<li>${producto.nombre}- ${producto.precio}</li>
			</tr>
		</c:forEach>
	</ul>

</body>
</html>