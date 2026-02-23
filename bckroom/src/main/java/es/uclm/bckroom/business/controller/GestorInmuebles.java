package es.uclm.bckroom.business.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import es.uclm.bckroom.business.domain.Inmueble;
import es.uclm.bckroom.business.domain.Propietario;
import es.uclm.bckroom.business.domain.Usuario;
import es.uclm.bckroom.persistence.InmuebleDAO;
import jakarta.servlet.http.HttpSession;

public class GestorInmuebles {
	@Autowired
	private InmuebleDAO inmuebleDAO;

	@GetMapping("/propietario/alta-inmueble")
	public String darAltaInmuebleForm(Model model, HttpSession session) {
		 Usuario usuario = (Usuario) session.getAttribute("usuarioLogueado");
		 
	    if(usuario == null) {
	        return "redirect:/login";
	    }

	    if(!(usuario instanceof Propietario)) {
	        return "redirect:/home";
	    }
	    
		model.addAttribute("inmueble", new Inmueble());
		return "propietario/alta-inmueble";
	}
	@PostMapping("/propietario/alta-inmueble")
	public String darAltaInmuebleSubmit(@ModelAttribute Inmueble inmueble, HttpSession session) {
		 Usuario usuario = (Usuario) session.getAttribute("usuarioLogueado");
		 
	    if(!(usuario instanceof Propietario propietario)) {
	        return "redirect:/login";
	    }
	    
	    inmueble.setPropietario(propietario);
	    inmuebleDAO.save(inmueble);
	    
	    return "/propietario/alta-inmueble-success";
	}
}
