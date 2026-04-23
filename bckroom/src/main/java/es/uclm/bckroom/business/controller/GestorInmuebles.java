package es.uclm.bckroom.business.controller;


import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import es.uclm.bckroom.business.domain.Disponibilidad;
import es.uclm.bckroom.business.domain.TipoCalle;
import es.uclm.bckroom.business.domain.TipoInmueble;
import es.uclm.bckroom.business.dto.DisponibilidadDTO;
import es.uclm.bckroom.business.dto.InmuebleDTO;
import es.uclm.bckroom.business.dto.PropietarioDTO;
import es.uclm.bckroom.business.dto.UsuarioDTO;
import es.uclm.bckroom.business.services.ServicioInmueble;
import es.uclm.bckroom.business.services.ServicioUsuario;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
public class GestorInmuebles implements IGestorInmuebles{
	private final ServicioInmueble inmuebleServicio;
	private final ServicioUsuario usuarioServicio;
	
	public GestorInmuebles(ServicioInmueble inmuebleServicio, ServicioUsuario usuarioServicio) {
		super();
		this.inmuebleServicio = inmuebleServicio;
		this.usuarioServicio = usuarioServicio;
	}
	
	@GetMapping("/propietario/alta-inmueble")
	public String getAltaInmueble(Model model, HttpSession session) {
		UsuarioDTO usuarioPropietario = (UsuarioDTO) session.getAttribute("usuarioLogueado");
		
		if (!usuarioServicio.comprobarRolUsuario(usuarioPropietario, PropietarioDTO.class)) return "redirect:/login"; 
			
		model.addAttribute("nuevoInmueble", new InmuebleDTO((PropietarioDTO)usuarioPropietario));
		model.addAttribute("tiposInmueble", TipoInmueble.values());
		return "propietario/alta-inmueble";
	}
	@PostMapping("/propietario/alta-inmueble")
	public String postAltaInmueble(@Valid @ModelAttribute InmuebleDTO inmueble, HttpSession session,
				BindingResult result, RedirectAttributes redirectAttributes) {
		UsuarioDTO usuarioPropietario = (UsuarioDTO) session.getAttribute("usuarioLogueado");
		
		if (!usuarioServicio.comprobarRolUsuario(usuarioPropietario, PropietarioDTO.class)) return "redirect:/login";
		
		if (result.hasErrors()) {
	        return "propietario/alta-inmueble";
	    }
		
		inmuebleServicio.altaInmueble(inmueble, (PropietarioDTO)usuarioPropietario);
		return "propietario/home";
	}
	@GetMapping("/propietario/inmuebles")
	public String getInmuebles(Model model, HttpSession session) {
		UsuarioDTO usuarioPropietario = (UsuarioDTO) session.getAttribute("usuarioLogueado");
		List<InmuebleDTO> inmueblesPropietario;
		
		if (!usuarioServicio.comprobarRolUsuario(usuarioPropietario, PropietarioDTO.class)) return "redirect:/login";
		
		inmueblesPropietario = inmuebleServicio.getInmueblesPropietario((PropietarioDTO)usuarioPropietario);
		
		model.addAttribute("inmuebles", inmueblesPropietario);
		
		return "propietario/inmuebles";
	}
	@PostMapping("/propietario/baja-inmueble")
	public String postBajaInmueble(@ModelAttribute Long idInmueble, HttpSession session) {
		UsuarioDTO usuarioPropietario = (UsuarioDTO) session.getAttribute("usuarioLogueado");
		
		if (!usuarioServicio.comprobarRolUsuario(usuarioPropietario, PropietarioDTO.class)) return "redirect:/login";
		
		inmuebleServicio.bajaInmueble(idInmueble);
		
		return "propietario/inmuebles";
	}
	
	@ModelAttribute("tiposCalle")
	public TipoCalle[] tiposCalle() {
	    return TipoCalle.values();
	}
	@GetMapping("/propietario/inmueble/{id}/disponibilidad")
	public String getDisponibilidades(@PathVariable Long inmuebleId, HttpSession session, Model model) {
		UsuarioDTO usuarioPropietario = (UsuarioDTO) session.getAttribute("usuarioLogueado");
		
		if (!usuarioServicio.comprobarRolUsuario(usuarioPropietario, PropietarioDTO.class)) return "redirect:/login";
		
		Set<DisponibilidadDTO> disponibilidades = inmuebleServicio.getDisponibilidades(inmuebleId);
		model.addAttribute("disponibilidadesInmueble",disponibilidades);
		
		return "propietario/inmueble/{id}/disponibilidad";
	}
	@GetMapping("/propietario/inmueble/{id}/disponibilidad/añadir-disponibilidad")
	public String getAniadirDisponibilidad(@PathVariable Long inmuebleId, HttpSession session, Model model) {
		UsuarioDTO usuarioPropietario = (UsuarioDTO) session.getAttribute("usuarioLogueado");
		
		if (!usuarioServicio.comprobarRolUsuario(usuarioPropietario, PropietarioDTO.class)) return "redirect:/login";
		
		model.addAttribute("nuevaDisponibilidad", new DisponibilidadDTO(inmuebleId));
		return null;
	}
	@PostMapping("/propietario/inmueble/{id}/disponibilidad/añadir-disponibilidad")
	public String postAniadirDisponibilidad(@PathVariable Long inmuebleId, HttpSession session,
			@Valid @ModelAttribute DisponibilidadDTO disponibilidadDTO, BindingResult result) {
		UsuarioDTO usuarioPropietario = (UsuarioDTO) session.getAttribute("usuarioLogueado");
		
		if (!usuarioServicio.comprobarRolUsuario(usuarioPropietario, PropietarioDTO.class)) return "redirect:/login";
		
		if (result.hasErrors()) {
	        return "propietario/inmueble/{id}/disponibilidad/añadir-disponibilidad";
	    }
		
		inmuebleServicio.setNuevaDisponibilidad(disponibilidadDTO);
		
	    return "redirect:/propietario/inmueble/{id}/disponibilidad";
	}
	
}
