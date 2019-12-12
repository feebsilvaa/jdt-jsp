package br.com.feedev.jdtjsp.controller.service;

import java.sql.SQLException;
import java.util.List;

import br.com.feedev.jdtjsp.model.bean.Usuario;
import br.com.feedev.jdtjsp.model.dao.UsuarioDao;
import br.com.feedev.jdtjsp.model.dao.UsuarioFileUploadsDao;

public class UsuarioService {

	private static UsuarioDao dao;
	private static UsuarioFileUploadsDao fileDao;

	public UsuarioService() {
		dao = new UsuarioDao();
		fileDao = new UsuarioFileUploadsDao();
	}

	public List<Usuario> listar() throws SQLException {
		return dao.listar();
	}

	public Usuario buscarUsuarioPorLogin(String login) throws SQLException {
		return dao.buscarUsuarioPorLogin(login);
	}

	public void salvarUsuario(Usuario novoUsuario) throws SQLException {
		dao.salvarUsuario(novoUsuario);
		Usuario usuarioSalvo = dao.buscarUsuarioPorLogin(novoUsuario.getLogin());
		if (novoUsuario.getFotoFile() != null) {
			System.out.println("Salvando foto...");
			fileDao.salvarFile(novoUsuario.getFotoFile(), usuarioSalvo.getId());
		}
		if (novoUsuario.getPdfFile() != null) {
			System.out.println("Salvando pdf...");
			fileDao.salvarFile(novoUsuario.getPdfFile(), usuarioSalvo.getId());
		}
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
