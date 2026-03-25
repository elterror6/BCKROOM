package es.uclm.bckroom.business.domain;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Disponibilidad {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@ManyToOne
    @JoinColumn(name = "inmueble_id")
	private Inmueble inmueble;
	@Embedded
	private Date fechaInicio;
	@Embedded
	private Date fechaFin;
	@Column
	private double precio;
	@Column
	private PoliticaCancelacion politicaCancelacion;
	@Column
	private boolean directa;
	
	public Disponibilidad() {
		super();
	}

	public Disponibilidad(Date fechaInicio, Date fechaFin, double precio, boolean directa, PoliticaCancelacion politicaCancelacion, Inmueble inmueble) {
		super();
		this.fechaInicio = fechaInicio;
		this.fechaFin = fechaFin;
		this.precio = precio;
		this.directa = directa;
		this.politicaCancelacion = politicaCancelacion;
		this.inmueble = inmueble;
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
