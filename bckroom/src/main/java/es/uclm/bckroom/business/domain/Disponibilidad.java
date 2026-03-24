package es.uclm.bckroom.business.domain;

import java.util.Date;

public class Disponibilidad {
	private Long id;
	private Inmueble inmueble;
	private Date fechaInicio;
	private Date fechaFin;
	private double precio;
	private PoliticaCancelacion politicaCancelacion;
	private boolean directa;
	
	public Disponibilidad() {
		super();
	}

	public Disponibilidad(Date fechaInicio, Date fechaFin, double precio, boolean directa, PoliticaCancelacion politicaCancelacion) {
		super();
		this.fechaInicio = fechaInicio;
		this.fechaFin = fechaFin;
		this.precio = precio;
		this.directa = directa;
		this.politicaCancelacion = politicaCancelacion;
	}
	
	public PoliticaCancelacion getPoliticaCancelacion() {
		return politicaCancelacion;
	}

	public void setPoliticaCancelacion(PoliticaCancelacion politicaCancelacion) {
		this.politicaCancelacion = politicaCancelacion;
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

	public Date getFechaInicio() {
		return fechaInicio;
	}

	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public Date getFechaFin() {
		return fechaFin;
	}

	public void setFechaFin(Date fechaFin) {
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
