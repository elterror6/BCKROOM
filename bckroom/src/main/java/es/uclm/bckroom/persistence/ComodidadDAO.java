package es.uclm.bckroom.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import es.uclm.bckroom.business.domain.Comodidad;

public interface ComodidadDAO extends JpaRepository<Comodidad,Long> {

}
