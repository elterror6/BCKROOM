package es.uclm.bckroom.business.services;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import es.uclm.bckroom.business.domain.Disponibilidad;
import es.uclm.bckroom.business.domain.Inmueble;
import es.uclm.bckroom.business.domain.Propietario;
import es.uclm.bckroom.business.domain.Usuario;
import es.uclm.bckroom.business.dto.DisponibilidadDTO;
import es.uclm.bckroom.business.dto.InmuebleDTO;
import es.uclm.bckroom.business.dto.PropietarioDTO;
import es.uclm.bckroom.persistence.InmuebleDAO;
import es.uclm.bckroom.persistence.UsuarioDAO;

public class ServicioInmueble {
	private static final Logger log = LoggerFactory.getLogger(ServicioUsuario.class);
	private final InmuebleDAO inmuebleDAO;
	private final UsuarioDAO usuarioDAO;
	
	public ServicioInmueble(InmuebleDAO inmuebleDAO, UsuarioDAO usuarioDAO) {
		super();
		this.inmuebleDAO = inmuebleDAO;
		this.usuarioDAO = usuarioDAO;
	}
	
	public void altaInmueble(InmuebleDTO inmuebleDTO, PropietarioDTO propietarioDTO) {
		Optional<Usuario> consultaUsuario = usuarioDAO.findByEmail(propietarioDTO.getEmail());
		Propietario propietario = (Propietario) consultaUsuario.get();
		
		Inmueble nuevoInmueble = new Inmueble(inmuebleDTO.getDireccion(), inmuebleDTO.getPrecioNoche(), 
				inmuebleDTO.getComodidades(), inmuebleDTO.getTipo(), inmuebleDTO.getNumeroHabitaciones(),
				inmuebleDTO.getNumeroBanios());
		propietario.addInmueble(nuevoInmueble);
		
		inmuebleDAO.save(nuevoInmueble);
		log.info("Propiedad guardada con exito:: "+nuevoInmueble);
	}
	
	public void bajaInmueble(Long idInmueble) {
		Optional<Inmueble> consultaInmueble = inmuebleDAO.findById(idInmueble);
		Inmueble inmuebleParaEliminar = consultaInmueble.get();
		
		inmuebleDAO.delete(inmuebleParaEliminar);
		log.info("Inmueble eliminado con exito:: "+inmuebleParaEliminar);
	}
	
	public List<InmuebleDTO> getInmueblesPropietario (PropietarioDTO propietarioDTO) {
		Optional<Usuario> consultaUsuario = usuarioDAO.findByEmail(propietarioDTO.getEmail());
		Propietario propietario = (Propietario) consultaUsuario.get();
		List<Inmueble> inmueblesPropietario = propietario.getInmuebles();
		List<InmuebleDTO> inmueblesPropietarioDTOs = new ArrayList<>();
		InmuebleDTO inmuebleDTO;
		
		
		for(Inmueble inmueble: inmueblesPropietario) {
			inmuebleDTO = new InmuebleDTO(propietarioDTO, inmueble.getDireccion(), inmueble.getPrecioNoche(), 
					inmueble.getDisponibilidades(), inmueble.getComodidades(), inmueble.getTipo(),
					inmueble.getNumeroHabitaciones(), inmueble.getNumeroBanios());
			inmuebleDTO.setId(inmueble.getId());
			inmueblesPropietarioDTOs.add(inmuebleDTO);
		}
		return inmueblesPropietarioDTOs;
	}
	
	public Set<DisponibilidadDTO> getDisponibilidades(Long inmuebleId) {
		Optional<Inmueble> consultaInmueble = inmuebleDAO.findById(inmuebleId);
		Inmueble inmueble = consultaInmueble.get();
		Set<DisponibilidadDTO> disponibilidadesDTO = new HashSet<>();
		
		for (Disponibilidad disponibilidad: inmueble.getDisponibilidades()) {
			DisponibilidadDTO disponibilidadDTO = new DisponibilidadDTO(inmueble.getId(), disponibilidad.getFechaInicio(), disponibilidad.getFechaFin(),
					disponibilidad.getPrecio(), disponibilidad.getPoliticaCancelacion(), disponibilidad.isDirecta());
			disponibilidadDTO.setId(disponibilidad.getId());
			disponibilidadesDTO.add(disponibilidadDTO);
		}
		
		return disponibilidadesDTO;
	}
	
	public void setNuevaDisponibilidad(DisponibilidadDTO disponibilidadDTO) {
		Optional<Inmueble> consultaInmueble = inmuebleDAO.findById(disponibilidadDTO.getId());
		Inmueble inmueble = consultaInmueble.get();
		Disponibilidad disponibilidad = new Disponibilidad(disponibilidadDTO.getFechaInicio(), disponibilidadDTO.getFechaFin(), inmueble,
				disponibilidadDTO.getPrecio(), disponibilidadDTO.getPoliticaCancelacion(), disponibilidadDTO.isDirecta());
		
		inmueble.setDisponibilidad(disponibilidad);
		
		inmuebleDAO.save(inmueble);
	}
	
}
