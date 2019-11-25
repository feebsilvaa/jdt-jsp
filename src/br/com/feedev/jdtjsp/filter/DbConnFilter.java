package br.com.feedev.jdtjsp.filter;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

import br.com.feedev.jdtjsp.conn.SingleConnection;

/**
 * Servlet Filter implementation class DbConnFilter
 */
@WebFilter(urlPatterns = { "/*" })
public class DbConnFilter implements Filter {

	private static Connection connection;

	public void destroy() {
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		try {
			chain.doFilter(request, response);
//			System.out.println("Commitando alterações...");
			connection.commit();
		} catch (Exception e) {
			System.out.println("Erro ao realizar transação no banco de dados:");
			e.printStackTrace();
			try {
				connection.rollback();
			} catch (SQLException e1) {
				System.out.println("Erro ao realizar rollback no banco de dados: " + e1.getMessage());
			}
		}
	}

	public void init(FilterConfig fConfig) throws ServletException {
		connection = SingleConnection.getConnection();
	}

}
