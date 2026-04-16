package es.uclm.bckroom.business.dto;

import java.util.List;

import es.uclm.bckroom.business.domain.Inmueble;

public class PropietarioDTO extends UsuarioDTO {
	private List<Inmueble> inmuebles;

	public PropietarioDTO() {
		super();
	}

	public PropietarioDTO(String username, String nombre, String primerApellido, String segundoApellido, String email, List<Inmueble> inmuebles) {
		super(username, nombre, primerApellido, segundoApellido, email);
		this.inmuebles=inmuebles;
	}

	public List<Inmueble> getInmuebles() {
		return inmuebles;
	}

	public void setInmuebles(List<Inmueble> inmuebles) {
		this.inmuebles = inmuebles;
	}
}
