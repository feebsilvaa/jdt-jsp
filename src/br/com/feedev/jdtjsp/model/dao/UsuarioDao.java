package br.com.feedev.jdtjsp.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.feedev.jdtjsp.config.conn.SingleConnection;
import br.com.feedev.jdtjsp.model.bean.File2Upload;
import br.com.feedev.jdtjsp.model.bean.SexoUsuario;
import br.com.feedev.jdtjsp.model.bean.Usuario;

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
		String sql = "" 
				+ "insert into usuario "
				+ "(nome, telefone, sexo, cep, logradouro, numero, "
				+ "complemento, bairro, cidade, estado, login, senha, ativo) " 
				+ "values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		PreparedStatement stmt = connection.prepareStatement(sql);

		System.out.println("Salvando usuario: " + usuario);

		stmt.setString(1, usuario.getNome());
		stmt.setString(2, usuario.getTelefone());
		stmt.setString(3, usuario.getSexo().toString());
		stmt.setString(4, usuario.getCep());
		stmt.setString(5, usuario.getLogradouro());
		stmt.setString(6, usuario.getNumero());
		stmt.setString(7, usuario.getComplemento());
		stmt.setString(8, usuario.getBairro());
		stmt.setString(9, usuario.getCidade());
		stmt.setString(10, usuario.getEstado());
		stmt.setString(11, usuario.getLogin());
		stmt.setString(12, usuario.getSenha());
		stmt.setInt(13, usuario.getAtivo() ? 1 : 0);

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

	public Usuario buscarUsuarioPorId(Long id) throws SQLException {
		String sql = "select usu.*, ufu.id as file_id, ufu.* "
				+ "from usuario usu " 
				+ "left join usuario_files_uploads ufu " 
				+ "on usu.id = ufu.usuario_id "
				+ "where usu.login <> 'admin' "
				+ "and usu.id = ?";
		PreparedStatement stmt = connection.prepareStatement(sql);

		stmt.setLong(1, id);

		ResultSet rs = stmt.executeQuery();
		Usuario usuario = null;
		while (rs.next()) {
			Usuario u = this.resultSetToUsuario(rs);
			File2Upload file = this.resultSet2FileComplete(rs);
			if (u.equals(usuario)) {
				adicionaFile(file, usuario);
			} else {
				usuario = u;
				adicionaFile(file, usuario);
			}
		}
		System.out.println(usuario);
		return usuario;
	}

	public List<Usuario> listar() throws SQLException {
		List<Usuario> usuarios = new ArrayList<Usuario>();
		String sql = ""
				+ "select usu.*, ufu.id as file_id, ufu.* "
				+ "from usuario usu "
				+ "left join usuario_files_uploads ufu "
				+ "on usu.id = ufu.usuario_id "
				+ "where usu.login <> 'admin' "
				+ "order by usu.id desc ";

		PreparedStatement stmt = connection.prepareStatement(sql);

		ResultSet resultSet = stmt.executeQuery();

		while (resultSet.next()) {
			Usuario u = this.resultSetToUsuario(resultSet);
			File2Upload file;
			String file_type = resultSet.getString("file_type");
			if (file_type != null && file_type.contains("image")) {
				file = this.resultSet2FileMini(resultSet);	
			} else {
				file = this.resultSet2FileComplete(resultSet);				
			}
			if (usuarios.contains(u)) { // Usuário ja está na lista
				Usuario usuExistente = usuarios.get(usuarios.indexOf(u));
				adicionaFile(file, usuExistente);
			} else {
				adicionaFile(file, u);
				usuarios.add(u);
			}
		}
		return usuarios;
	}

	private void adicionaFile(File2Upload file, Usuario usu) {
		if (file.getFileType() != null) {
			if (file.getFileType().contains("image")) {
				usu.setFotoFile(file);
			}
			if (file.getFileType().contains("pdf")) {
				usu.setPdfFile(file);
			}
		}
	}

	public void excluir(Long id) throws SQLException {
		String sql = "delete from usuario where id = ? and login <> 'admin'";
		PreparedStatement stmt = connection.prepareStatement(sql);
		stmt.setLong(1, id);
		stmt.execute();
	}

	public void editarUsuario(Long id, Usuario usuario) throws SQLException {
		String sql = ""
				+ "update usuario "
				+ "set nome = ?, telefone = ?, sexo = ?, ativo = ? "
				+ "where id = ? and login <> 'admin'";
		PreparedStatement stmt = connection.prepareStatement(sql);
		stmt.setString(1, usuario.getNome());
		try {
			stmt.setString(2, usuario.getTelefone());
		} catch (NullPointerException e) {
			stmt.setString(2, null);
		}
		stmt.setString(3, usuario.getSexo().toString());
		stmt.setInt(4, usuario.getAtivo() ? 1 : 0);
		stmt.setLong(5, id);
		stmt.executeUpdate();
	}

	private Usuario resultSetToUsuario(ResultSet rs) throws SQLException {
		String sexoString = rs.getString("sexo");
		if (sexoString != null) {
			return new Usuario(rs.getLong("id"), rs.getString("nome"), rs.getString("telefone"), SexoUsuario.valueOf(sexoString), rs.getString("cep"),
					rs.getString("logradouro"), rs.getString("numero"), rs.getString("complemento"), rs.getString("bairro"),
					rs.getString("cidade"), rs.getString("estado"), rs.getString("login"), rs.getString("senha"), rs.getBoolean("ativo"));			
		} else {
			return new Usuario(rs.getLong("id"), rs.getString("nome"), rs.getString("telefone"), null, rs.getString("cep"),
					rs.getString("logradouro"), rs.getString("numero"), rs.getString("complemento"), rs.getString("bairro"),
					rs.getString("cidade"), rs.getString("estado"), rs.getString("login"), rs.getString("senha"), rs.getBoolean("ativo"));

		}
		
	}
	
	private File2Upload resultSet2FileComplete(ResultSet rs) throws SQLException {
		return new File2Upload(rs.getLong("file_id"), rs.getString("file_name"), rs.getString("file_type"),
				rs.getLong("file_size"), rs.getString("file_base_64"), rs.getString("file_mini_b64"), rs.getLong("usuario_id"));
	}
	
	private File2Upload resultSet2FileMini(ResultSet rs) throws SQLException {
		return new File2Upload(rs.getLong("file_id"), rs.getString("file_name"), rs.getString("file_type"),
				rs.getLong("file_size"), rs.getString("file_mini_b64"), rs.getLong("usuario_id"));
	}
	
}
