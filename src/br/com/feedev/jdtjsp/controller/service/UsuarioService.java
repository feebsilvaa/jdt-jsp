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
			salvarFoto(novoUsuario, usuarioSalvo);
		}
		if (novoUsuario.getPdfFile() != null) {
			salvarPdf(novoUsuario, usuarioSalvo);
		}
	}

	public void excluir(Long id) throws SQLException {
		dao.excluir(id);
	}

	public void editarUsuario(Long id, Usuario usuario) throws SQLException {
		dao.editarUsuario(id, usuario);
		Usuario usuExistente = dao.buscarUsuarioPorId(id);

		if (usuario.getFotoFile() != null) { // Se tem foto a ser salvo ou editado
			if (usuExistente.getFotoFile() != null) { // Se ja existia uma foto salva para esse usuario
				atualizarFoto(usuario, usuExistente);
			} else {
				salvarFoto(usuario, usuExistente);
			}
		}

		if (usuario.getPdfFile() != null) { // Se tem pdf a ser salvo ou editado
			if (usuExistente.getPdfFile() != null) { // Se ja existia um pdf salvo para esse usuario
				atualizarPdf(usuario, usuExistente);
			} else {
				salvarPdf(usuario, usuExistente);
			}
		}
	}

	private void atualizarPdf(Usuario usuario, Usuario usuExistente) throws SQLException {
		System.out.println("Atualizando pdf...");
		fileDao.editarFile(usuario.getPdfFile(), usuExistente.getPdfFile(), usuario.getId());
	}

	private void atualizarFoto(Usuario usuario, Usuario usuExistente) throws SQLException {
		System.out.println("Atualizando foto...");
		fileDao.editarFile(usuario.getFotoFile(), usuExistente.getFotoFile(), usuario.getId());
	}

	private void salvarPdf(Usuario novoUsuario, Usuario usuarioSalvo) throws SQLException {
		System.out.println("Salvando pdf...");
		fileDao.salvarFile(novoUsuario.getPdfFile(), usuarioSalvo.getId());
	}

	private void salvarFoto(Usuario novoUsuario, Usuario usuarioSalvo) throws SQLException {
		System.out.println("Salvando foto...");
		fileDao.salvarFile(novoUsuario.getFotoFile(), usuarioSalvo.getId());
	}

	public Usuario buscarUsuarioPorId(Long id) throws SQLException {
		return dao.buscarUsuarioPorId(id);
	}

}
