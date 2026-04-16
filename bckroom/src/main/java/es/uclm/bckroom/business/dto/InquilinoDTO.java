package es.uclm.bckroom.business.dto;

import es.uclm.bckroom.business.domain.ListaDeseos;

public class InquilinoDTO extends UsuarioDTO {
	private ListaDeseos listaDeseos;

	public InquilinoDTO() {
		super();
	}

	public InquilinoDTO(String username, String nombre, String primerApellido, String segundoApellido, String email, ListaDeseos listaDeseos) {
		super(username, nombre, primerApellido, segundoApellido, email);
		this.listaDeseos = listaDeseos;
	}

	public ListaDeseos getListaDeseos() {
		return listaDeseos;
	}

	public void setListaDeseos(ListaDeseos listaDeseos) {
		this.listaDeseos = listaDeseos;
	}
	
}
