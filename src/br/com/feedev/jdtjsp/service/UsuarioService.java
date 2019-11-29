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

	public List<Usuario> listar() throws SQLException {
		return dao.listar();
	}

	public Usuario buscarUsuarioPorLogin(String login) throws SQLException {
		return dao.buscarUsuarioPorLogin(login);
	}

	public void salvarUsuario(Usuario novoUsuario) throws SQLException {
		dao.salvarUsuario(novoUsuario);
	}

	public void excluir(Long id) throws SQLException {
		dao.excluir(id);
	}

	public void editarUsuario(Long id, Usuario usuario) throws SQLException {
		dao.editarUsuario(id, usuario);
	}

	public Usuario buscarUsuarioPorId(Long id) throws SQLException {
		return dao.buscarUsuarioPorId(id);
	}

}
