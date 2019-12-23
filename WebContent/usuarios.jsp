<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page contentType="text/html; charset=UTF-8"%>

<!DOCTYPE html>
<html lang="pt-br">

<head>

<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="">
<meta name="author" content="">

<title>JDT JSP - Usuários</title>

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
					<li class="breadcrumb-item active">Usuários</li>
				</ol>

				<%-- <%
        	pageContext.setAttribute("successMessageHeader", response.getHeader("successMessage"));
		%>
        
        <c:if test="${ !empty successMessageHeader}">
	        <div class="alert alert-success" role="alert">
			  <span>${ successMessageHeader }</span>
			</div>	        
        </c:if> --%>

				<div id="errorMessage">
					<c:if test="${ errorMessage != null }">
						<div class="alert alert-danger" role="alert">
							<span>${ errorMessage }</span>
						</div>
					</c:if>
				</div>

				<!-- 
				Icon Cards
				<div class="row">
					<div class="col-xl-3 col-sm-6 mb-3">
						<div class="card text-white bg-primary o-hidden h-100">
							<div class="card-body">
								<div class="card-body-icon">
									<i class="fas fa-fw fa-comments"></i>
								</div>
								<div class="mr-5">26 New Messages!</div>
							</div>
							<a class="card-footer text-white clearfix small z-1" href="#">
								<span class="float-left">View Details</span> <span
								class="float-right"> <i class="fas fa-angle-right"></i>
							</span>
							</a>
						</div>
					</div>
					<div class="col-xl-3 col-sm-6 mb-3">
						<div class="card text-white bg-warning o-hidden h-100">
							<div class="card-body">
								<div class="card-body-icon">
									<i class="fas fa-fw fa-list"></i>
								</div>
								<div class="mr-5">11 New Tasks!</div>
							</div>
							<a class="card-footer text-white clearfix small z-1" href="#">
								<span class="float-left">View Details</span> <span
								class="float-right"> <i class="fas fa-angle-right"></i>
							</span>
							</a>
						</div>
					</div>
					<div class="col-xl-3 col-sm-6 mb-3">
						<div class="card text-white bg-success o-hidden h-100">
							<div class="card-body">
								<div class="card-body-icon">
									<i class="fas fa-fw fa-shopping-cart"></i>
								</div>
								<div class="mr-5">123 New Orders!</div>
							</div>
							<a class="card-footer text-white clearfix small z-1" href="#">
								<span class="float-left">View Details</span> <span
								class="float-right"> <i class="fas fa-angle-right"></i>
							</span>
							</a>
						</div>
					</div>
					<div class="col-xl-3 col-sm-6 mb-3">
						<div class="card text-white bg-danger o-hidden h-100">
							<div class="card-body">
								<div class="card-body-icon">
									<i class="fas fa-fw fa-life-ring"></i>
								</div>
								<div class="mr-5">13 New Tickets!</div>
							</div>
							<a class="card-footer text-white clearfix small z-1" href="#">
								<span class="float-left">View Details</span> <span
								class="float-right"> <i class="fas fa-angle-right"></i>
							</span>
							</a>
						</div>
					</div>
				</div>
 -->
				<%-- 
				<!-- Area Chart Example-->
        <div class="card mb-3">
          <div class="card-header">
            <i class="fas fa-chart-area"></i>
            Area Chart Example</div>
          <div class="card-body">
            <canvas id="myAreaChart" width="100%" height="30"></canvas>
          </div>
          <div class="card-footer small text-muted">Updated yesterday at 11:59 PM</div>
        </div> 
        --%>
				<div class="card card-register mx-auto mt-5">
					<div class="card-header">Cadastro de usuário</div>
					<div class="card-body">
						<form action="usuarios?acao=salvar" method="post"
							enctype="multipart/form-data"
							onsubmit="return validaCampos() ? true : false;">

							<div class="form-group">
								<div class="form-row">
									<div class="col-md-6">
										<div class="form-label-group">
											<input type="text" name="nome" id="inputNome"
												value="${ nomeForm }" class="form-control"
												placeholder="Nome"> <label for="inputNome">Nome
												*</label>
										</div>
									</div>
									<div class="col-md-6">
										<div class="form-label-group">
											<input type="text" name="telefone" id="inputTelefone"
												value="${ telefoneForm }" class="form-control"
												placeholder="Telefone"> <label for="inputTelefone">Telefone</label>
										</div>
									</div>
								</div>
							</div>

							<div class="form-group">
								<div class="form-row">
									<div class="col-md-3">
										<div class="form-label-group">
											<input type="text" name="cep" id="inputCEP"
												value="${ cepForm }" class="form-control" placeholder="CEP">
											<label for="inputCEP">CEP *</label>
										</div>
									</div>
									<div class="col-md-7">
										<div class="form-label-group">
											<input type="text" name="logradouro" id="inputLogradouro"
												value="${ logradouroForm }" class="form-control"
												placeholder="Logradouro"> <label
												for="inputLogradouro">Logradouro *</label>
										</div>
									</div>
									<div class="col-md-2">
										<div class="form-label-group">
											<input type="text" name="numero" id="inputNumero"
												value="${ numeroForm }" class="form-control"
												placeholder="Numero"> <label for="inputNumero">Numero
												*</label>
										</div>
									</div>
								</div>
							</div>


							<div class="form-group">
								<div class="form-row">
									<div class="col-md-3">
										<div class="form-label-group">
											<input type="text" name="complemento" id="inputComplemento"
												value="${ complementoForm }" class="form-control"
												placeholder="Complemento"> <label
												for="inputComplemento">Complemento</label>
										</div>
									</div>
									<div class="col-md-4">
										<div class="form-label-group">
											<input type="text" name="bairro" id="inputBairro"
												value="${ bairroForm }" class="form-control"
												placeholder="Bairro"> <label for="inputBairro">Bairro
												*</label>
										</div>
									</div>
									<div class="col-md-3">
										<div class="form-label-group">
											<input type="text" name="cidade" id="inputCidade"
												value="${ cidadeForm }" class="form-control"
												placeholder="Cidade"> <label for="inputCidade">Cidade
												*</label>
										</div>
									</div>
									<div class="col-md-2">
										<div class="form-label-group">
											<input type="text" name="estado" id="inputEstado"
												value="${ estadoForm }" class="form-control"
												placeholder="Estado"> <label for="inputEstado">Estado
												*</label>
										</div>
									</div>
								</div>
							</div>

							<div class="form-group">
								<div class="form-row">
									<div class="col-md-4">
										<div class="form-label-group">
											<input type="text" name="login" id="inputLogin"
												value="${ usernameForm }" class="form-control"
												placeholder="Usuário"> <label for="inputLogin">Usuário
												*</label>
										</div>
									</div>
									<div class="col-md-4">
										<div class="form-label-group">
											<input type="password" name="senha" id="inputPassword"
												value="${ passwordForm }" class="form-control"
												placeholder="Senha"> <label for="inputPassword">Senha
												*</label>
										</div>
									</div>
									<div class="col-md-4">
										<div class="form-label-group">
											<input type="password" name="confirmaSenha"
												value="${ passwordConfirmForm }" id="confirmPassword"
												class="form-control" placeholder="Confirmação de Senha">
											<label for="confirmPassword">Confirmação de Senha *</label>
										</div>
									</div>
								</div>
							</div>
							<div class="form-row">
								<div class="col-md-4">
									<div class="custom-file">
										<input type="file" name="foto" id="foto"
											class="custom-file-input" accept="image/*"> <label
											class="custom-file-label" for="foto">Escolha uma
											foto...</label>
									</div>
								</div>
								<div class="col-md-4">
									<div class="custom-file">
										<input type="file" name="pdf" id="pdf"
											class="custom-file-input" accept="application/pdf"> <label
											class="custom-file-label" for="pdf">Escolha um PDF...</label>
									</div>
								</div>
								<div class="col-md-2">
									<div class="form-group">
										<div class="form-check">
											<input class="form-check-input" 
												id="usuarioAtivo" name="usuarioAtivo"
												type="checkbox" value="${ ativoForm }"> <label class="form-check-label"
												for="usuarioAtivo"> Ativo </label>
										</div>
									</div>
								</div>
							</div>
							<div class="form-row">
								<div class="offset-md-10 col-md-2">
									<div class="form-group">
										<button class="btn btn-primary btn-block">Salvar</button>
									</div>
								</div>
							</div>
						</form>
					</div>
				</div>

				<br> <br>


				<!-- DataTables Example -->
				<div class="card mb-3">
					<div class="card-header">
						<i class="fas fa-table"></i> Usuários
					</div>
					<div class="card-body">
						<div class="table-responsive">
							<table class="table table-bordered" id="dataTable">
								<thead>
									<tr>
										<th>Id</th>
										<th>Nome</th>
										<th>Telefone</th>
										<th>Endereço</th>
										<th>Username</th>
										<th>Ativo</th>
										<th>Imagem</th>
										<th>Ações</th>
									</tr>
								</thead>
								<tfoot>
									<tr>
										<th>Id</th>
										<th>Nome</th>
										<th>Telefone</th>
										<th>Endereço</th>
										<th>Username</th>
										<th>Ativo</th>
										<th>Imagem</th>
										<th>Ações</th>
									</tr>
								</tfoot>
								<tbody>
									<c:catch var="err">
										<c:forEach items="${ usuarios }" var="usuario">
											<tr>
												<td><span>${ usuario.id }</span></td>
												<td><span>${ usuario.nome }</span></td>
												<td><span>${ usuario.telefone }</span></td>
												<td><span>${ usuario.endereco }</span></td>
												<td><span>${ usuario.login }</span></td>
												<td><span>${ usuario.ativo }</span></td>
												<td><c:if test="${ !empty usuario.tempMiniFoto }">
														<span> <a
															href="usuarios?acao=downloadFile&idFile=${ usuario.fotoFile.id }">
																<img src="${ usuario.tempMiniFoto }"
																class="img-thumbnail-sm" alt="${ usuario.login }"
																title="${ usuario.login }">
														</a>
														</span>
													</c:if> <c:if test="${ empty usuario.tempMiniFoto }">
														<span> <i class="fas fa-user"></i></span>
													</c:if></td>
												<td><label for="icoEditar" data-toggle="tooltip"
													data-placement="top" title="Editar"> <a
														id="icoEditar" class="btn btn-outline-dark"
														href="usuarios?acao=edicao&id=${ usuario.id }"> <i
															class="fas fa-edit"></i>
													</a>
												</label> <label for="icoExcluir" data-toggle="tooltip"
													data-placement="top" title="Excluir"> <a
														id="icoExcluir" class="btn btn-outline-dark"
														onclick="fillModal('${ usuario.id }')" data-toggle="modal"
														data-target="#confirmaExclusaoModal"> <i
															class="fas fa-trash"></i>
													</a>
												</label>
												<c:if test="${ !empty usuario.tempPdf}">
														<label for="icoPDF" data-toggle="tooltip"
															data-placement="top" title="Download PDF"> <a
															id="icoPDF" class="btn btn-outline-dark"
															href="usuarios?acao=downloadFile&idFile=${ usuario.pdfFile.id }">
																<i class="fas fa-file-pdf"></i>
														</a>
														</label>
													</c:if></td>
											</tr>
										</c:forEach>
									</c:catch>
									<c:if test="err">
										<span>${ err.message }</span>
									</c:if>
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
					"usuarios?acao=excluir&id=" + id);
		}
	</script>


	<!-- Bootstrap core JavaScript-->
	<script src="resources/static/vendor/jquery/jquery.min.js"></script>
	<script
		src="resources/static/vendor/bootstrap/js/bs-custom-file-input.min.js"
		type="text/javascript"></script>
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

	<script src="resources/static/js/form_usuarios_validator.js"></script>

	<script type="text/javascript">
		$(document).ready(function() {
			bsCustomFileInput.init()
		});
	</script>
</body>

</html>
