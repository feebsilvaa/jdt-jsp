package br.com.feedev.jdtjsp.exception;

public class UsuarioExistenteException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7698007948355279205L;

	
	public UsuarioExistenteException() {
		super("Usuario já existe.");
	}
}
