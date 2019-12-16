package br.com.feedev.jdtjsp.model.bean;

public class Usuario {

	private Long id;

	private String nome;

	private String telefone;

	private String endereco;

	private String cep;

	private String logradouro;

	private String numero;

	private String complemento;

	private String bairro;

	private String cidade;

	private String estado;

	private String login;

	private String senha;

	private String confirmaSenha;

	private File2Upload fotoFile;
	
	private String tempFoto;
	
	private String tempMiniFoto;

	private File2Upload pdfFile;
	
	private String tempPdf;

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

	public Usuario(String nome, String telefone, String cep, String logradouro, String numero, String complemento,
			String bairro, String cidade, String estado, String login, String senha, String confirmaSenha) {
		this.nome = nome;
		this.telefone = telefone;
		this.cep = cep;
		this.logradouro = logradouro;
		this.numero = numero;
		this.complemento = complemento;
		this.bairro = bairro;
		this.cidade = cidade;
		this.estado = estado;
		this.login = login;
		this.senha = senha;
		this.confirmaSenha = confirmaSenha;
	}

	public Usuario(Long id, String nome, String telefone, String cep, String logradouro, String numero,
			String complemento, String bairro, String cidade, String estado, String login, String senha) {
		this.id = id;
		this.nome = nome;
		this.telefone = telefone;
		this.cep = cep;
		this.logradouro = logradouro;
		this.numero = numero;
		this.complemento = complemento;
		this.bairro = bairro;
		this.cidade = cidade;
		this.estado = estado;
		this.login = login;
		this.senha = senha;
	}
	
	public Usuario(Long id, String nome, String telefone, String cep, String logradouro, String numero,
			String complemento, String bairro, String cidade, String estado, String login, String senha,
			String confirmaSenha, File2Upload fotoFile, File2Upload pdfFile) {
		this.id = id;
		this.nome = nome;
		this.telefone = telefone;
		this.cep = cep;
		this.logradouro = logradouro;
		this.numero = numero;
		this.complemento = complemento;
		this.bairro = bairro;
		this.cidade = cidade;
		this.estado = estado;
		this.login = login;
		this.senha = senha;
		this.confirmaSenha = confirmaSenha;
		this.fotoFile = fotoFile;
		this.pdfFile = pdfFile;
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

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getConfirmaSenha() {
		return confirmaSenha;
	}

	public File2Upload getFotoFile() {
		return fotoFile;
	}

	public void setFotoFile(File2Upload fotoFile) {
		this.fotoFile = fotoFile;
	}

	public File2Upload getPdfFile() {
		return pdfFile;
	}

	public void setPdfFile(File2Upload pdfFile) {
		this.pdfFile = pdfFile;
	}

	public String getTempFoto() {
		if (fotoFile != null) {
			tempFoto = "data:" + fotoFile.getFileType() + ";base64," + fotoFile.getFileB64();			
		}
		return tempFoto;
	}

	public String getTempPdf() {
		if (pdfFile != null) {
			tempPdf = "data:" + pdfFile.getFileType() + ";base64," + pdfFile.getFileB64();			
		}
		return tempPdf;
	}
	
	public String getTempMiniFoto() {
		if (fotoFile != null) {
			tempMiniFoto = "data:image/png;base64," + fotoFile.getMiniaturaB64();			
		}
		return tempMiniFoto;
	}

	public String getEndereco() {
		if (cep != null) {
			if (!complemento.isEmpty()) {
				return String.format("%s, %s, %s - %s - %s - %s - %s", logradouro, numero, complemento, cep, bairro,
						cidade, estado);
			} else {
				return String.format("%s, %s - %s - %s - %s - %s", logradouro, numero, cep, bairro, cidade, estado);
			}
		}
		return "";
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public void setConfirmaSenha(String confirmaSenha) {
		this.confirmaSenha = confirmaSenha;
	}

	public boolean validarLoginSenha(String login, String senha) {
		if (login.equals("admin") && senha.equals("admin")) {
			return true;
		}
		return false;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((login == null) ? 0 : login.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Usuario other = (Usuario) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (login == null) {
			if (other.login != null)
				return false;
		} else if (!login.equals(other.login))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Usuario [id=" + id + ", nome=" + nome + ", telefone=" + telefone + ", endereco=" + endereco + ", cep="
				+ cep + ", logradouro=" + logradouro + ", numero=" + numero + ", complemento=" + complemento
				+ ", bairro=" + bairro + ", cidade=" + cidade + ", estado=" + estado + ", login=" + login + ", senha="
				+ senha + ", confirmaSenha=" + confirmaSenha + ", fotoFile=" + fotoFile + ", tempFoto=" + tempFoto
				+ ", tempMiniFoto=" + tempMiniFoto + ", pdfFile=" + pdfFile + ", tempPdf=" + tempPdf + "]";
	}

}
