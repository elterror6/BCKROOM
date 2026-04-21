package es.uclm.bckroom.business.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import es.uclm.bckroom.business.domain.Inmueble;
import es.uclm.bckroom.business.domain.Propietario;
import es.uclm.bckroom.business.domain.Usuario;
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
		
		Inmueble nuevoInmueble = new Inmueble(inmuebleDTO.getDireccion(), inmuebleDTO.getPrecioNoche(), inmuebleDTO.getComodidades());
		propietario.addInmueble(nuevoInmueble);
		
		inmuebleDAO.save(nuevoInmueble);
		log.info("Propiedad guardada con exito:: "+nuevoInmueble);
	}
	
	public List<InmuebleDTO> getInmueblesPropietario (PropietarioDTO propietarioDTO) {
		Optional<Usuario> consultaUsuario = usuarioDAO.findByEmail(propietarioDTO.getEmail());
		Propietario propietario = (Propietario) consultaUsuario.get();
		List<Inmueble> inmueblesPropietario = propietario.getInmuebles();
		List<InmuebleDTO> inmueblesPropietarioDTOs = new ArrayList<>();
		InmuebleDTO inmuebleDTO;
		
		
		for(Inmueble inmueble: inmueblesPropietario) {
			inmuebleDTO = new InmuebleDTO(propietarioDTO, inmueble.getDireccion(), inmueble.getPrecioNoche(), inmueble.getDisponibilidades(), inmueble.getComodidades());
			inmueblesPropietarioDTOs.add(inmuebleDTO);
		}
		return inmueblesPropietarioDTOs;
	}
	
}
