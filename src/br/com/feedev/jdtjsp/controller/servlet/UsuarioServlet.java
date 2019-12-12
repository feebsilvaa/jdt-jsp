package br.com.feedev.jdtjsp.controller.servlet;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.postgresql.util.PSQLException;

import br.com.feedev.jdtjsp.config.util.ApplicationConstants;
import br.com.feedev.jdtjsp.controller.service.UsuarioService;
import br.com.feedev.jdtjsp.exception.UsuarioExistenteException;
import br.com.feedev.jdtjsp.model.bean.File2Upload;
import br.com.feedev.jdtjsp.model.bean.Usuario;

@WebServlet(urlPatterns = { "/usuarios" })
@MultipartConfig
public class UsuarioServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3020240496211414881L;

	private static UsuarioService usuarioService;

	public UsuarioServlet() {
		usuarioService = new UsuarioService();
	}

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String acaoParam = request.getParameter("acao");

		if (acaoParam == null) {
			try {
				this.listarUsuarios(request, response);
			} catch (PSQLException | NullPointerException e) {
				e.printStackTrace();
				this.retornaErroForm("usuarios.jsp", "Ocorreu um erro genérico.", null, request, response);
				return;
			} catch (SQLException e) {
				e.printStackTrace();
				this.retornaErroForm("usuarios", e.getMessage(), null, request, response);
				return;
			}
			return;
		}

		String idParam;
		switch (acaoParam) {
		case "listar":
			try {
				this.listarUsuarios(request, response);
			} catch (PSQLException | NullPointerException e) {
				e.printStackTrace();
				this.retornaErroForm("usuarios.jsp", "Ocorreu um erro genérico.", null, request, response);
				return;
			} catch (SQLException e) {
				e.printStackTrace();
				this.retornaErroForm("usuarios", e.getMessage(), null, request, response);
				return;
			}
			break;
		case "excluir":
			idParam = request.getParameter("id");
			try {
				this.excluirUsuario(Long.valueOf(idParam));
				this.redirectTo("usuarios", response);
			} catch (SQLException e) {
				e.printStackTrace();
				this.retornaErroForm("usuarios", e.getMessage(), null, request, response);
				return;
			}
			break;
		case "edicao":
			idParam = request.getParameter("id");
			try {
				Usuario usuario = usuarioService.buscarUsuarioPorId(Long.valueOf(idParam));
				request.setAttribute("usuario", usuario);
				this.dispathTo("edita-usuario.jsp", request, response);
			} catch (SQLException e) {
				e.printStackTrace();
				this.retornaErroForm("usuarios", e.getMessage(), null, request, response);
				return;
			} catch (NullPointerException e) {
				e.printStackTrace();
				this.retornaErroForm("usuarios.jsp", "Ocorreu um erro genérico.", null, request, response);
				return;
			}
			break;
		default:
			try {
				this.listarUsuarios(request, response);
			} catch (SQLException e) {
				e.printStackTrace();
				this.retornaErroForm("usuarios", e.getMessage(), null, request, response);
				return;
			} catch (NullPointerException e) {
				e.printStackTrace();
				this.retornaErroForm("usuarios.jsp", "Ocorreu um erro genérico.", null, request, response);
				return;
			}
			break;
		}

	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// setando encoding do request
		request.setCharacterEncoding("UTF-8");

		String acaoParam = request.getParameter("acao");
		
		if (acaoParam == null) {
			doGet(request, response);
			return;
		}
		
		String idParam = null;
		String nomeParam = null;
		String telefoneParam = null;
		String cepParam = null;
		String logradouroParam = null;
		String numeroParam = null;
		String complementoParam = null;
		String bairroParam = null;
		String cidadeParam = null;
		String estadoParam = null;
		String loginParam = null;
		String senhaParam = null;
		String confirmaSenhaParam = null;
		File2Upload fotoFile = null;
		File2Upload pdfFile = null;

		HashMap<String, String> params = new HashMap<String, String>();

		switch (acaoParam) {
		case "salvar":

			idParam = request.getParameter("id");
			nomeParam = request.getParameter("nome");
			telefoneParam = request.getParameter("telefone");
			cepParam = request.getParameter("cep");
			logradouroParam = request.getParameter("logradouro");
			numeroParam = request.getParameter("numero");
			complementoParam = request.getParameter("complemento");
			bairroParam = request.getParameter("bairro");
			cidadeParam = request.getParameter("cidade");
			estadoParam = request.getParameter("estado");
			loginParam = request.getParameter("login");
			senhaParam = request.getParameter("senha");
			confirmaSenhaParam = request.getParameter("confirmaSenha");
			
			try {
				// FILE UPLOAD de imagem e pdf
				if (ServletFileUpload.isMultipartContent(request)) {
					Part fotoPart = request.getPart("foto");
					fotoFile = Part2FileObj(fotoPart);
					
					Part pdfPart = request.getPart("pdf");
					pdfFile = Part2FileObj(pdfPart);			
				}	
			} catch (Exception e) {
				e.printStackTrace();
			}

			// TODO: REFATORAR A DEVOLUCAO DE PARAMETROS
			params.put("nomeParam", nomeParam);
			params.put("telefoneParam", telefoneParam);
			params.put("cepParam", cepParam);
			params.put("logradouroParam", logradouroParam);
			params.put("numeroParam", numeroParam);
			params.put("complementoParam", complementoParam);
			params.put("bairroParam", bairroParam);
			params.put("cidadeParam", cidadeParam);
			params.put("estadoParam", estadoParam);
			params.put("loginParam", loginParam);
			params.put("senhaParam", senhaParam);
			params.put("confirmaSenhaParam", confirmaSenhaParam);
			if (fotoFile != null) {
				params.put("fotoParam", fotoFile.getFileB64());
			}
			if (pdfFile != null) {
				params.put("pdfParam", pdfFile.getFileB64());
			}

			Usuario userForm = new Usuario(null, nomeParam, telefoneParam, cepParam, logradouroParam, numeroParam,
					complementoParam, bairroParam, cidadeParam, estadoParam, loginParam, senhaParam, confirmaSenhaParam,
					fotoFile, pdfFile);

			System.out.println(userForm);

			if (isFormValido(userForm)) {

				if (senhaParam.equals(confirmaSenhaParam)) {
					try {
						try {
							this.salvarUsuario(userForm, request, response, params);
						} catch (UsuarioExistenteException e) {
							this.retornaErroForm("usuarios?acao=listar", e.getMessage(), params, request, response);
							return;
						} catch (NullPointerException e) {
							e.printStackTrace();
							this.retornaErroForm("usuarios?acao=listar", "Ocorreu um erro genérico.", params, request,
									response);
							return;
						}
						request.setAttribute("usuarios", usuarioService.listar());
						this.redirectTo("usuarios", response);
						return;
					} catch (SQLException e) {
						e.printStackTrace();
						this.retornaErroForm("usuarios?acao=listar", e.getMessage(), params, request, response);
						return;
					} catch (NullPointerException e) {
						e.printStackTrace();
						this.retornaErroForm("usuarios?acao=listar", "Ocorreu um erro genérico.", params, request,
								response);
						return;
					}

				} else {
					this.retornaErroForm("usuarios?acao=listar", "Senha e confirmação de senha não são iguais.", params,
							request, response);
					return;
				}
			} else {
				this.retornaErroForm("usuarios?acao=listar", "Parâmetros obrigatórios.", params, request, response);
				return;
			}

		case "editar":

			idParam = request.getParameter("id");
			nomeParam = request.getParameter("nome");
			telefoneParam = request.getParameter("telefone");

			params.put("idParam", idParam);
			params.put("nomeParam", nomeParam);
			params.put("telefoneParam", telefoneParam);

			if (isFormValido(nomeParam)) {

				try {
					Usuario usuarioEditado = new Usuario();
					usuarioEditado.setNome(nomeParam);
					usuarioEditado.setTelefone(telefoneParam);
					this.editarUsuario(Long.valueOf(idParam), usuarioEditado, request, response);
					return;
				} catch (SQLException e) {
					e.printStackTrace();
					this.retornaErroForm("usuarios?acao=edicao", e.getMessage(), params, request, response);
					return;
				} catch (NullPointerException e) {
					e.printStackTrace();
					this.retornaErroForm("usuarios?acao=edicao", "Ocorreu um erro genérico.", params, request,
							response);
					return;
				}

			} else {
				this.retornaErroForm("usuarios?acao=edicao", "Parâmetros obrigatórios.", params, request, response);
				return;
			}

		default:
			doGet(request, response);
			break;
		}

	}

	/**
	 * Converte um objeto Part para File2Upload
	 * @param part
	 * @return
	 * @throws IOException
	 */
	private File2Upload Part2FileObj(Part part) throws IOException {
		File2Upload file = null;
		byte[] fileB64 = inputSream2Byte(part.getInputStream());
		if (fileB64.length > 0) {
			file = new File2Upload();
			file.setFileB64(Base64.getEncoder().encodeToString(fileB64)); // Base64 array to String
			file.setFileName(part.getSubmittedFileName());
			file.setFileSize(part.getSize());
			file.setFileType(part.getContentType());
		}
		return file;
	}
	
	/**
	 * Converte a entrada de fluxo de dados da imagem para um array de byte
	 * @param is
	 * @return
	 * @throws IOException
	 */
	private byte[] inputSream2Byte(InputStream is) throws IOException {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		int reads = is.read();
		while (reads != -1) {
			baos.write(reads);
			reads = is.read();
		}
		return baos.toByteArray();
	}

	private boolean isFormValido(Usuario userForm) {
		if (userForm == null)
			return false;
		if ((userForm.getNome() == null) || (userForm.getNome().isEmpty()))
			return false;
		if ((userForm.getCep() == null) || (userForm.getCep().isEmpty()))
			return false;
		if ((userForm.getLogradouro() == null) || (userForm.getLogradouro().isEmpty()))
			return false;
		if ((userForm.getNumero() == null) || (userForm.getNumero().isEmpty()))
			return false;
		if ((userForm.getBairro() == null) || (userForm.getBairro().isEmpty()))
			return false;
		if ((userForm.getCidade() == null) || (userForm.getCidade().isEmpty()))
			return false;
		if ((userForm.getEstado() == null) || (userForm.getEstado().isEmpty()))
			return false;
		if ((userForm.getLogin() == null) || (userForm.getLogin().isEmpty()))
			return false;
		if ((userForm.getSenha() == null) || (userForm.getSenha().isEmpty()))
			return false;
		if ((userForm.getConfirmaSenha() == null) || (userForm.getConfirmaSenha().isEmpty()))
			return false;

		return true;
	}
	
	private boolean isFormValido(String nomeParam) {
		return (nomeParam != null && !nomeParam.isEmpty());
	}

	private void excluirUsuario(Long id) throws SQLException {
		usuarioService.excluir(id);
	}

	private void editarUsuario(Long id, Usuario usuario, HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException {
		usuarioService.editarUsuario(id, usuario);
		System.out.println("Editado com sucesso.");
		request.setAttribute("usuarios", usuarioService.listar());
		this.redirectTo("usuarios", response);
	}

	private void salvarUsuario(Usuario novoUsuario, HttpServletRequest request, HttpServletResponse response,
			HashMap<String, String> params)
			throws SQLException, ServletException, IOException, UsuarioExistenteException {

		// verifica se usuario existe
		if (usuarioService.buscarUsuarioPorLogin(novoUsuario.getLogin()) == null) {
			usuarioService.salvarUsuario(novoUsuario);
			System.out.println("Cadastrado com sucesso.");
		} else {
			throw new UsuarioExistenteException();
		}
	}

	private void retornaErroForm(String view, String message, HashMap<String, String> params,
			HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println(message);
		request.setAttribute(ApplicationConstants.ATTR_ERROR_MESSAGE, message);
		if (params != null) {
			request.setAttribute(ApplicationConstants.ATTR_NOME_FORM, params.get("nomeParam"));
			request.setAttribute(ApplicationConstants.ATTR_TELEFONE_FORM, params.get("telefoneParam"));
			request.setAttribute(ApplicationConstants.ATTR_CEP_FORM, params.get("cepParam"));
			request.setAttribute(ApplicationConstants.ATTR_LOGRADOURO_FORM, params.get("logradouroParam"));
			request.setAttribute(ApplicationConstants.ATTR_NUMERO_FORM, params.get("numeroParam"));
			request.setAttribute(ApplicationConstants.ATTR_COMPLEMENTO_FORM, params.get("complementoParam"));
			request.setAttribute(ApplicationConstants.ATTR_BAIRRO_FORM, params.get("bairroParam"));
			request.setAttribute(ApplicationConstants.ATTR_CIDADE_FORM, params.get("cidadeParam"));
			request.setAttribute(ApplicationConstants.ATTR_ESTADO_FORM, params.get("estadoParam"));
			request.setAttribute(ApplicationConstants.ATTR_USERNAME_FORM, params.get("loginParam"));
			request.setAttribute(ApplicationConstants.ATTR_PASSWORD_FORM, params.get("senhaParam"));
			request.setAttribute(ApplicationConstants.ATTR_PASSWORD_CONFIRM_FORM, params.get("confirmaSenhaParam"));
		}
		this.dispathTo(view, request, response);
	}

	private void listarUsuarios(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, SQLException {
		List<Usuario> usuarios = usuarioService.listar();
		request.setAttribute("usuarios", usuarios);
		this.dispathTo("usuarios.jsp", request, response);
	}

	private void redirectTo(String view, HttpServletResponse response) throws IOException {
		response.sendRedirect(view);
	}

	private void dispathTo(String view, HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getRequestDispatcher(view).forward(request, response);
	}

}
