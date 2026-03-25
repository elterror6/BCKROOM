package es.uclm.bckroom.business.domain;

import java.util.Date;
import java.util.Objects;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class Inmueble {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	@JoinColumn(name = "propietario_id")
	private Propietario propietario;
	
	@Embedded
	private DireccionInmueble direccion;
	
	@Column
	private double precioNoche;
	
	@OneToMany(mappedBy = "inmueble", cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<Disponibilidad> disponibilidades;
	
	@JoinTable(
		name = "comodidad_inmueble",
		joinColumns = @JoinColumn(name = "inmueble_id"),
		inverseJoinColumns = @JoinColumn(name = "comodidad_id")
	)
	private Set<Comodidad> comodidades;
	
	public Inmueble() {
	    super();
	}
	public Inmueble(DireccionInmueble direccion, double precioNoche) {
		super();
		this.direccion = direccion;
		this.precioNoche = precioNoche;
	}

	public DireccionInmueble getDireccion() {
		return direccion;
	}

	public void setDireccion(DireccionInmueble direccion) {
		this.direccion = direccion;
	}

	public double getPrecioNoche() {
		return precioNoche;
	}

	public void setPrecioNoche(double precioNoche) {
		this.precioNoche = precioNoche;
	}
	
	public Propietario getPropietario() {
		return propietario;
	}
	public void setPropietario(Propietario propietario) {
		this.propietario = propietario;
	}
	
	public Long getId() {
		return id;
	}
	
	public boolean disponible(Date fechaInicio, Date fechaFin) {
		if (this.disponibilidades.size()==0) {
			return false;
		}
		for (Disponibilidad disponibilidad: this.disponibilidades) {
			if (disponibilidad.fechasDentroDeRango(fechaInicio, fechaFin)) return true;
		}
		return false;
	}
	
	public Set<Disponibilidad> getDisponibilidades() {
		return disponibilidades;
	}
	public void setDisponibilidades(Set<Disponibilidad> disponibilidades) {
		this.disponibilidades = disponibilidades;
	}
	@Override
	public boolean equals(Object o) {
	    if (this == o) return true;
	    if (o == null || getClass() != o.getClass()) return false;
	    Inmueble inmueble = (Inmueble) o;
	    return id != null && id.equals(inmueble.id);
	}

	@Override
	public int hashCode() {
	    return id != null ? id.hashCode() : 0;
	}
	
}
