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

<title>JDT JSP - Produtos</title>

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
					<li class="breadcrumb-item"><a href="#">Home</a></li>
					<li class="breadcrumb-item active">Produtos</li>
				</ol>

				<div id="errorMessage">
					<c:if test="${ errorMessage != null }">
						<div class="alert alert-danger" role="alert">
							<span>${ errorMessage }</span>
						</div>
					</c:if>
				</div>

				<!-- DataTables Example -->
				<div class="card card-register mx-auto mt-5">
					<div class="card-header">Cadastro de produto</div>
					<div class="card-body">
						<form action="produtos?acao=salvar" method="post"
							onsubmit="return validaCampos() ? true : false;">
							<div class="form-group">
								<div class="form-label-group">
									<input type="text" name="nome" id="inputNome"
										value="${ nomeForm }" class="form-control" placeholder="Nome">
									<label for="inputNome">Nome</label>
								</div>
							</div>
							<div class="form-group">
								<div class="form-row">
									<div class="col-md-4">
										<div class="form-group">
											<div class="form-label-group">
												<input type="number" name="quantidade" id="inputQuantidade"
													value="${ quantidadeForm }" class="form-control" min="0"
													placeholder="Quantidade"> <label
													for="inputQuantidade">Quantidade</label>
											</div>
										</div>
									</div>
									<div class="col-md-4">
										<div class="form-group">
											<div class="form-label-group">
												<input type="number" name="preco" id="inputValor"
													value="${ precoForm }" class="form-control" min="0"
													max="999999999" placeholder="Valor"> <label
													for="inputValor">Valor</label>
											</div>
										</div>
									</div>
									<div class="col-md-4">
										<div class="form-group">
											<div class="input-group mb-3">
												<div class="input-group-prepend">
													<label class="input-group-text" for="selectCategoria">Categoria</label>
												</div>
												<select class="custom-select" id="selectCategoria"
													name="categoria">
													<option value="non_value" selected>Selecione...</option>
													<c:forEach items="${ categoriasProduto }" var="categoria">
														<option value="${ categoria.id }">${ categoria.descricao }</option>
													</c:forEach>
												</select>
											</div>
										</div>
									</div>
								</div>
							</div>
							<button class="btn btn-primary btn-block">Salvar</button>
						</form>
					</div>
				</div>

				<br> <br>


				<!-- DataTables Example -->
				<div class="card mb-3">
					<div class="card-header">
						<i class="fas fa-table"></i> Produtos
					</div>
					<div class="card-body">
						<div class="table-responsive">
							<table class="table table-bordered" id="dataTable" width="100%"
								cellspacing="0">
								<thead>
									<tr>
										<th>Nome</th>
										<th>Quantidade</th>
										<th>Valor</th>
										<th>Categoria</th>
										<th>Ações</th>
									</tr>
								</thead>
								<tfoot>
									<tr>
										<th>Nome</th>
										<th>Quantidade</th>
										<th>Valor</th>
										<th>Categoria</th>
										<th>Ações</th>
									</tr>
								</tfoot>
								<tbody>
									<c:catch var="err">
										<c:forEach items="${ produtos }" var="produto">
											<tr>
												<td><span>${ produto.nome }</span></td>
												<td><span>${ produto.quantidade }</span></td>
												<td><span>R$ ${ produto.preco }</span></td>
												<td><span>${ produto.categoria.descricao }</span></td>
												<td><label for="icoEditar" data-toggle="tooltip"
													data-placement="top" title="Editar"> <a
														id="icoEditar" class="btn btn-outline-dark"
														href="produtos?acao=edicao&id=${ produto.id }"> <i
															class="fas fa-edit"></i>
													</a>
												</label> <label for="icoExcluir" data-toggle="tooltip"
													data-placement="top" title="Excluir"> <a
														id="icoExcluir" class="btn btn-outline-dark"
														onclick="fillModal('${ produto.id }')" data-toggle="modal"
														data-target="#confirmaExclusaoModal"> <i
															class="fas fa-trash"></i>
													</a>
												</label></td>
											</tr>
										</c:forEach>
									</c:catch>
								</tbody>
							</table>
						</div>
					</div>
					<div class="card-footer small text-muted">Updated yesterday
						at 11:59 PM</div>
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

	<!-- Confirmacao Modal-->
	<div class="modal fade" id="confirmaExclusaoModal" tabindex="-1"
		role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="exampleModalLabel">Deseja
						prosseguir?</h5>
					<button class="close" type="button" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">×</span>
					</button>
				</div>
				<div class="modal-body">Selecione "Confirmar" abaixo se deseja
					excluir o registro.</div>
				<div class="modal-footer">
					<button class="btn btn-secondary" type="button"
						data-dismiss="modal">Cancelar</button>
					<a id="btnConfirmaExclusaoModal" class="btn btn-primary" href="">Confirmar</a>
				</div>
			</div>
		</div>
	</div>

	<!-- Fill Modal Function -->
	<script type="text/javascript">
		function fillModal(id) {
			console.log(id);
			$('#btnConfirmaExclusaoModal').attr("href",
					"produtos?acao=excluir&id=" + id);
		}
	</script>


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
		function validaCampos() {

			let inputNomeVal = $('#inputNome').val();
			let inputQuantidadeVal = $('#inputQuantidade').val();
			let inputValorVal = $('#inputValor').val();
			let selectCategoriaVal = $('#selectCategoria').val();

			let errors = [];

			if (inputNomeVal == '') {
				errors.push("O campo nome é de preenchimento obrigatório.");
			}

			if (inputQuantidadeVal == '') {
				errors
						.push("O campo quantidade é de preenchimento obrigatório.");
			}

			if (inputValorVal == '') {
				errors.push("O campo valor é de preenchimento obrigatório.");
			}

			if (selectCategoriaVal == 'non_value') {
				errors.push("Selecione uma categoria válida.");
			}

			if (errors.length > 0) {
				$('#errorMessage')
						.html(
								'<div class="alert alert-danger" role="alert"><ul></ul></div>');
				for (var i = 0; i < errors.length; i++) {
					$('#errorMessage div ul').append(
							'<li>' + errors[i] + '</li>');
				}

				return false;
			}
			return true;
		}
	</script>


</body>

</html>
