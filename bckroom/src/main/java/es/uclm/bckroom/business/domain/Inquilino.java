package es.uclm.bckroom.business.domain;

public class Inquilino extends Usuario {
	private ListaDeseos listaDeseos;
	public Inquilino() {
	}

	public Inquilino(String username, String passwd, String nombre, String primerApellido, String segundoApellido,
			Direccion direccion, ListaDeseos listaDeseos) {
		super(username, passwd, nombre, primerApellido, segundoApellido, direccion);
		this.listaDeseos=listaDeseos;
	}

	public ListaDeseos getListaDeseos() {
		return listaDeseos;
	}

	public void setListaDeseos(ListaDeseos listaDeseos) {
		this.listaDeseos = listaDeseos;
	}
	
}
