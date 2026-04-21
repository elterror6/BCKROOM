package es.uclm.bckroom.business.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import es.uclm.bckroom.business.domain.Disponibilidad;
import es.uclm.bckroom.business.domain.Inmueble;
import es.uclm.bckroom.business.domain.Propietario;
import es.uclm.bckroom.business.domain.TipoCalle;
import es.uclm.bckroom.business.domain.Usuario;
import es.uclm.bckroom.business.dto.InmuebleDTO;
import es.uclm.bckroom.business.dto.PropietarioDTO;
import es.uclm.bckroom.business.dto.UsuarioDTO;
import es.uclm.bckroom.business.services.ServicioInmueble;
import es.uclm.bckroom.business.services.ServicioUsuario;
import es.uclm.bckroom.persistence.DisponibilidadDAO;
import es.uclm.bckroom.persistence.InmuebleDAO;
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
	@PostMapping("/propietario/inmuebles")
	public String postBajaInmueble() {
		return "propietario/inmuebles";
	}
	
	@ModelAttribute("tiposCalle")
	public TipoCalle[] tiposCalle() {
	    return TipoCalle.values();
	}
	
	@GetMapping("/inmueble/{id}/add-availability")
	public String getAddAvailability(@PathVariable Long id, Model model) {

	    Inmueble inmueble = inmuebleDAO.findById(id).orElse(null);

	    model.addAttribute("inmueble", inmueble);

	    return "add-availability";
	}
	@PostMapping("/inmueble/{id}/add-availability")
	public String postAddAvailability(@PathVariable Long id, @ModelAttribute Disponibilidad disponibilidad, HttpSession session ) {
		Usuario usuario = (Usuario) session.getAttribute("usuarioLogueado");

	    if (usuario == null || !(usuario instanceof Propietario)) {
	        return "redirect:/login";
	    }

	    Inmueble inmueble = inmuebleDAO.findById(id).orElse(null);

	    disponibilidad.setInmueble(inmueble);

	    disponibilidadDAO.save(disponibilidad);

	    return "redirect:/propietario/home";
	}
	@GetMapping("/inmueble/{id}/check-availability")
	public String checkDisponibilidadesFromInmueble(@PathVariable Long id, Model model, HttpSession session) {
		Usuario usuario = (Usuario) session.getAttribute("usuarioLogueado");

	    if (usuario == null || !(usuario instanceof Propietario)) {
	        return "redirect:/login";
	    }
	    
	    Inmueble inmueble = inmuebleDAO.findById(id).orElse(null);
	    
	    Set<Disponibilidad> disponibilidades = inmueble.getDisponibilidades();
	    
	    model.addAttribute("disponibilidades",disponibilidades);
	    
	    
		return "/inmueble/"+id+"/check-availability";
	}
	
}
