package es.uclm.bckroom.business.domain.exceptions;

public class PasswordNoCoincideExcepcion extends RuntimeException {

	public PasswordNoCoincideExcepcion() {
		super("La contraseña no es correcta.");
	}

	public PasswordNoCoincideExcepcion(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public PasswordNoCoincideExcepcion(String message, Throwable cause) {
		super(message, cause);
	}

	public PasswordNoCoincideExcepcion(String message) {
		super(message);
	}

	public PasswordNoCoincideExcepcion(Throwable cause) {
		super(cause);
	}

}
