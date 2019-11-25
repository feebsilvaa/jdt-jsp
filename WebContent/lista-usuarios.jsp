<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Lista de usuarios</title>
</head>
<body>
	<jsp:include page="cabecalho.jsp" />

	<h2>Usuarios</h2>

	<table>

		<thead>
			<tr>
				<th>Login</th>
				<th>Senha</th>
				<th>Ações</th>
			</tr>
		</thead>
		<tbody>
			<c:catch var="err">
				<c:forEach items="${ usuarios }" var="usuario">
					<tr>
						<td><span>${ usuario.login }</span></td>
						<td><span>${ usuario.senha }</span></td>
						<td>
							<a href="usuarios?acao=edicao&user=${ usuario.login }">Editar</a>
							<a href="usuarios?acao=excluir&user=${ usuario.login }">Excluir</a>
						</td>
					</tr>
				</c:forEach>
			</c:catch>
			<c:if test="err">
				<span>${ err.message }</span>
			</c:if>
		</tbody>

	</table>


								
</body>
</html>