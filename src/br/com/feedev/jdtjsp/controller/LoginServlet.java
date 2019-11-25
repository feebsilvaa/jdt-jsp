package br.com.feedev.jdtjsp.controller;

import java.io.IOException;
import java.sql.SQLException;

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
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String loginParam = req.getParameter("login");
		String senhaParam = req.getParameter("senha");
		
		if ((loginParam != null && !loginParam.isEmpty()) && (senhaParam != null && !senhaParam.isEmpty())) {

			boolean autenticado = false;
			try {
				UsuarioDao dao = new UsuarioDao();
				autenticado = dao.autentica(loginParam, senhaParam);
			} catch (SQLException e) {
				e.printStackTrace();
			}
					
			if (autenticado) {
				UsuarioServlet usuarioServlet = new UsuarioServlet();
				usuarioServlet.doGet(req, resp);
//				resp.sendRedirect("home.jsp");
			} else {
				req.setAttribute("errorMessage", "Usuário e/ou Senha incorretos.");
				req.setAttribute("usernameForm", loginParam);
				req.setAttribute("passwordForm", senhaParam);
				this.dispathTo("index.jsp", req, resp);
			}
		} else {
			resp.sendRedirect("index.jsp");	
		}
	
	}

	private void redirectTo(String view, HttpServletResponse response) throws IOException {
		response.sendRedirect(view);
	}

	private void dispathTo(String view, HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getRequestDispatcher(view).forward(request, response);
	}

}
