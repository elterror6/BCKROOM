package es.uclm.bckroom.business.domain.exceptions;

public class PasswordNoCoincidenteExcepcion extends RuntimeException{

	public PasswordNoCoincidenteExcepcion() {
		super("Las contraseñas con coinciden.");
	}

	public PasswordNoCoincidenteExcepcion(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public PasswordNoCoincidenteExcepcion(String message, Throwable cause) {
		super(message, cause);
	}

	public PasswordNoCoincidenteExcepcion(String message) {
		super(message);
	}

	public PasswordNoCoincidenteExcepcion(Throwable cause) {
		super(cause);
	}
	
}
