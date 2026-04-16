package es.uclm.bckroom.business.dto;

public class UsuarioDTO {
	protected String username;
    protected String nombre;
    protected String primerApellido;
    protected String segundoApellido;
    protected String email;
    
    
	public UsuarioDTO() {
		super();
	}


	public UsuarioDTO(String username, String nombre, String primerApellido, String segundoApellido, String email) {
		super();
		this.username = username;
		this.nombre = nombre;
		this.primerApellido = primerApellido;
		this.segundoApellido = segundoApellido;
		this.email = email;
	}


	public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
		this.username = username;
	}


	public String getNombre() {
		return nombre;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	public String getPrimerApellido() {
		return primerApellido;
	}


	public void setPrimerApellido(String primerApellido) {
		this.primerApellido = primerApellido;
	}


	public String getSegundoApellido() {
		return segundoApellido;
	}


	public void setSegundoApellido(String segundoApellido) {
		this.segundoApellido = segundoApellido;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}
    
}
