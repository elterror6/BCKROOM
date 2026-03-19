package es.uclm.bckroom.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import es.uclm.bckroom.business.domain.Disponibilidad;

public interface DisponibilidadDAO extends JpaRepository<Disponibilidad, Long> {

}
