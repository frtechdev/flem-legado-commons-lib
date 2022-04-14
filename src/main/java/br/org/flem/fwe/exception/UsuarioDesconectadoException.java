package br.org.flem.fwe.exception;

public class UsuarioDesconectadoException extends AplicacaoException{

	private static final long serialVersionUID = 1L;

	public UsuarioDesconectadoException() {
		super();
	}

	public UsuarioDesconectadoException(String message) {
		super(message);
	}

	public UsuarioDesconectadoException(String message, Throwable cause) {
		super(message, cause);
	}

	public UsuarioDesconectadoException(Throwable cause) {
		super(cause);
	}

}
