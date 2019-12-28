package br.com.feedev.jdtjsp.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.feedev.jdtjsp.config.conn.SingleConnection;
import br.com.feedev.jdtjsp.model.bean.CategoriaProduto;

public class CategoriaDao {

	private static Connection connection;

	public CategoriaDao() {
		connection = SingleConnection.getConnection();
	}

	public void salvarCategoria(CategoriaProduto categoriaProduto) throws SQLException {
		String sql = "insert into categoria_produto (descricao) values (?)";
		PreparedStatement stmt = connection.prepareStatement(sql);
		stmt.setString(1, categoriaProduto.getDescricao());
		stmt.execute();
	}

	public CategoriaProduto buscarCategoriaPorDescricao(String descricaoCategoria) throws SQLException {
		String sql = "select * from categoria_produto where descricao = ?";
		PreparedStatement stmt = connection.prepareStatement(sql);
		stmt.setString(1, descricaoCategoria);
		ResultSet rs = stmt.executeQuery();
		if (rs.next()) {
			return this.resultSetToCategoriaProduto(rs);
		}
		return null;
	}

	public CategoriaProduto buscarCategoriaProdutoPorId(Long id) throws SQLException {
		String sql = "select * from categoria_produto where id = ?";
		PreparedStatement stmt = connection.prepareStatement(sql);
		stmt.setLong(1, id);
		ResultSet rs = stmt.executeQuery();
		if (rs.next()) {
			return this.resultSetToCategoriaProduto(rs);
		}
		return null;
	}

	public List<CategoriaProduto> listar() throws SQLException {
		List<CategoriaProduto> categorias = new ArrayList<CategoriaProduto>();
		String sql = "select * from categoria_produto";
		PreparedStatement stmt = connection.prepareStatement(sql);
		ResultSet resultSet = stmt.executeQuery();
		while (resultSet.next()) {
			categorias.add(this.resultSetToCategoriaProduto(resultSet));
		}
		return categorias;
	}

	public void excluir(Long id) throws SQLException {
		String sql = "delete from categoria_produto where id = ? ";
		PreparedStatement stmt = connection.prepareStatement(sql);
		stmt.setLong(1, id);
		stmt.execute();
	}

	public void editarCategoria(Long id, CategoriaProduto categoriaProduto) throws SQLException {
		String sql = "update categoria_produto set descricao = ? where id = ?";
		PreparedStatement stmt = connection.prepareStatement(sql);
		stmt.setString(1, categoriaProduto.getDescricao());
		stmt.setLong(2, id);
		stmt.executeUpdate();
	}

	private CategoriaProduto resultSetToCategoriaProduto(ResultSet rs) throws SQLException {
		return new CategoriaProduto(rs.getLong("id"), rs.getString("descricao"));
	}

}
