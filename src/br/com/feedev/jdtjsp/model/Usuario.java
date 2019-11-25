package br.com.feedev.jdtjsp.model;

public class Usuario {

	private String nome;

	private String login;

	private String senha;

	public Usuario() {
	}

	public Usuario(String nome, String login, String senha) {
		this.nome = nome;
		this.login = login;
		this.senha = senha;
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

	public boolean validarLoginSenha(String login, String senha) {
		if (login.equals("admin") && senha.equals("admin")) {
			return true;
		}
		return false;
	}

	@Override
	public String toString() {
		return "Usuario [login=" + login + ", senha=" + senha + "]";
	}

}