package br.com.feedev.jdtjsp.model.enums;

public enum PerfilUsuario {

	ADMINISTRADOR(1, "Administrador(a)"), 
	SECRETARIO(2, "Secretário(a)"), 
	GERENTE(3, "Gerente"), 
	FUNCIONARIO(4, "Funcionário(a)");

	private Integer codigo;
	private String descricao;

	private PerfilUsuario(Integer codigo, String descricao) {
		this.codigo = codigo;
		this.descricao = descricao;
	}
	
	public Integer getCodigo() {
		return codigo;
	}

	public String getDescricao() {
		return descricao;
	}
}
