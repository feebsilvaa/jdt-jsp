package br.com.feedev.jdtjsp.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
			this.listarUsuarios(request, response);
			return;
		}

		String idParam;
		switch (acaoParam) {
		case "listar":
			this.listarUsuarios(request, response);
			break;
		case "cadastro":
			this.dispathTo("cadastra-usuario.jsp", request, response);
			break;
		case "excluir":
			idParam = request.getParameter("id");
			try {
				this.excluirUsuario(Long.valueOf(idParam));
				this.redirectTo("usuarios", response);
			} catch (SQLException e) {
				e.printStackTrace();
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
			}
			break;
		default:
			this.listarUsuarios(request, response);
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
		switch (acaoParam) {
		case "salvar":

			nomeParam = request.getParameter("nome");
			loginParam = request.getParameter("login");
			senhaParam = request.getParameter("senha");
			confirmaSenhaParam = request.getParameter("confirmaSenha");

			if (isFormValido(nomeParam, loginParam, senhaParam, confirmaSenhaParam)) {

				if (senhaParam.equals(confirmaSenhaParam)) {
					try {
						HashMap<String, String> params = new HashMap<String, String>();
						params.put("nomeParam", nomeParam);
						params.put("loginParam", loginParam);
						params.put("senhaParam", senhaParam);
						params.put("confirmaSenhaParam", confirmaSenhaParam);
						this.salvarUsuario(new Usuario(nomeParam, loginParam, senhaParam), request, response, params);
						return;
					} catch (SQLException e) {
						e.printStackTrace();
					}

				} else {
					System.out.println("Senha e confirmação de senha não são iguais.");
					request.setAttribute("errorMessage", "Senha e confirmação de senha não são iguais.");
					request.setAttribute("nomeForm", nomeParam);
					request.setAttribute("usernameForm", loginParam);
					request.setAttribute("passwordForm", senhaParam);
					request.setAttribute("passwordConfirmForm", confirmaSenhaParam);
					this.dispathTo("usuarios?acao=cadastro", request, response);
					return;
				}
			} else {
				System.out.println("Parâmetros obrigatórios.");
				request.setAttribute("errorMessage", "Parâmetros obrigatórios.");
				this.dispathTo("usuarios?acao=cadastro", request, response);
				return;
			}

			this.listarUsuarios(request, response);
			break;
		case "editar":
			idParam = request.getParameter("id");
			nomeParam = request.getParameter("nome");
			if (isFormValido(nomeParam)) {

					try {
						Usuario usuarioEditado = new Usuario();
						usuarioEditado.setNome(nomeParam);
						this.editarUsuario(Long.valueOf(idParam), usuarioEditado, request, response);
						return;
					} catch (SQLException e) {
						e.printStackTrace();
					}

			} else {
				System.out.println("Parâmetros obrigatórios.");
				request.setAttribute("errorMessage", "Parâmetros obrigatórios.");
				this.dispathTo("usuarios?acao=cadastro", request, response);
				return;
			}

			this.listarUsuarios(request, response);
			break;
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

	private void editarUsuario(Long id, Usuario usuario, HttpServletRequest request,
			HttpServletResponse response) throws SQLException, IOException {
		usuarioService.editarUsuario(id, usuario);
		System.out.println("Editado com sucesso.");
		request.setAttribute("usuarios", usuarioService.listar());
		this.redirectTo("usuarios", response);
	}

	private void salvarUsuario(Usuario novoUsuario, HttpServletRequest request, HttpServletResponse response, HashMap<String, String> params)
			throws SQLException, ServletException, IOException {

		// verifica se usuario existe
		if (usuarioService.buscarUsuarioPorLogin(novoUsuario.getLogin()) == null) {
			usuarioService.salvarUsuario(novoUsuario);
			System.out.println("Cadastrado com sucesso.");
			request.setAttribute("usuarios", usuarioService.listar());
//			request.setAttribute("successMessage", "Usuário criado com sucesso!");
//			this.dispathTo("usuarios?acao=listar", request, response);
			response.setHeader("successMessage", "Usuario criado com sucesso!");
			this.redirectTo("usuarios", response);
		} else {
			System.out.println("Usuario já existe.");
			request.setAttribute("errorMessage", "Usuario já existe.");
			request.setAttribute("nomeForm", params.get("nomeParam"));
			request.setAttribute("usernameForm", params.get("loginParam"));
			request.setAttribute("passwordForm", params.get("senhaParam"));
			request.setAttribute("passwordConfirmForm", params.get("confirmaSenhaParam"));
			this.dispathTo("usuarios?acao=cadastro", request, response);
		}
	}

	private void listarUsuarios(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List<Usuario> usuarios = usuarioService.listar();
		request.setAttribute("usuarios", usuarios);
		this.dispathTo("home.jsp", request, response);
	}

	private void redirectTo(String view, HttpServletResponse response) throws IOException {
		response.sendRedirect(view);
	}

	private void dispathTo(String view, HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getRequestDispatcher(view).forward(request, response);
	}

}
