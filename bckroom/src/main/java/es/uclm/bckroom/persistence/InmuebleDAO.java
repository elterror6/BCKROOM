package es.uclm.bckroom.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import es.uclm.bckroom.business.domain.Inmueble;

public interface InmuebleDAO extends JpaRepository<Inmueble, Long> {

}
