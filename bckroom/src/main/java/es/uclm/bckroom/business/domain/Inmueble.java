package es.uclm.bckroom.business.domain;

import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

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
