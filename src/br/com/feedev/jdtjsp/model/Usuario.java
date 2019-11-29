package br.com.feedev.jdtjsp.model;

public class Usuario {

	private Long id;

	private String nome;

	private String telefone;

	private String login;

	private String senha;

	public Usuario() {
	}

	public Usuario(String nome, String telefone, String login, String senha) {
		this.nome = nome;
		this.telefone = telefone;
		this.login = login;
		this.senha = senha;
	}

	public Usuario(Long id, String nome, String login, String senha) {
		this.id = id;
		this.nome = nome;
		this.login = login;
		this.senha = senha;
	}

	public Usuario(Long id, String nome, String telefone, String login, String senha) {
		this.id = id;
		this.nome = nome;
		this.telefone = telefone;
		this.login = login;
		this.senha = senha;
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

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public boolean validarLoginSenha(String login, String senha) {
		if (login.equals("admin") && senha.equals("admin")) {
			return true;
		}
		return false;
	}

	@Override
	public String toString() {
		return "Usuario [id=" + id + ", nome=" + nome + ", telefone=" + telefone + ", login=" + login + ", senha="
				+ senha + "]";
	}

}
