package br.org.flem.fwe.exception;

public class ValidacaoException extends AplicacaoException {

	private static final long serialVersionUID = 1L;

	public ValidacaoException() {
		super();
	}

	public ValidacaoException(String message) {
		super(message);
	}

	public ValidacaoException(String message, Throwable cause) {
		super(message, cause);
	}

	public ValidacaoException(Throwable cause) {
		super(cause);
	}

}
