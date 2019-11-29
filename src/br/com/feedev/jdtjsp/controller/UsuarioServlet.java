package br.com.feedev.jdtjsp.controller;

import static br.com.feedev.jdtjsp.config.ApplicationConstants.ATTR_ERROR_MESSAGE;
import static br.com.feedev.jdtjsp.config.ApplicationConstants.ATTR_NOME_FORM;
import static br.com.feedev.jdtjsp.config.ApplicationConstants.ATTR_PASSWORD_CONFIRM_FORM;
import static br.com.feedev.jdtjsp.config.ApplicationConstants.ATTR_PASSWORD_FORM;
import static br.com.feedev.jdtjsp.config.ApplicationConstants.ATTR_TELEFONE_FORM;
import static br.com.feedev.jdtjsp.config.ApplicationConstants.ATTR_USERNAME_FORM;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.feedev.jdtjsp.exception.UsuarioExistenteException;
import br.com.feedev.jdtjsp.model.Usuario;
import br.com.feedev.jdtjsp.service.UsuarioService;

@WebServlet(urlPatterns = { "/usuarios" })
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
			} catch (SQLException e) {
				e.printStackTrace();
				this.retornaErroForm("usuarios", e.getMessage(), null, request, response);
				return;
			} catch (NullPointerException  e) {
				e.printStackTrace();
				this.retornaErroForm("usuarios.jsp", "Ocorreu um erro genérico.", null, request, response);
				return;
			}
			return;
		}

		String idParam;
		switch (acaoParam) {
		case "listar":
			try {
				this.listarUsuarios(request, response);
			} catch (SQLException e) {
				e.printStackTrace();
				this.retornaErroForm("usuarios", e.getMessage(), null, request, response);
				return;
			} catch (NullPointerException  e) {
				e.printStackTrace();
				this.retornaErroForm("usuarios.jsp", "Ocorreu um erro genérico.", null, request, response);
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
			} catch (NullPointerException  e) {
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
			} catch (NullPointerException  e) {
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

		String acaoParam = request.getParameter("acao");

		if (acaoParam == null) {
			doGet(request, response);
			return;
		}

		String idParam;
		String nomeParam;
		String loginParam;
		String senhaParam;
		String confirmaSenhaParam;
		String telefoneParam;

		HashMap<String, String> params = new HashMap<String, String>();

		switch (acaoParam) {
		case "salvar":

			nomeParam = request.getParameter("nome");
			telefoneParam = request.getParameter("telefone");
			loginParam = request.getParameter("login");
			senhaParam = request.getParameter("senha");
			confirmaSenhaParam = request.getParameter("confirmaSenha");

			params.put("nomeParam", nomeParam);
			params.put("telefoneParam", telefoneParam);
			params.put("loginParam", loginParam);
			params.put("senhaParam", senhaParam);
			params.put("confirmaSenhaParam", confirmaSenhaParam);

			if (isFormValido(nomeParam, loginParam, senhaParam, confirmaSenhaParam)) {

				if (senhaParam.equals(confirmaSenhaParam)) {
					try {
						try {
							this.salvarUsuario(new Usuario(nomeParam, telefoneParam, loginParam, senhaParam), request,
									response, params);
						} catch (UsuarioExistenteException e) {
							this.retornaErroForm("usuarios?acao=listar", e.getMessage(), params, request, response);
							return;
						} catch (NullPointerException  e) {
							e.printStackTrace();
							this.retornaErroForm("usuarios?acao=listar", "Ocorreu um erro genérico.", params, request, response);
							return;
						}
						request.setAttribute("usuarios", usuarioService.listar());
						this.redirectTo("usuarios", response);
						return;
					} catch (SQLException e) {
						e.printStackTrace();
						this.retornaErroForm("usuarios?acao=listar", e.getMessage(), params, request, response);
						return;
					} catch (NullPointerException  e) {
						e.printStackTrace();
						this.retornaErroForm("usuarios?acao=listar", "Ocorreu um erro genérico.", params, request, response);
						return;
					}

				} else {
					this.retornaErroForm("usuarios?acao=listar", "Senha e confirmação de senha não são iguais.",
							params, request, response);
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
					this.retornaErroForm("usuarios?acao=listar", e.getMessage(), params, request, response);
					return;
				} catch (NullPointerException  e) {
					e.printStackTrace();
					this.retornaErroForm("usuarios?acao=listar", "Ocorreu um erro genérico.", params, request, response);
					return;
				}

			} else {
				this.retornaErroForm("usuarios?acao=listar", "Parâmetros obrigatórios.", params, request, response);
				return;
			}

		default:
			doGet(request, response);
			break;
		}

	}

	private boolean isFormValido(String nomeParam) {
		return (nomeParam != null && !nomeParam.isEmpty());
	}

	private boolean isFormValido(String nomeParam, String loginParam, String senhaParam, String confirmaSenhaParam) {
		return (nomeParam != null && !nomeParam.isEmpty()) && (loginParam != null && !loginParam.isEmpty())
				&& (senhaParam != null && !senhaParam.isEmpty())
				&& (confirmaSenhaParam != null && !confirmaSenhaParam.isEmpty());
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
		request.setAttribute(ATTR_ERROR_MESSAGE, message);
		if (params != null) {
			request.setAttribute(ATTR_NOME_FORM, params.get("nomeParam"));
			request.setAttribute(ATTR_TELEFONE_FORM, params.get("telefoneParam"));
			request.setAttribute(ATTR_USERNAME_FORM, params.get("loginParam"));
			request.setAttribute(ATTR_PASSWORD_FORM, params.get("senhaParam"));
			request.setAttribute(ATTR_PASSWORD_CONFIRM_FORM, params.get("confirmaSenhaParam"));			
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
