package br.com.feedev.jdtjsp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.feedev.jdtjsp.conn.SingleConnection;
import br.com.feedev.jdtjsp.model.Usuario;

public class UsuarioDao {

	private static Connection connection;

	public UsuarioDao() {
		connection = SingleConnection.getConnection();
	}

	public boolean autentica(String login, String senha) throws SQLException {
		String sql = "select * from usuario where login = ? and senha = ?";
		PreparedStatement stmt = connection.prepareStatement(sql);

		stmt.setString(1, login);
		stmt.setString(2, senha);

		ResultSet rs = stmt.executeQuery();

		if (rs.next()) {
			return true;
		}
		return false;
	}

	public void salvarUsuario(Usuario usuario) throws SQLException {
		String sql = "insert into usuario (nome, login, senha) values (?, ?, ?)";
		PreparedStatement stmt = connection.prepareStatement(sql);

		stmt.setString(1, usuario.getNome());
		stmt.setString(2, usuario.getLogin());
		stmt.setString(3, usuario.getSenha());

		stmt.execute();

	}

	public Usuario buscarUsuarioPorLogin(String login) throws SQLException {
		String sql = "select * from usuario where login = ?";
		PreparedStatement stmt = connection.prepareStatement(sql);

		stmt.setString(1, login);

		ResultSet rs = stmt.executeQuery();

		if (rs.next()) {
			return this.resultSetToUsuario(rs);
		}
		return null;
	}

	public List<Usuario> listar() throws SQLException {
		List<Usuario> usuarios = new ArrayList<Usuario>();
		String sql = "select * from usuario";

		PreparedStatement stmt = connection.prepareStatement(sql);

		ResultSet resultSet = stmt.executeQuery();

		while (resultSet.next()) {
			usuarios.add(this.resultSetToUsuario(resultSet));
		}

		return usuarios;
	}

	public void excluir(Long id) throws SQLException {
		String sql = "delete from usuario where id = ? ";
		PreparedStatement stmt = connection.prepareStatement(sql);
		stmt.setLong(1, id);
		stmt.execute();
	}

	public void editarUsuario(Long id, Usuario usuario) throws SQLException {
		String sql = "update usuario set nome = ? where id = ? ";
		PreparedStatement stmt = connection.prepareStatement(sql);
		stmt.setString(1, usuario.getNome());
		stmt.setLong(2, id);
		stmt.executeUpdate();
	}

	private Usuario resultSetToUsuario(ResultSet rs) throws SQLException {
		return new Usuario(rs.getLong("id"), rs.getString("nome"), rs.getString("login"), rs.getString("senha"));
	}


}
