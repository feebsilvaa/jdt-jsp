<jsp:useBean id="calcula"
	class="br.com.feedev.jdtjsp.beans.BeanCursoJsp"
	type="br.com.feedev.jdtjsp.beans.BeanCursoJsp" 
	scope="page"/>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Inicio</title>
</head>
<body>

	<span><%= calcula.calcula(50) %></span>

</body>
</html>







