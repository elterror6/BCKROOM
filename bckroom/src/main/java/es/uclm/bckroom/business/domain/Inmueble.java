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
	public int hashCode() {
		return Objects.hash(direccion, id, precioNoche, propietario);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Inmueble other = (Inmueble) obj;
		return Objects.equals(direccion, other.direccion) && Objects.equals(id, other.id)
				&& Double.doubleToLongBits(precioNoche) == Double.doubleToLongBits(other.precioNoche)
				&& Objects.equals(propietario, other.propietario);
	}
	
}
