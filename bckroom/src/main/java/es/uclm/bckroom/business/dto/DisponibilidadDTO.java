package es.uclm.bckroom.business.dto;

import java.time.LocalDate;

public class DisponibilidadDTO {
	private Long id;
	private Long inmuebleId;
	private LocalDate fechaInicio;
	private LocalDate fechaFin;
	private double precio;
	
	public DisponibilidadDTO() {
		super();
	}

	public DisponibilidadDTO(Long inmuebleId, LocalDate fechaInicio, LocalDate fechaFin, double precio) {
		super();
		this.inmuebleId = inmuebleId;
		this.fechaInicio = fechaInicio;
		this.fechaFin = fechaFin;
		this.precio = precio;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getInmuebleId() {
		return inmuebleId;
	}

	public void setInmuebleId(Long inmuebleId) {
		this.inmuebleId = inmuebleId;
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
	
	
	
}
