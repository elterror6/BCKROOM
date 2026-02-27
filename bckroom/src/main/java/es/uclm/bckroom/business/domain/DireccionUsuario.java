package es.uclm.bckroom.business.domain;

import jakarta.persistence.Embeddable;

@Embeddable 
public class DireccionUsuario {

	private TipoCalle tipoCalle;
    private String calle;
    private String ciudad;
    private String estado;
    private String codigoPostal;
    private String pais;
    
	public DireccionUsuario() {
		super();
	}


	public DireccionUsuario(TipoCalle tipoCalle, String calle, String ciudad, String estado, String codigoPostal, String pais) {
		super();
		this.tipoCalle = tipoCalle;
		this.calle = calle;
		this.ciudad = ciudad;
		this.estado = estado;
		this.codigoPostal = codigoPostal;
		this.pais = pais;
	}

	public TipoCalle getTipoCalle() {
		return tipoCalle;
	}


	public void setTipoCalle(TipoCalle tipoCalle) {
		this.tipoCalle = tipoCalle;
	}


	public String getCalle() {
		return calle;
	}


	public void setCalle(String calle) {
		this.calle = calle;
	}

	public String getCiudad() {
		return ciudad;
	}


	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}


	public String getEstado() {
		return estado;
	}


	public void setEstado(String estado) {
		this.estado = estado;
	}


	public String getCodigoPostal() {
		return codigoPostal;
	}


	public void setCodigoPostal(String codigoPostal) {
		this.codigoPostal = codigoPostal;
	}


	public String getPais() {
		return pais;
	}


	public void setPais(String pais) {
		this.pais = pais;
	}
    
}
