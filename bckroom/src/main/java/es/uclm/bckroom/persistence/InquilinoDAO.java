package es.uclm.bckroom.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import es.uclm.bckroom.business.domain.Inquilino;

public interface InquilinoDAO extends JpaRepository<Inquilino, Long>{

	public Inquilino findByUsername(String username);
	
}
