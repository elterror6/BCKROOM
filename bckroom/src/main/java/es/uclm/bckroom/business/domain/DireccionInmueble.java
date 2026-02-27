package es.uclm.bckroom.business.domain;

import jakarta.persistence.MappedSuperclass;

@MappedSuperclass
public class DireccionInmueble {
	private DireccionUsuario base;
	private String numero;
	private String piso;
	private String puerta;
	
	public DireccionInmueble() {
		super();
	}

	public DireccionInmueble(TipoCalle tipoCalle, String calle, String numero, String piso,
			String puerta, String ciudad, String estado, String codigoPostal, String pais) {
		this.base = new DireccionUsuario(tipoCalle, calle, ciudad, estado, codigoPostal, pais);
		this.numero = numero;
		if (piso != null) {
			this.piso = piso;
		} else {
			this.piso = "";
		}
		
		if (puerta != null) {
			this.puerta = puerta;
		} else {
			this.puerta = "";
		}
	}

	public DireccionUsuario getBase() {
		return base;
	}

	public void setBase(DireccionUsuario base) {
		this.base = base;
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
	
}
