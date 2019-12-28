package br.com.feedev.jdtjsp.model.enums;

public enum SexoUsuario {

	MASCULINO("Masculino"), 
	FEMININO("Feminino");

	private String descricao;
	
	private SexoUsuario(String descricao) {
		this.descricao = descricao;
	}
	
	public String getDescricao() {
		return descricao;
	}

}
