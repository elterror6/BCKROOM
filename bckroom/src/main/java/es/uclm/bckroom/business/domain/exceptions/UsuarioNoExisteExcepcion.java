package es.uclm.bckroom.business.domain.exceptions;

public class UsuarioNoExisteExcepcion extends RuntimeException{

	public UsuarioNoExisteExcepcion() {
		super("El usuario no existe.");
	}

	public UsuarioNoExisteExcepcion(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public UsuarioNoExisteExcepcion(String message, Throwable cause) {
		super(message, cause);
	}

	public UsuarioNoExisteExcepcion(String message) {
		super(message);
	}

	public UsuarioNoExisteExcepcion(Throwable cause) {
		super(cause);
	}
	
}
