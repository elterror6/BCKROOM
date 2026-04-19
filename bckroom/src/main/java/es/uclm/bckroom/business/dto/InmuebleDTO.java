package es.uclm.bckroom.business.dto;

import java.util.Set;

import es.uclm.bckroom.business.domain.Comodidad;
import es.uclm.bckroom.business.domain.DireccionInmueble;
import es.uclm.bckroom.business.domain.Disponibilidad;

public class InmuebleDTO {
	private PropietarioDTO propietario;
	private DireccionInmueble direccion;
	private double precioNoche;
	private Set<Disponibilidad> disponibilidades;
	private Set<Comodidad> comodidades;
	
	
	public InmuebleDTO(PropietarioDTO propietario) {
		super();
		this.propietario = propietario;
	}

	public InmuebleDTO(PropietarioDTO propietario, DireccionInmueble direccion, double precioNoche,
			Set<Disponibilidad> disponibilidades, Set<Comodidad> comodidades) {
		super();
		this.propietario = propietario;
		this.direccion = direccion;
		this.precioNoche = precioNoche;
		this.disponibilidades = disponibilidades;
		this.comodidades = comodidades;
	}

	public PropietarioDTO getPropietario() {
		return propietario;
	}

	public void setPropietario(PropietarioDTO propietario) {
		this.propietario = propietario;
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

	public Set<Disponibilidad> getDisponibilidades() {
		return disponibilidades;
	}

	public void setDisponibilidades(Set<Disponibilidad> disponibilidades) {
		this.disponibilidades = disponibilidades;
	}

	public Set<Comodidad> getComodidades() {
		return comodidades;
	}

	public void setComodidades(Set<Comodidad> comodidades) {
		this.comodidades = comodidades;
	}
	
	
}
