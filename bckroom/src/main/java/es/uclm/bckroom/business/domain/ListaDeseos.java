package es.uclm.bckroom.business.domain;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Entity;
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
	@ManyToMany
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
	public void setInmueblesDeseados(Set<Inmueble> inmueblesDeseados) {
		this.inmueblesDeseados = inmueblesDeseados;
	}
	
	public Set<Inmueble> getInmueblesDeseados() {
		return inmueblesDeseados;
	}
	public Inquilino getInquilino() {
		return inquilino;
	}
	public boolean addInmueble(Inmueble inmueble) {
		if (this.inmueblesDeseados.contains(inmueble)) {
			return false;
		} else {
			this.inmueblesDeseados.add(inmueble);
		}
		return true;
	}
	public boolean delInmueble(Inmueble inmueble) {
		if (!this.inmueblesDeseados.contains(inmueble)) {
			return false;
		} else {
			this.inmueblesDeseados.remove(inmueble);
		}
		return true;
	}
}
