package es.uclm.bckroom.business.domain;

import java.time.LocalDate;
import java.util.Date;

import jakarta.persistence.Id;

public class Disponibilidad {
	//TODO: Realizar la configuarción de persistencia JPA
	private Long id;
	private Inmueble inmueble;
	private LocalDate fechaInicio;
	private LocalDate fechaFin;
	private double precio;
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Inmueble getInmueble() {
		return inmueble;
	}

	public void setInmueble(Inmueble inmueble) {
		this.inmueble = inmueble;
	}

	private boolean directa;
	
	public Disponibilidad() {
		super();
	}

	public Disponibilidad(LocalDate fechaInicio, LocalDate fechaFin, double precio, boolean directa) {
		super();
		this.fechaInicio = fechaInicio;
		this.fechaFin = fechaFin;
		this.precio = precio;
		this.directa = directa;
	}

	public LocalDate getFechaInicio() {
		return fechaInicio;
	}

	public void setFechaInicio(LocalDate fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public LocalDate getFechaFin() {
		return fechaFin;
	}

	public void setFechaFin(LocalDate fechaFin) {
		this.fechaFin = fechaFin;
	}

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}

	public boolean isDirecta() {
		return directa;
	}

	public void setDirecta(boolean directa) {
		this.directa = directa;
	}
	
	public boolean fechasDentroDeRango(Date fechaInicio, Date fechaFin) {
		boolean fechaInicioBien = fechaInicio.after(this.fechaInicio);
		boolean fechaFinBien = fechaFin.before(this.fechaFin);
		return fechaInicioBien && fechaFinBien;
	}
	
}
