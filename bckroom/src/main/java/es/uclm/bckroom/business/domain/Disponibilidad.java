package es.uclm.bckroom.business.domain;

import java.time.LocalDate;

public class Disponibilidad {
	//TODO: Realizar la configuarción de persistencia JPA
	private Long id;
	private Inmueble inmueble;
	private LocalDate fechaInicio;
	private LocalDate fechaFin;
	private double precio;
	private PoliticaCancelacion politicaCancelacion;
	private boolean directa;
	
	public Disponibilidad() {
		super();
	}

	public Disponibilidad(LocalDate fechaInicio, LocalDate fechaFin, Inmueble inmueble,double precio, PoliticaCancelacion politicaCancelacion, boolean directa) {
		super();
		this.fechaInicio = fechaInicio;
		this.fechaFin = fechaFin;
		this.precio = precio;
		this.politicaCancelacion = politicaCancelacion;
		this.directa = directa;
	}
	
	public PoliticaCancelacion getPoliticaCancelacion() {
		return politicaCancelacion;
	}

	public void setPoliticaCancelacion(PoliticaCancelacion politicaCancelacion) {
		this.politicaCancelacion = politicaCancelacion;
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
	public boolean fechasDentroDeRango(LocalDate fechaInicio, LocalDate fechaFin) {
		boolean fechaInicioBien = fechaInicio.isAfter(this.fechaInicio);
		boolean fechaFinBien = fechaFin.isBefore(this.fechaFin);
		return fechaInicioBien && fechaFinBien;
	}
	
}
