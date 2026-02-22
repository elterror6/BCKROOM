package es.uclm.bckroom.business.domain;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;

@Entity
public class Propietario extends Usuario {
	@OneToMany(mappedBy = "propietario", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Inmueble> inmuebles;
	
	public Propietario() {
	    super();
	}
	
	public Propietario(Usuario usuario) {
		super(usuario);
		this.inmuebles=new ArrayList<>();
	}
	
	public Propietario(String username, String passwd, String nombre, String primerApellido, String segundoApellido,
			Direccion direccion, List<Inmueble> inmuebles) {
		super(username, passwd, nombre, primerApellido, segundoApellido, direccion);
		this.inmuebles=inmuebles;
	}

	public List<Inmueble> getInmuebles() {
		return inmuebles;
	}

	public void setInmuebles(List<Inmueble> inmuebles) {
		this.inmuebles = inmuebles;
	}
	
}
