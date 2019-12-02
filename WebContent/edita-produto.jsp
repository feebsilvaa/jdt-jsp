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
					<li class="breadcrumb-item active"><span>${ produto.nome }</span></li>
				</ol>

				<!-- DataTables Example -->
				<div class="card card-register mx-auto mt-5">
					<div class="card-header">
						Editar <span>${ produto.nome }</span>
					</div>
					<div class="card-body">
						<form action="produtos?acao=editar" method="post">
							<div class="form-group">
								<div class="form-label-group">
									<input type="text" name="nome" id="inputNome"
										value="${ produto.nome }" class="form-control"
										placeholder="Nome" required="required"> <label
										for="inputNome">Nome</label>
								</div>
							</div>
							<div class="form-group">
								<div class="form-row">
									<div class="col-md-6">
										<div class="form-group">
											<div class="form-label-group">
												<input type="number" name="quantidade" id="inputQuantidade"
													value="${ produto.quantidade }" class="form-control"
													min="0" placeholder="Quantidade" required="required">
												<label for="inputQuantidade">Quantidade</label>
											</div>
										</div>
									</div>
									<div class="col-md-6">
										<div class="form-group">
											<div class="form-label-group">
												<input type="number" name="preco" id="inputValor"
													value="${ produto.preco }" class="form-control" min="0" max="999999999"
													placeholder="Valor" required="required"> <label
													for="inputValor">Valor</label>
											</div>
										</div>
									</div>
								</div>
							</div>
							<div class="form-group">
								<div class="form-label-group">
									<input type="hidden" name="id" value="${ produto.id }"
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
