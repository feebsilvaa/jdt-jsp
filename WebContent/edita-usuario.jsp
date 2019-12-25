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

<title>JDT JSP - Editar</title>

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
					<li class="breadcrumb-item"><a href="#">Editar</a></li>
					<li class="breadcrumb-item active"><span>${ usuario.nome }</span></li>
				</ol>

				<div class="card card-register mx-auto mt-5">
					<div class="card-header">
						Editar <span>${ usuario.nome }</span>
					</div>
					<div class="row no-gutters">
						<div class="col-md-4 align-self-center">
							<div class="row">
								<c:if test="${ !empty usuario.tempFoto}">
									<span> <img src="${ usuario.tempFoto }"
										class="text-center card-img img-thumbnail"
										alt="${ usuario.login }" title="${ usuario.login }">
									</span>
								</c:if>
								<c:if test="${ empty usuario.tempFoto}">
									<div class="offset-sm-2 offset-md-4">
										<i class="fas fa-10x fa-user img-thumbnail"></i>
									</div>
								</c:if>
							</div>
						</div>
						<div class="col-md-8">
							<div class="card-body">
								<form action="usuarios?acao=editar" method="post"
									enctype="multipart/form-data">
									<div class="form-group form-row">
										<div class="col-md-12">
											<div class="form-label-group">
												<input type="text" name="login" id="inputLogin"
													value="${ usuario.login }" class="form-control"
													placeholder="Usuário" disabled> <label
													for="inputLogin">Usuário</label>
											</div>
										</div>
									</div>
									<div class="form-group form-row">
										<div class="col-md-6">
											<div class="form-label-group">
												<input type="text" name="nome" id="inputNome"
													value="${ usuario.nome }" class="form-control"
													placeholder="Nome" required="required"> <label
													for="inputNome">Nome</label>
											</div>
										</div>
										<div class="col-md-6">
											<div class="form-label-group">
												<c:if test="${ usuario.sexo != 'FEMININO' }">
													<div class="form-check form-check-inline">
														<input class="form-check-input" type="radio"
															name="radioSexo" id="radioSexo1" value="opt1" checked>
														<label class="form-check-label" for="radioSexo1">Masculino</label>
													</div>
													<div class="form-check form-check-inline">
														<input class="form-check-input" type="radio"
															name="radioSexo" id="radioSexo2" value="opt2"> <label
															class="form-check-label" for="radioSexo2">Feminino</label>
													</div>
												</c:if>
												<c:if test="${ usuario.sexo == 'FEMININO' }">
													<div class="form-check form-check-inline">
														<input class="form-check-input" type="radio"
															name="radioSexo" id="radioSexo1" value="opt1">
														<label class="form-check-label" for="radioSexo1">Masculino</label>
													</div>
													<div class="form-check form-check-inline">
														<input class="form-check-input" type="radio"
															name="radioSexo" id="radioSexo2" value="opt2" checked> <label
															class="form-check-label" for="radioSexo2">Feminino</label>
													</div>
												</c:if>
											</div>
										</div>
									</div>
									<div class="form-group form-row">
										<div class="col-md-6">
											<div class="form-label-group">
												<input type="text" name="telefone" id="inputTelefone"
													value="${ usuario.telefone }" class="form-control"
													placeholder="Nome"> <label for="inputTelefone">Telefone</label>
											</div>
										</div>
									</div>

									<div class="form-row">
										<div class="col-md-4">
											<div class="custom-file">
												<input type="file" name="foto" id="foto"
													class="custom-file-input" accept="image/*" value="">
												<label class="custom-file-label" for="foto">Escolha
													uma foto...</label>
											</div>
										</div>
										<div class="col-md-4">
											<div class="custom-file">
												<input type="file" name="pdf" id="pdf"
													class="custom-file-input" accept="application/pdf"
													value="${ usuario.pdfFile.fileB64 }"> <label
													class="custom-file-label" for="pdf">Escolha um
													PDF...</label>
											</div>
										</div>
										<div class="col-md-2">
											<div class="form-group">
												<div class="form-check">
													<c:if test="${ usuario.ativo }">
														<input class="form-check-input" id="usuarioAtivo"
															name="usuarioAtivo" type="checkbox" value="ativo" checked>
													</c:if>
													<c:if test="${ not usuario.ativo }">
														<input class="form-check-input" id="usuarioAtivo"
															name="usuarioAtivo" type="checkbox" value="ativo">
													</c:if>
													<label class="form-check-label" for="usuarioAtivo">
														Ativo </label>
												</div>
											</div>
										</div>
									</div>
									<div class="form-group">
										<div class="form-label-group">
											<input type="hidden" name="id" value="${ usuario.id }"
												class="form-control">
										</div>
									</div>
									<button class="btn btn-primary btn-block">Editar</button>
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

	<script type="text/javascript">
		$(document).ready(function() {
			bsCustomFileInput.init()
		});
	</script>
</body>

</html>
