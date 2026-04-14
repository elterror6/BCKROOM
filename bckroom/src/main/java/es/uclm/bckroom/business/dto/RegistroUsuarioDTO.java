package es.uclm.bckroom.business.dto;

import es.uclm.bckroom.business.domain.DireccionUsuario;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.Email;


public class RegistroUsuarioDTO {
	@NotBlank
	@Size(max=30)
	private String username;
	
	@NotBlank
    private String nombre;

    @NotBlank
    private String primerApellido;
    @NotBlank
    private String segundoApellido;

    @Email
    @NotBlank
    private String email;

    @Size(min = 6)
    private String password;

    private String repetirPassword;

    @NotNull
    private TipoUsuario tipoUsuario;

	@NotBlank
	private DireccionUsuario direccion;

	public RegistroUsuarioDTO(@NotBlank @Size(max = 30) String username, @NotBlank String nombre,
			@NotBlank String primerApellido, @NotBlank String segundoApellido, @Email @NotBlank String email,
			@Size(min = 6) String password, String repetirPassword, @NotNull TipoUsuario tipoUsuario,
			@NotBlank DireccionUsuario direccion) {
		super();
		this.username = username;
		this.nombre = nombre;
		this.primerApellido = primerApellido;
		this.segundoApellido = segundoApellido;
		this.email = email;
		this.password = password;
		this.repetirPassword = repetirPassword;
		this.tipoUsuario = tipoUsuario;
		this.direccion = direccion;
	}

	public RegistroUsuarioDTO() {
		super();
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRepetirPassword() {
		return repetirPassword;
	}

	public void setRepetirPassword(String repetirPassword) {
		this.repetirPassword = repetirPassword;
	}

	public TipoUsuario getTipoUsuario() {
		return tipoUsuario;
	}

	public void setTipoUsuario(TipoUsuario tipoUsuario) {
		this.tipoUsuario = tipoUsuario;
	}

	public DireccionUsuario getDireccion() {
		return direccion;
	}

	public void setDireccion(DireccionUsuario direccion) {
		this.direccion = direccion;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
}
