package es.uclm.bckroom.business.domain;

import jakarta.persistence.Embeddable;

@Embeddable 
public class Direccion {


    private String calle;
    private String numero;
    private String piso;
    private String puerta;
    private String ciudad;
    private String estado;
    private String codigoPostal;
    private String pais;
    
	public Direccion() {
		super();
	}


	public Direccion(String calle, String ciudad, String estado, String codigoPostal, String pais) {
		super();
		this.calle = calle;
		this.ciudad = ciudad;
		this.estado = estado;
		this.codigoPostal = codigoPostal;
		this.pais = pais;
	}


	public Direccion(String calle, String numero, String piso, String puerta, String ciudad, String estado,
			String codigoPostal, String pais) {
		super();
		this.calle = calle;
		this.numero = numero;
		this.piso = piso;
		this.puerta = puerta;
		this.ciudad = ciudad;
		this.estado = estado;
		this.codigoPostal = codigoPostal;
		this.pais = pais;
	}
    
    
	public String getCalle() {
		return calle;
	}


	public void setCalle(String calle) {
		this.calle = calle;
	}


	public String getNumero() {
		return numero;
	}


	public void setNumero(String numero) {
		this.numero = numero;
	}


	public String getPiso() {
		return piso;
	}


	public void setPiso(String piso) {
		this.piso = piso;
	}


	public String getPuerta() {
		return puerta;
	}


	public void setPuerta(String puerta) {
		this.puerta = puerta;
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
