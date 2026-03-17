package es.uclm.bckroom.business.domain;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.JoinColumn;

@Entity
public class ListaDeseos {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(
		name = "lista_inmueble",
		joinColumns = @JoinColumn(name = "lista_id"),
		inverseJoinColumns = @JoinColumn(name = "inmueble_id")
	)
	private Set<Inmueble> inmueblesDeseados;
	@OneToOne(mappedBy="listaDeseos")
	private Inquilino inquilino;
	public ListaDeseos() {
		this.inmueblesDeseados = new HashSet<>();
	}
	public void setInmueblesDeseados(Inquilino inquilino,Set<Inmueble> inmueblesDeseados) {
		this.inquilino = inquilino;
		this.inmueblesDeseados = inmueblesDeseados;
	}
	
	public Set<Inmueble> getInmueblesDeseados() {
		return inmueblesDeseados;
	}
	public void setInmueblesDeseados(Set<Inmueble> inmueblesDeseados) {
		this.inmueblesDeseados = inmueblesDeseados;
	}
	public void setInquilino(Inquilino inquilino) {
		this.inquilino = inquilino;
	}
	public Inquilino getInquilino() {
		return inquilino;
	}
	public boolean toggleInmueble(Inmueble inmueble) {
	    if (inmueblesDeseados.contains(inmueble)) {
	        inmueblesDeseados.remove(inmueble);
	        return false;
	    } else {
	        inmueblesDeseados.add(inmueble);
	        return true;
	    }
	}
}
