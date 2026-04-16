package es.uclm.bckroom.business.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public class InicioSesionUsuarioDTO {
	@NotBlank
	@Email
	private String email;
	@NotBlank
	private String password;
	
	public InicioSesionUsuarioDTO() {
		super();
	}

	public InicioSesionUsuarioDTO(@NotBlank @Email String email, @NotBlank String password) {
		super();
		this.email = email;
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
}
