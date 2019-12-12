$(document).ready(
		function() {
			
			function limpaFormularioCep() {
				// Limpa os valores do formulário de cadastro que contém o CEP
				$('#inputCEP').val('').attr(ATTR_CLASS, classeDefault);
				$('#inputLogradouro').val('').attr(ATTR_CLASS, classeDefault);
				$('#inputNumero').val('').attr(ATTR_CLASS, classeDefault);
				$('#inputComplemento').val('').attr(ATTR_CLASS, classeDefault);
				$('#inputBairro').val('').attr(ATTR_CLASS, classeDefault);
				$('#inputCidade').val('').attr(ATTR_CLASS, classeDefault);
				$('#inputEstado').val('').attr(ATTR_CLASS, classeDefault);
			};
			
			const ATTR_CLASS = "class";
			let classeDefault = "form-control";
			let classeErro = "form-control border border-danger";
			let classeSucesso = "form-control border border-success";
			
			let cepVal = $("#inputCEP").val();
			console.log("valor do campo cep: " + cepVal);
//			if (cepVal != '') {
//				alert("Tudo ok para prosseguir");
//			}
			
			$("#inputNome").blur(function() {
				let value = $(this).val();
				if (value == '') {
					$(this).attr(ATTR_CLASS, classeErro);
				} else {
					$(this).attr(ATTR_CLASS, classeSucesso);
				}
			});
			
			$("#inputLogradouro").blur(function() {
				let value = $(this).val();
				if (value == '') {
					$(this).attr(ATTR_CLASS, classeErro);
				} else {
					$(this).attr(ATTR_CLASS, classeSucesso);
				}
			});
			
			$("#inputNumero").blur(function() {
				let value = $(this).val();
				if (value == '') {
					$(this).attr(ATTR_CLASS, classeErro);
				} else {
					$(this).attr(ATTR_CLASS, classeSucesso);
				}
			});
			
			$("#inputBairro").blur(function() {
				let value = $(this).val();
				if (value == '') {
					$(this).attr(ATTR_CLASS, classeErro);
				} else {
					$(this).attr(ATTR_CLASS, classeSucesso);
				}
			});
			
			$("#inputCidade").blur(function() {
				let value = $(this).val();
				if (value == '') {
					$(this).attr(ATTR_CLASS, classeErro);
				} else {
					$(this).attr(ATTR_CLASS, classeSucesso);
				}
			});
			
			$("#inputEstado").blur(function() {
				let value = $(this).val();
				if (value == '') {
					$(this).attr(ATTR_CLASS, classeErro);
				} else {
					$(this).attr(ATTR_CLASS, classeSucesso);
				}
			});
			
			$("#inputLogin").blur(function() {
				let value = $(this).val();
				if (value == '') {
					$(this).attr(ATTR_CLASS, classeErro);
				} else {
					$(this).attr(ATTR_CLASS, classeSucesso);
				}
			});
			
			$("#inputPassword").blur(function() {
				let value = $(this).val();
				if (value == '') {
					$(this).attr(ATTR_CLASS, classeErro);
				} else {
					$(this).attr(ATTR_CLASS, classeSucesso);
				}
			});
			
			$("#confirmPassword").blur(function() {
				let value = $(this).val();
				if (value == '') {
					$(this).attr(ATTR_CLASS, classeErro);
				} else {
					$(this).attr(ATTR_CLASS, classeSucesso);
				}
			});

			// limpaFormularioCep();

			// Quando o campo cep perde o foco.
			$("#inputCEP").blur(
					function() {

						// Nova variável "cep" somente com dígitos.
						var cep = $(this).val().replace(/\D/g, '');
						$(this).val(cep);

						// Verifica se campo cep possui valor informado.
						if (cep != "") {

							// Expressão regular para validar o CEP.
							var validacep = /^[0-9]{8}$/;

							// Valida o formato do CEP.
							if (validacep.test(cep)) {

								// Preenche os campos com "..." enquanto
								// consulta webservice.
								$("#inputLogradouro").val("...").attr(
										'readOnly', 'true');
								$("#inputBairro").val("...").attr(
										'readOnly', 'true');
								$("#inputCidade").val("...").attr(
										'readOnly', 'true');
								$("#inputEstado").val("...").attr(
										'readOnly', 'true');

								// Consulta o webservice viacep.com.br/
								$.getJSON("https://viacep.com.br/ws/" + cep
										+ "/json/?callback=?", function(dados) {

									if (!("erro" in dados)) {
										// Atualiza os campos com os valores da
										// consulta.
										$("#inputLogradouro").val(
												dados.logradouro).attr(ATTR_CLASS, classeSucesso);
										$("#inputBairro").val(dados.bairro).attr(ATTR_CLASS, classeSucesso);
										$("#inputCidade").val(dados.localidade).attr(ATTR_CLASS, classeSucesso);
										$("#inputEstado").val(dados.uf).attr(ATTR_CLASS, classeSucesso);
										$("#inputNumero").focus();
										$("#inputCEP").attr(ATTR_CLASS, classeSucesso);
									} // end if.
									else {
										// CEP pesquisado não foi encontrado.
										limpaFormularioCep();
										alert("CEP não encontrado.");
										$(this).attr(ATTR_CLASS, classeErro);
										$('#inputCEP').focus().attr(ATTR_CLASS, classeDefault);

									}
								});
							} // end if.
							else {
								// cep é inválido.
								limpaFormularioCep();
								alert("Formato de CEP inválido.");
								$(this).attr(ATTR_CLASS, classeErro);
								$('#inputCEP').focus();
							}
						} // end if.
						else {
							// cep sem valor, limpa formulário.
							limpaFormularioCep();
							$(this).attr(ATTR_CLASS, classeErro);
						}
					});
		});

function validaCampos() {

	let inputNomeVal = $('#inputNome').val();
	let inputCEPVal = $('#inputCEP').val();
	let inputLogradouroVal = $('#inputLogradouro').val();
	let inputNumeroVal = $('#inputNumero').val();
	let inputBairroVal = $('#inputBairro').val();
	let inputCidadeVal = $('#inputCidade').val();
	let inputEstadoVal = $('#inputEstado').val();
	let inputLoginVal = $('#inputLogin').val();
	let inputSenhaVal = $('#inputPassword').val();
	let inputSenhaConfirmVal = $('#confirmPassword').val();

	let errors = [];

	if (inputNomeVal == '') {
		errors.push("O campo Nome é de preenchimento obrigatório.");
	}

	if (inputCEPVal == '') {
		errors.push("O campo CEP é de preenchimento obrigatório.");
	}

	if (inputLogradouroVal == '') {
		errors.push("O campo Logradouro é de preenchimento obrigatório.");
	}

	if (inputNumeroVal == '') {
		errors.push("O campo Número é de preenchimento obrigatório.");
	}

	if (inputBairroVal == '') {
		errors.push("O campo Bairro é de preenchimento obrigatório.");
	}

	if (inputCidadeVal == '') {
		errors.push("O campo Cidade é de preenchimento obrigatório.");
	}

	if (inputEstadoVal == '') {
		errors.push("O campo Estado é de preenchimento obrigatório.");
	}

	if (inputLoginVal == '') {
		errors.push("O campo usuário é de preenchimento obrigatório.");
	}

	if (inputSenhaVal == '') {
		errors.push("O campo senha é de preenchimento obrigatório.");
	}

	if (inputSenhaConfirmVal == '') {
		errors
				.push("O campo confirmação de senha é de preenchimento obrigatório.");
	}

	if (inputSenhaVal != inputSenhaConfirmVal) {
		errors.push("Senha e confirmação de senha são diferentes.");
	}

	if (errors.length > 0) {
		$('#errorMessage').html(
				'<div class="alert alert-danger" role="alert"><ul></ul></div>');
		for (var i = 0; i < errors.length; i++) {
			$('#errorMessage div ul').append('<li>' + errors[i] + '</li>');
		}

		return false;
	}
	return true;
}
