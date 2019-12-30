package br.com.feedev.jdtjsp.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.feedev.jdtjsp.config.conn.SingleConnection;
import br.com.feedev.jdtjsp.model.bean.CategoriaProduto;
import br.com.feedev.jdtjsp.model.bean.Produto;

public class ProdutoDao {

	private static Connection connection;

	public ProdutoDao() {
		connection = SingleConnection.getConnection();
	}

	public void salvarProduto(Produto produto) throws SQLException {
		String sql = "insert into produto (nome, preco, quantidade, id_categoria) values (?, ?, ?, ?)";
		PreparedStatement stmt = connection.prepareStatement(sql);
		stmt.setString(1, produto.getNome());
		stmt.setBigDecimal(2, produto.getPreco());
		stmt.setInt(3, produto.getQuantidade());
		stmt.setLong(4, produto.getCategoria().getId());
		stmt.execute();
	}

	public Produto buscarProdutoPorNome(String nomeProduto) throws SQLException {
		String sql = "select * from produto prod "
				+ "left join categoria_produto cat "
				+ "on prod.id_categoria = cat.id "
				+ "where nome = ?";
		PreparedStatement stmt = connection.prepareStatement(sql);

		stmt.setString(1, nomeProduto);

		ResultSet rs = stmt.executeQuery();

		if (rs.next()) {
			return this.resultSetToProduto(rs);
		}
		return null;
	}

	public Produto buscarProdutoPorId(Long id) throws SQLException {
		String sql = "select * from produto prod "
				+ "left join categoria_produto cat "
				+ "on prod.id_categoria = cat.id "
				+ "where prod.id = ?";
		PreparedStatement stmt = connection.prepareStatement(sql);
		stmt.setLong(1, id);
		ResultSet rs = stmt.executeQuery();
		if (rs.next()) {
			return this.resultSetToProduto(rs);
		}
		return null;
	}

	public List<Produto> listar() throws SQLException {
		List<Produto> produtos = new ArrayList<Produto>();
		String sql = "select * from produto prod "
				+ "left join categoria_produto cat "
				+ "on prod.id_categoria = cat.id ";

		PreparedStatement stmt = connection.prepareStatement(sql);

		ResultSet resultSet = stmt.executeQuery();

		while (resultSet.next()) {
			produtos.add(this.resultSetToProduto(resultSet));
		}

		return produtos;
	}

	public void excluir(Long id) throws SQLException {
		String sql = "delete from produto where id = ? ";
		PreparedStatement stmt = connection.prepareStatement(sql);
		stmt.setLong(1, id);
		stmt.execute();
	}

	public void editarProduto(Long id, Produto produto) throws SQLException {
		String sql = "update produto set nome = ?, preco = ?, quantidade = ?, id_categoria = ? where id = ?";
		PreparedStatement stmt = connection.prepareStatement(sql);
		stmt.setString(1, produto.getNome());
		stmt.setBigDecimal(2, produto.getPreco());		
		stmt.setInt(3, produto.getQuantidade());			
		stmt.setLong(4, produto.getCategoria().getId());			
		stmt.setLong(5, id);
		stmt.executeUpdate();
	}

	private Produto resultSetToProduto(ResultSet rs) throws SQLException {
		CategoriaProduto categoria = new CategoriaProduto(rs.getLong("id_categoria"), rs.getString("descricao"));
		if (categoria.getId() < 1) {
			return new Produto(rs.getLong("id"), rs.getString("nome"), rs.getBigDecimal("preco"), rs.getInt("quantidade"), null);			
		}		
		return new Produto(rs.getLong("id"), rs.getString("nome"), rs.getBigDecimal("preco"), rs.getInt("quantidade"), categoria);
	}

}
