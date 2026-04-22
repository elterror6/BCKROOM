package es.uclm.bckroom.business.dto;

import java.util.Set;

import es.uclm.bckroom.business.domain.Comodidad;
import es.uclm.bckroom.business.domain.DireccionInmueble;
import es.uclm.bckroom.business.domain.Disponibilidad;
import es.uclm.bckroom.business.domain.TipoInmueble;

public class InmuebleDTO {
	private Long id;
	private PropietarioDTO propietario;
	private DireccionInmueble direccion;
	private TipoInmueble tipo;
	private int numeroHabitaciones;
	private int numeroBanios;
	private double precioNoche;
	private Set<Disponibilidad> disponibilidades;
	private Set<Comodidad> comodidades;
	
	
	public InmuebleDTO(PropietarioDTO propietario) {
		super();
		this.propietario = propietario;
	}

	public InmuebleDTO(PropietarioDTO propietario, DireccionInmueble direccion, double precioNoche,
			Set<Disponibilidad> disponibilidades, Set<Comodidad> comodidades, TipoInmueble tipo,
			int numeroHabitaciones, int numeroBanios) {
		super();
		this.propietario = propietario;
		this.direccion = direccion;
		this.precioNoche = precioNoche;
		this.disponibilidades = disponibilidades;
		this.comodidades = comodidades;
		this.tipo = tipo;
		this.numeroHabitaciones = numeroHabitaciones;
		this.numeroBanios = numeroBanios;
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

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public TipoInmueble getTipo() {
		return tipo;
	}

	public void setTipo(TipoInmueble tipo) {
		this.tipo = tipo;
	}

	public int getNumeroHabitaciones() {
		return numeroHabitaciones;
	}

	public void setNumeroHabitaciones(int numeroHabitaciones) {
		this.numeroHabitaciones = numeroHabitaciones;
	}

	public int getNumeroBanios() {
		return numeroBanios;
	}

	public void setNumeroBanios(int numeroBanios) {
		this.numeroBanios = numeroBanios;
	}
	
	
}
