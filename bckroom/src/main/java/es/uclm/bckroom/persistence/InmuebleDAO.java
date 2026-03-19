package es.uclm.bckroom.persistence;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import es.uclm.bckroom.business.domain.Inmueble;

public interface InmuebleDAO extends JpaRepository<Inmueble, Long> {
	@Query("""
			SELECT i FROM Inmueble i
			WHERE (:ciudad IS NULL OR i.direccion.base.ciudad = :ciudad)
				AND (:precioMin IS NULL OR i.precioNoche >= :precioMin)
			    AND (:precioMax IS NULL OR i.precioNoche <= :precioMax) 
			
			""")
	List<Inmueble> buscarPorCiudadPrecioMinMax(String ciudad,
			Double precioMin, Double precioMax);
	
	@Query("""
			SELECT i FROM Inmueble i
			WHERE i.propietario.id = :propietarioId
			AND i NOT IN (
			    SELECT i2 FROM Inmueble i2
			    JOIN i2.disponibilidades d
			    WHERE :inicio <= d.fin AND :fin >= d.inicio
			)
			""")
	List<Inmueble> findDisponiblesByPropietario(Long propietarioId, Date inicio, Date fin);
	
	@Query("""
			SELECT DISTINCT i FROM Inmueble i
			JOIN i.disponibilidades d
			WHERE i.propietario.id = :propietarioId
			AND :inicio <= d.fin AND :fin >= d.inicio
			""")
	List<Inmueble> findNoDisponiblesByPropietario(Long propietarioId, Date inicio, Date fin);

	List<Inmueble> findByPropietarioId(Object id);

}
