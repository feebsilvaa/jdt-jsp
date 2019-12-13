package br.com.feedev.jdtjsp.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.feedev.jdtjsp.config.conn.SingleConnection;
import br.com.feedev.jdtjsp.model.bean.File2Upload;

public class UsuarioFileUploadsDao {

	private static Connection connection;

	public UsuarioFileUploadsDao() {
		connection = SingleConnection.getConnection();
	}

	public void salvarFile(File2Upload file, Long idUsuario) throws SQLException {
		String sql = ""
				+ "insert into usuario_files_uploads "
				+ "(file_size, file_name, file_base_64, file_type, usuario_id) "
				+ "values (?, ?, ?, ?, ?)";
		PreparedStatement stmt = connection.prepareStatement(sql);

		stmt.setLong(1, file.getFileSize());
		stmt.setString(2, file.getFileName());
		stmt.setString(3, file.getFileB64());
		stmt.setString(4, file.getFileType());
		stmt.setLong(5, idUsuario);

		stmt.execute();

	}

	public File2Upload buscarFilePorId(Long id) throws SQLException {
		String sql = "select * from usuario_files_uploads where id = ?";
		PreparedStatement stmt = connection.prepareStatement(sql);

		stmt.setLong(1, id);

		ResultSet rs = stmt.executeQuery();

		if (rs.next()) {
			return this.resultSetToFileUploaded(rs);
		}
		return null;
	}

	public List<File2Upload> listar() throws SQLException {
		List<File2Upload> files = new ArrayList<File2Upload>();
		String sql = "select * from usuario_files_uploads";

		PreparedStatement stmt = connection.prepareStatement(sql);

		ResultSet resultSet = stmt.executeQuery();

		while (resultSet.next()) {
			files.add(this.resultSetToFileUploaded(resultSet));
		}

		return files;
	}

	public List<File2Upload> listarPorUsuario(String loginUsuario) throws SQLException {
		List<File2Upload> files = new ArrayList<File2Upload>();
		String sql = "select * from usuario_files_uploads where usuario_id = ("
				+ "select id from usuario where login = ? )";

		PreparedStatement stmt = connection.prepareStatement(sql);
		stmt.setString(1, loginUsuario);

		ResultSet resultSet = stmt.executeQuery();

		while (resultSet.next()) {
			files.add(this.resultSetToFileUploaded(resultSet));
		}

		return files;
	}

	public void excluir(Long id) throws SQLException {
		String sql = "delete from usuario_files_uploads where id = ? ";
		PreparedStatement stmt = connection.prepareStatement(sql);
		stmt.setLong(1, id);
		stmt.execute();
	}

	public void editarFile(File2Upload fileNovo, File2Upload fileAntigo, Long idUsuario) throws SQLException {
		String sql = ""
				+ "update usuario_files_uploads set "
				+ "file_size = ?, file_name = ?,"
				+ "file_base_64 = ?, file_type = ?  "
				+ "where id = ? "
				+ "and usuario_id = ?";
		PreparedStatement stmt = connection.prepareStatement(sql);
		stmt.setLong(1, fileNovo.getFileSize());
		stmt.setString(2, fileNovo.getFileName());
		stmt.setString(3, fileNovo.getFileB64());
		stmt.setString(4, fileNovo.getFileType());
		stmt.setLong(5, fileAntigo.getId());
		stmt.setLong(6, fileAntigo.getIdUsuario());
		
		stmt.executeUpdate();
		
	}
	
	private File2Upload resultSetToFileUploaded(ResultSet rs) throws SQLException {
		return new File2Upload(rs.getLong("id"), rs.getString("file_name"), rs.getString("file_type"), rs.getLong("file_size"), rs.getString("file_base_64"), rs.getLong("usuario_id"));
	}



}
