package es.uclm.bckroom.business.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import es.uclm.bckroom.business.domain.Inmueble;
import es.uclm.bckroom.business.domain.Propietario;
import es.uclm.bckroom.business.domain.TipoCalle;
import es.uclm.bckroom.business.domain.Usuario;
import es.uclm.bckroom.persistence.InmuebleDAO;
import jakarta.servlet.http.HttpSession;

@Controller
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
	    
	    Inmueble inmueble = new Inmueble();
	    
		model.addAttribute("inmueble", inmueble);
		return "propietario/alta-inmueble";
	}
	@PostMapping("/propietario/alta-inmueble")
	public String darAltaInmuebleSubmit(@ModelAttribute("inmueble") Inmueble inmueble, HttpSession session, Model model) {
		 Usuario usuario = (Usuario) session.getAttribute("usuarioLogueado");
		 
	    if(!(usuario instanceof Propietario propietario)) {
	        return "redirect:/login";
	    }
	    
	    model.addAttribute("inmueble", inmueble);
	    inmueble.setPropietario(propietario);
	    inmuebleDAO.save(inmueble);
	    
	    return "/propietario/alta-inmueble-success";
	}
	@ModelAttribute("tiposCalle")
	public TipoCalle[] tiposCalle() {
	    return TipoCalle.values();
	}
}
