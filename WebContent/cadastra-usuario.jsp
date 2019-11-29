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

<title>JDT JSP - Cadastro</title>

<!-- Custom fonts for this template-->
<link href="resources/static/vendor/fontawesome-free/css/all.min.css"
	rel="stylesheet" type="text/css">

<!-- Page level plugin CSS-->
<link
	href="resources/static/vendor/datatables/dataTables.bootstrap4.css"
	rel="stylesheet">

<!-- Custom styles for this template-->
<link href="resources/static/css/sb-admin.css" rel="stylesheet">

</head>

<body id="page-top">

	<jsp:include page="cabecalho.jsp"></jsp:include>

	<div id="wrapper">

		<jsp:include page="sidebar.jsp"></jsp:include>

		<div id="content-wrapper">

			<div class="container-fluid">

				<!-- Breadcrumbs-->
				<ol class="breadcrumb">
					<li class="breadcrumb-item"><a href="#">Cadasro</a></li>
					<li class="breadcrumb-item active">Usuário</li>
				</ol>

				<!-- DataTables Example -->
				<div class="card card-register mx-auto mt-5">
					<div class="card-header">Cadastro de usuário</div>
					<div class="card-body">
						<form action="usuarios?acao=salvar" method="post">
							<div class="form-group">
								<div class="form-label-group">
									<input type="text" name="nome" id="inputNome" value="${ nomeForm }"
										class="form-control" placeholder="Nome" required="required">
									<label for="inputNome">Nome</label>
								</div>
							</div>
							<div class="form-group">
								<div class="form-label-group">
									<input type="text" name="telefone" id="inputTelefone" value="${ telefoneForm }"
										class="form-control" placeholder="Nome">
									<label for="inputTelefone">Telefone</label>
								</div>
							</div>
							<div class="form-group">
								<div class="form-label-group">
									<input type="text" name="login" id="inputLogin" value="${ usernameForm }"
										class="form-control" placeholder="Usuário" required="required">
									<label for="inputLogin">Usuário</label>
								</div>
							</div>
							<div class="form-group">
								<div class="form-row">
									<div class="col-md-6">
										<div class="form-label-group">
											<input type="password" name="senha" id="inputPassword" value="${ passwordForm }"
												class="form-control" placeholder="Senha" required="required">
											<label for="inputPassword">Senha</label>
										</div>
									</div>
									<div class="col-md-6">
										<div class="form-label-group">
											<input type="password" name="confirmaSenha" value="${ passwordConfirmForm }"
												id="confirmPassword" class="form-control"
												placeholder="Confirmação de Senha" required="required">
											<label for="confirmPassword">Confirmação de Senha</label>
										</div>
									</div>
								</div>
							</div>
							<button class="btn btn-primary btn-block">Salvar</button>
							<br>
							<c:if test="${ errorMessage != null }">
								<div class="alert alert-danger" role="alert">
									<span>${ errorMessage }</span>
								</div>
							</c:if>
						</form>
					</div>
				</div>

			</div>
			<!-- /.container-fluid -->

			<jsp:include page="rodape.jsp"></jsp:include>

		</div>
		<!-- /.content-wrapper -->

	</div>
	<!-- /#wrapper -->

	<!-- Scroll to Top Button-->
	<a class="scroll-to-top rounded" href="#page-top"> <i
		class="fas fa-angle-up"></i>
	</a>



	<!-- Bootstrap core JavaScript-->
	<script src="resources/static/vendor/jquery/jquery.min.js"></script>
	<script
		src="resources/static/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

	<!-- Core plugin JavaScript-->
	<script
		src="resources/static/vendor/jquery-easing/jquery.easing.min.js"></script>

	<!-- Page level plugin JavaScript-->
	<script src="resources/static/vendor/chart.js/Chart.min.js"></script>
	<script src="resources/static/vendor/datatables/jquery.dataTables.js"></script>
	<script
		src="resources/static/vendor/datatables/dataTables.bootstrap4.js"></script>

	<!-- Custom scripts for all pages-->
	<script src="resources/static/js/sb-admin.min.js"></script>

	<!-- Demo scripts for this page-->
	<script src="resources/static/js/demo/datatables-demo.js"></script>
	<script src="resources/static/js/demo/chart-area-demo.js"></script>

</body>

</html>
