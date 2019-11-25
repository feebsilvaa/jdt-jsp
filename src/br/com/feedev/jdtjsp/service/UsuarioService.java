package br.com.feedev.jdtjsp.service;

import java.sql.SQLException;
import java.util.List;

import br.com.feedev.jdtjsp.dao.UsuarioDao;
import br.com.feedev.jdtjsp.model.Usuario;

public class UsuarioService {
	
	private static UsuarioDao dao;
	
	public UsuarioService() {
		dao = new UsuarioDao();
	}

	public List<Usuario> listar() {
		try {
			return dao.listar();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public Usuario buscarUsuarioPorLogin(String login) throws SQLException {
		return dao.buscarUsuarioPorLogin(login);
	}

	public void salvarUsuario(Usuario novoUsuario) throws SQLException {
		dao.salvarUsuario(novoUsuario);
	}

	public void excluir(String login) throws SQLException {
		dao.excluir(login);
	}

	public void editarUsuario(String oldLogin, Usuario usuario) throws SQLException {
		dao.editarUsuario(oldLogin, usuario);
	}

}
