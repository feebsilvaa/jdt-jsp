package br.com.feedev.jdtjsp.model.bean;

import java.math.BigDecimal;

public class Produto {

	private Long id;

	private String nome;

	private BigDecimal preco;

	private Integer quantidade;
	
	private CategoriaProduto categoria;

	public Produto() {
	}

	public Produto(String nome, BigDecimal preco, Integer quantidade, CategoriaProduto categoria) {
		this.nome = nome;
		this.preco = preco;
		this.quantidade = quantidade;
		this.categoria = categoria;
	}

	public Produto(Long id, String nome, BigDecimal preco, Integer quantidade, CategoriaProduto categoria) {
		this.id = id;
		this.nome = nome;
		this.preco = preco;
		this.quantidade = quantidade;
		this.categoria = categoria;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public BigDecimal getPreco() {
		return preco;
	}

	public void setPreco(BigDecimal preco) {
		this.preco = preco;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}
	
	public CategoriaProduto getCategoria() {
		return categoria;
	}

	public void setCategoria(CategoriaProduto categoria) {
		this.categoria = categoria;
	}

	@Override
	public String toString() {
		return "Produto [id=" + id + ", nome=" + nome + ", preco=" + preco + ", quantidade=" + quantidade
				+ ", categoria=" + categoria + "]";
	}

}
