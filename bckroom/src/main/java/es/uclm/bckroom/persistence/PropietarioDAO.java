package es.uclm.bckroom.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import es.uclm.bckroom.business.domain.Propietario;

public interface PropietarioDAO extends JpaRepository<Propietario, Long> {

}
