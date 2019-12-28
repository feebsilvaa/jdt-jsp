package br.com.feedev.jdtjsp.model.bean;

public class CategoriaProduto {

	private Long id;

	private String descricao;

	public CategoriaProduto() {
	}

	public CategoriaProduto(Long id, String descricao) {
		super();
		this.id = id;
		this.descricao = descricao;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	@Override
	public String toString() {
		return "CategoriaProduto [id=" + id + ", descricao=" + descricao + "]";
	}

}
