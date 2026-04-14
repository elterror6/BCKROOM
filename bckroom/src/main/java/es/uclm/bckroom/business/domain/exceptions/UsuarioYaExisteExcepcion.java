package es.uclm.bckroom.business.domain.exceptions;

public class UsuarioYaExisteExcepcion extends RuntimeException{

	public UsuarioYaExisteExcepcion() {
		super("El usuario ya existe en el sistema.");
	}

	public UsuarioYaExisteExcepcion(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public UsuarioYaExisteExcepcion(String message, Throwable cause) {
		super(message, cause);
	}

	public UsuarioYaExisteExcepcion(String message) {
		super(message);
	}

	public UsuarioYaExisteExcepcion(Throwable cause) {
		super(cause);
	}

}
