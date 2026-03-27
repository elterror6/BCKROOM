package es.uclm.bckroom.business.domain;

import java.util.List;

public class FiltroDTO {
	private Boolean reservaInmediata;
	private List<Long> comodidades;
	private List<Long> politicas;
	
	public FiltroDTO(Boolean reservaInmediata, List<Long> comodidades, List<Long> politicas) {
		super();
		this.reservaInmediata = reservaInmediata;
		this.comodidades = comodidades;
		this.politicas = politicas;
	}

	public Boolean getReservaInmediata() {
		return reservaInmediata;
	}

	public void setReservaInmediata(Boolean reservaInmediata) {
		this.reservaInmediata = reservaInmediata;
	}

	public List<Long> getComodidades() {
		return comodidades;
	}

	public void setComodidades(List<Long> comodidades) {
		this.comodidades = comodidades;
	}

	public List<Long> getPoliticas() {
		return politicas;
	}

	public void setPoliticas(List<Long> politicas) {
		this.politicas = politicas;
	}
	
	
	
}
