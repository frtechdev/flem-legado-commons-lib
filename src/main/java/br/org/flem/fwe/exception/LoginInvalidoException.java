package br.org.flem.fwe.exception;

public class LoginInvalidoException extends AplicacaoException{

	private static final long serialVersionUID = 1L;

	public LoginInvalidoException() {
		super();
	}

	public LoginInvalidoException(String message) {
		super(message);
	}

	public LoginInvalidoException(String message, Throwable cause) {
		super(message, cause);
	}

	public LoginInvalidoException(Throwable cause) {
		super(cause);
	}

}
