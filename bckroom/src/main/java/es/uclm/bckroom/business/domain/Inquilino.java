package es.uclm.bckroom.business.domain;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;

@Entity
public class Inquilino extends Usuario {
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "lista_id")
	private ListaDeseos listaDeseos;
	public Inquilino() {
	}
	
	public Inquilino(Usuario usuario) {
		super(usuario);
		this.listaDeseos=new ListaDeseos();
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
