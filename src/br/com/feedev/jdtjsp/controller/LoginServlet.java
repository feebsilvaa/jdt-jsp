package br.com.feedev.jdtjsp.controller;

import static br.com.feedev.jdtjsp.config.ApplicationConstants.ATTR_ERROR_MESSAGE;
import static br.com.feedev.jdtjsp.config.ApplicationConstants.ATTR_NOME_FORM;
import static br.com.feedev.jdtjsp.config.ApplicationConstants.ATTR_PASSWORD_CONFIRM_FORM;
import static br.com.feedev.jdtjsp.config.ApplicationConstants.ATTR_PASSWORD_FORM;
import static br.com.feedev.jdtjsp.config.ApplicationConstants.ATTR_TELEFONE_FORM;
import static br.com.feedev.jdtjsp.config.ApplicationConstants.ATTR_USERNAME_FORM;
import static br.com.feedev.jdtjsp.config.ApplicationConstants.ERR_GENERICO_MSG;
import static br.com.feedev.jdtjsp.config.ApplicationConstants.ERR_PARAMETROS_OBRIGATORIOS_MSG;
import static br.com.feedev.jdtjsp.config.ApplicationConstants.ERR_USER_SENHA_INCORRETO_MSG;
import static br.com.feedev.jdtjsp.config.ApplicationConstants.PARAM_LOGIN;
import static br.com.feedev.jdtjsp.config.ApplicationConstants.PARAM_SENHA;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.feedev.jdtjsp.dao.UsuarioDao;

@WebServlet(urlPatterns = { "/loginServlet" })
public class LoginServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final String VIEW_INDEX = "index.jsp";
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String loginParam = request.getParameter(PARAM_LOGIN);
		String senhaParam = request.getParameter(PARAM_SENHA);

		HashMap<String, String> params = new HashMap<String, String>();
		params.put("loginParam", loginParam);		
		params.put("senhaParam", senhaParam);		
		
		if ((loginParam != null && !loginParam.isEmpty()) && (senhaParam != null && !senhaParam.isEmpty())) {

			try {
				UsuarioDao dao = new UsuarioDao();
				if (dao.autentica(loginParam, senhaParam)) {
					// Chama proxima servlet
					UsuarioServlet usuarioServlet = new UsuarioServlet();
					usuarioServlet.doGet(request, response);
				} else {
					this.retornaErroForm(VIEW_INDEX, ERR_USER_SENHA_INCORRETO_MSG, params, request, response);
					return;
				}			
				
			} catch (SQLException e) {
				e.printStackTrace();
				this.retornaErroForm(VIEW_INDEX, e.getMessage(), params, request, response);
				return;
			} catch (NullPointerException  e) {
				e.printStackTrace();
				this.retornaErroForm(VIEW_INDEX, ERR_GENERICO_MSG, params, request, response);
				return;
			}
					
			
		} else {
			this.retornaErroForm(VIEW_INDEX, ERR_PARAMETROS_OBRIGATORIOS_MSG, params, request, response);
			return;
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

	
	private void redirectTo(String view, HttpServletResponse response) throws IOException {
		response.sendRedirect(view);
	}

	private void dispathTo(String view, HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getRequestDispatcher(view).forward(request, response);
	}

}
