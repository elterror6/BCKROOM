package es.uclm.bckroom.persistence;

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
}
