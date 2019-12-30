package br.com.feedev.jdtjsp.controller.service;

import java.sql.SQLException;
import java.util.List;

import br.com.feedev.jdtjsp.model.bean.CategoriaProduto;
import br.com.feedev.jdtjsp.model.bean.Produto;
import br.com.feedev.jdtjsp.model.dao.CategoriaDao;
import br.com.feedev.jdtjsp.model.dao.ProdutoDao;

public class ProdutoService {

	private static ProdutoDao dao;
	private static CategoriaDao daoCategorias;

	public ProdutoService() {
		dao = new ProdutoDao();
		daoCategorias = new CategoriaDao();
	}

	public List<Produto> listar() throws SQLException {
		return dao.listar();
	}

	public Produto buscarProdutoPorNome(String nome) throws SQLException {
		return dao.buscarProdutoPorNome(nome);
	}

	public void salvarProduto(Produto novoProduto) throws SQLException {
		novoProduto.setCategoria(daoCategorias.buscarCategoriaProdutoPorId(novoProduto.getCategoria().getId()));
		dao.salvarProduto(novoProduto);
	}

	public void excluir(Long id) throws SQLException {
		dao.excluir(id);
	}

	public void editarProduto(Long id, Produto produto) throws SQLException {
		produto.setCategoria(daoCategorias.buscarCategoriaProdutoPorId(produto.getCategoria().getId()));
		dao.editarProduto(id, produto);
	}

	public Produto buscarProdutoPorId(Long id) throws SQLException {
		return dao.buscarProdutoPorId(id);
	}

	public List<CategoriaProduto> listarCategorias() throws SQLException {
		return daoCategorias.listar();
	}

}
