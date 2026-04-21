package es.uclm.bckroom.business.services;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import es.uclm.bckroom.business.domain.DireccionUsuario;
import es.uclm.bckroom.business.domain.Inmueble;
import es.uclm.bckroom.business.domain.Inquilino;
import es.uclm.bckroom.business.domain.ListaDeseos;
import es.uclm.bckroom.business.domain.Propietario;
import es.uclm.bckroom.business.domain.Usuario;
import es.uclm.bckroom.business.domain.exceptions.PasswordNoCoincideExcepcion;
import es.uclm.bckroom.business.domain.exceptions.PasswordNoCoincidenteExcepcion;
import es.uclm.bckroom.business.domain.exceptions.UsuarioNoExisteExcepcion;
import es.uclm.bckroom.business.domain.exceptions.UsuarioYaExisteExcepcion;
import es.uclm.bckroom.business.dto.InicioSesionUsuarioDTO;
import es.uclm.bckroom.business.dto.InquilinoDTO;
import es.uclm.bckroom.business.dto.PropietarioDTO;
import es.uclm.bckroom.business.dto.RegistroUsuarioDTO;
import es.uclm.bckroom.business.dto.TipoUsuario;
import es.uclm.bckroom.business.dto.UsuarioDTO;
import es.uclm.bckroom.persistence.UsuarioDAO;

@Service
public class ServicioUsuario {
	private static final Logger log = LoggerFactory.getLogger(ServicioUsuario.class);
	private final UsuarioDAO usuarioDAO;
	private final BCryptPasswordEncoder codificador;
	
	public ServicioUsuario(UsuarioDAO usuarioDAO) {
		this.usuarioDAO = usuarioDAO;
		this.codificador = new BCryptPasswordEncoder();
	}
	
	public void registrar(RegistroUsuarioDTO usuarioDTO) {
		Optional<Usuario> resultado = usuarioDAO.findByEmail(usuarioDTO.getEmail());
		Usuario usuario = resultado.get();
		TipoUsuario tipoUsuario = usuarioDTO.getTipoUsuario();
		String emailUsuario, usernameUsuario, nombreUsuario,
			primerApellidoUsuario, segundoApellidoUsuario,
			passwordUsuario;
		DireccionUsuario direccionUsuario;
		
		if (usuario != null) throw new UsuarioYaExisteExcepcion();
		
		if (usuarioDTO.getPassword() != usuarioDTO.getRepetirPassword()) throw new PasswordNoCoincidenteExcepcion();
		
		emailUsuario = usuarioDTO.getEmail();
		usernameUsuario = usuarioDTO.getUsername();
		nombreUsuario = usuarioDTO.getNombre();
		primerApellidoUsuario = usuarioDTO.getPrimerApellido();
		segundoApellidoUsuario = usuarioDTO.getSegundoApellido();
		passwordUsuario = codificador.encode(usuarioDTO.getPassword());
		direccionUsuario = usuarioDTO.getDireccion();
		
		if (tipoUsuario == TipoUsuario.INQUILINO) {
			usuario = new Inquilino(emailUsuario, usernameUsuario, passwordUsuario,
									nombreUsuario, primerApellidoUsuario,
									segundoApellidoUsuario, direccionUsuario, 
									new ListaDeseos());
		} else {
			usuario = new Propietario(emailUsuario, usernameUsuario, passwordUsuario,
									nombreUsuario, primerApellidoUsuario,
									segundoApellidoUsuario, direccionUsuario);
		}
		
		try {
			usuarioDAO.save(usuario);
			log.info("["+LocalDate.now()+"] Usuario "+ usuario + "guardado correctamente.");
		} catch (RuntimeException e) {
			log.error("["+LocalDate.now()+"] Error al guardar el usuario en la base de datos. Detalles: "+e.getMessage());
		}
	}
	
	public UsuarioDTO inicioSesion(InicioSesionUsuarioDTO usuarioDTO) {
		Optional<Usuario> resultado = usuarioDAO.findByEmail(usuarioDTO.getEmail());
		Usuario usuario = resultado.get();
		boolean passwordCoincide;
		String email, username, nombre, primerApellido, segundoApellido;
		UsuarioDTO usuarioIniciadoSesion = null;
		
		if (usuario == null) throw new UsuarioNoExisteExcepcion(); 
		
		passwordCoincide = codificador.matches(usuarioDTO.getPassword(), usuario.getPasswd());
		
		if (!passwordCoincide) throw new PasswordNoCoincideExcepcion();
		
		email = usuario.getEmail();
		username = usuario.getUsername();
		nombre = usuario.getNombre();
		primerApellido = usuario.getPrimerApellido();
		segundoApellido = usuario.getSegundoApellido();
		
		if (usuario instanceof Inquilino) {
			ListaDeseos listaDeseos = ((Inquilino) usuario).getListaDeseos();
			usuarioIniciadoSesion = new InquilinoDTO(username, nombre, primerApellido, segundoApellido, email, listaDeseos);
		} else if (usuario instanceof Propietario) {
			List<Inmueble> inmuebles = ((Propietario) usuario).getInmuebles();
			usuarioIniciadoSesion = new PropietarioDTO(username, nombre, primerApellido, segundoApellido, email, inmuebles);
		}
		log.info("["+LocalDate.now()+"] Usuario logueado: "+usuario);
		return usuarioIniciadoSesion;
	}
	
	public boolean comprobarRolUsuario(UsuarioDTO usuario, Class<?> claseUsuario) {
		boolean resultado = true;
		if (usuario == null || !claseUsuario.isInstance(usuario)) {
			resultado = false;
		}
		return resultado;
	}
}
