package es.uclm.bckroom.business.controller;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import es.uclm.bckroom.business.domain.Inmueble;
import es.uclm.bckroom.business.domain.Inquilino;
import es.uclm.bckroom.business.domain.Propietario;
import es.uclm.bckroom.business.domain.TipoCalle;
import es.uclm.bckroom.business.domain.Usuario;
import es.uclm.bckroom.persistence.UsuarioDAO;
import jakarta.servlet.http.HttpSession;
import es.uclm.bckroom.persistence.PropietarioDAO;
import es.uclm.bckroom.persistence.InquilinoDAO;

@Controller
public class GestorUsuarios implements IGestorUsuarios{
	private static final Logger log = LoggerFactory.getLogger(GestorUsuarios.class);
	
	@Autowired
	private UsuarioDAO usuarioDAO;
	@Autowired
	private PropietarioDAO propietarioDAO;
	@Autowired
	private InquilinoDAO inquilinoDAO;
	
	@GetMapping("/register")
	public String registerForm(Model model) {
		model.addAttribute("usuario", new Usuario());
		log.info(usuarioDAO.findAll().toString());
		return "register";
	}
	@PostMapping("/register")
	public String inquilinoSubmit(@ModelAttribute Usuario usuario, @RequestParam String tipo,Model model) {
		model.addAttribute("usuario", usuario);
		if (tipo.equals(inquilino)) {
			Inquilino nuevoInquilino = new Inquilino(usuario);
			log.info("Inquilino creado: "+nuevoInquilino.getUsername());
			inquilinoDAO.save(nuevoInquilino);
		} else {
			Propietario nuevoPropietario = new Propietario(usuario);
			log.info("Propietario creado: "+nuevoPropietario.getUsername());
			propietarioDAO.save(nuevoPropietario);
		}
		return "login";
	}
	
	@GetMapping("/login")
	public String loginForm(Model model) {
		model.addAttribute("usuario", new Usuario());
		return "login";
	}
	@PostMapping("/login")
	public String loginSubmit(@ModelAttribute Usuario usuario, Model model, HttpSession session) {
		Optional<Usuario> usuarioOpt = usuarioDAO.findByUsername(usuario.getUsername());
		
		if(usuarioOpt.isPresent()) {
			Usuario usuarioIniciarSesion = usuarioOpt.get();
			
			if(usuarioIniciarSesion.checkPasswd(usuario.getPasswd())) {
				session.setAttribute("usuarioLogueado", usuarioIniciarSesion);
				if(usuarioIniciarSesion instanceof Inquilino) {
					return "search";
				} else {
					Propietario usuarioPropietario = (Propietario) usuarioIniciarSesion;
					List<Inmueble> inmueblesPropietario = usuarioPropietario.getInmuebles();
					int numeroInmuebles = inmueblesPropietario.size();
					
					if (numeroInmuebles == 0) {
						return "redirect:/propietario/alta-inmueble";
					}
					return "propietario/home";
				}
				
			} else {
				model.addAttribute("error","Contraseña incorrecta");
				return "login";
			}
		} else {
			model.addAttribute("error", "Usuario no encontrado");
			return "login";
		}
	}
	
	@ModelAttribute("tiposCalle")
	public TipoCalle[] tiposCalle() {
	    return TipoCalle.values();
	}
}
