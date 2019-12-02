package br.com.feedev.jdtjsp.exception;

public class ProdutoExistenteException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7966789944699704869L;

	public ProdutoExistenteException() {
		super("Já existe um produto com esse nome.");
	}

}
