<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Inicio</title>
</head>
<body>
	<h1>Bem vindo ao curso de jsp</h1>
	<%= "Agora vaaai!" %>
	
	<br>
	<br>
	
	<form action="receber-nome.jsp">
		<label>Nome: </label>
		<input type="text" id="nome" name="nome"><br>
		
		<input type="submit" value="Enviar">
	</form>
	
	

	<%! 
		int cont = 2; 
		public int soma(int n1, int n2) {
			return n1 + n2;
		}
	%>
	<%= cont %>
	<%= soma(2, 4) %>
</body>
</html>