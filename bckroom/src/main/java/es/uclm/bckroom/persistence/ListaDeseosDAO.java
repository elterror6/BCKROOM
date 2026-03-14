package es.uclm.bckroom.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import es.uclm.bckroom.business.domain.ListaDeseos;
import es.uclm.bckroom.business.domain.Usuario;

public interface ListaDeseosDAO extends JpaRepository<ListaDeseos, Long> {

	ListaDeseos findByUsuario(Usuario usuario);
	
}
