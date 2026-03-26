package es.uclm.bckroom.business.domain;

import java.util.Objects;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;

@Entity
public class Comodidad {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column
	private String nombre;
	@ManyToMany(mappedBy="comodidades")
	private Set<Inmueble> inmuebles;
	
	
	public Comodidad() {
		super();
	}
	
	public Comodidad(Long id, String nombre) {
		super();
		this.id = id;
		this.nombre = nombre;
	}

	public Comodidad(Long id, String nombre, Set<Inmueble> inmuebles) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.inmuebles = inmuebles;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Set<Inmueble> getInmuebles() {
		return inmuebles;
	}

	public void setInmuebles(Set<Inmueble> inmuebles) {
		this.inmuebles = inmuebles;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, nombre);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Comodidad other = (Comodidad) obj;
		return Objects.equals(id, other.id) && Objects.equals(nombre, other.nombre);
	}

	
}
