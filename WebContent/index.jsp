<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html lang="pt-br">

<head>

<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="">
<meta name="author" content="">

<title>JDT JSP - Login</title>

<!-- Custom fonts for this template-->
<link href="resources/static/vendor/fontawesome-free/css/all.min.css"
	rel="stylesheet" type="text/css">

<!-- Custom styles for this template-->
<link href="resources/static/css/sb-admin.css" rel="stylesheet">

</head>

<body class="bg-dark">

	<div class="container">

		<div class="card card-login mx-auto mt-5">
			<div class="card-header">Login</div>
			<div class="card-body">
				<form action="auth" method="post"
					onsubmit="return validaCampos() ? true : false;">
					<div class="form-group">
						<div class="form-label-group">
							<input type="text" name="login" id="inputLogin"
								value="${ usernameForm }" class="form-control"
								placeholder="Username" autofocus="autofocus"> <label
								for="inputLogin">Username</label>
						</div>
					</div>
					<div class="form-group">
						<div class="form-label-group">
							<input type="password" name="senha" id="inputPassword"
								value="${ passwordForm }" class="form-control"
								placeholder="Password"> <label for="inputPassword">Password</label>
						</div>
					</div>
					<div class="form-group">
						<div class="checkbox">
							<label> <input type="checkbox" value="remember-me">
								Remember Password
							</label>
						</div>
					</div>
					<button class="btn btn-primary btn-block">Login</button>
				</form>
				<div class="text-center">
					<a class="d-block small mt-3" href="#">Register an Account</a> <a
						class="d-block small" href="#">Forgot Password?</a>
				</div>
				<br>
				<div id="errorMessage">
					<c:if test="${ errorMessage != null }">
						<div class="alert alert-danger" role="alert">
							<span>${ errorMessage }</span>
						</div>
					</c:if>
				</div>
			</div>
		</div>
	</div>

	<!-- Bootstrap core JavaScript-->
	<script src="resources/static/vendor/jquery/jquery.min.js"></script>
	<script
		src="resources/static/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

	<!-- Core plugin JavaScript-->
	<script
		src="resources/static/vendor/jquery-easing/jquery.easing.min.js"></script>

	<script type="text/javascript">
		function validaCampos() {

			let inputLoginVal = $('#inputLogin').val();
			let inputSenhaVal = $('#inputPassword').val();

			let errors = [];

			if (inputLoginVal == '') {
				errors.push("O campo usuário é de preenchimento obrigatório.");
			}

			if (inputSenhaVal == '') {
				errors.push("O campo senha é de preenchimento obrigatório.");
			}

			if (errors.length > 0) {
				$('#errorMessage')
						.html(
								'<div class="alert alert-danger" role="alert"><ul></ul></div>');
				for (var i = 0; i < errors.length; i++) {
					$('#errorMessage div ul').append('<li>' + errors[i] + '</li>');
				}
				
				return false;
			}
			return true;
		}
	</script>

</body>

</html>
