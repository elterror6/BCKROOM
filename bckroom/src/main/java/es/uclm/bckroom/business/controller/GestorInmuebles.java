package es.uclm.bckroom.business.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import es.uclm.bckroom.business.domain.Comodidad;
import es.uclm.bckroom.business.domain.Disponibilidad;
import es.uclm.bckroom.business.domain.Inmueble;
import es.uclm.bckroom.business.domain.PoliticaCancelacion;
import es.uclm.bckroom.business.domain.Propietario;
import es.uclm.bckroom.business.domain.TipoCalle;
import es.uclm.bckroom.business.domain.Usuario;
import es.uclm.bckroom.persistence.ComodidadDAO;
import es.uclm.bckroom.persistence.DisponibilidadDAO;
import es.uclm.bckroom.persistence.InmuebleDAO;
import jakarta.servlet.http.HttpSession;

@Controller
public class GestorInmuebles implements IGestorInmuebles{
	@Autowired
	private InmuebleDAO inmuebleDAO;
	@Autowired
	private DisponibilidadDAO disponibilidadDAO;
	@Autowired
	private ComodidadDAO comodidadDAO;

	@GetMapping("/propietario/alta-inmueble")
	public String darAltaInmuebleForm(Model model, HttpSession session) {
		 Usuario usuario = (Usuario) session.getAttribute("usuarioLogueado");
		 
	    if(usuario == null) {
	        return "redirect:/login";
	    }

	    if(!(usuario instanceof Propietario)) {
	        return "redirect:/search";
	    }
	    
	    Inmueble inmueble = new Inmueble();
	    
		model.addAttribute("inmueble", inmueble);
		model.addAttribute("tipoCalle",TipoCalle.values());
		model.addAttribute("comodidades", comodidadDAO.findAll());
		
		return "propietario/alta-inmueble";
	}
	@PostMapping("/propietario/alta-inmueble")
	public String darAltaInmuebleSubmit(
	        @ModelAttribute("inmueble") Inmueble inmueble,
	        @RequestParam(value = "comodidadesSeleccionadas", required = false) List<Long> comodidadesIds,
	        HttpSession session,
	        Model model) {

	    Usuario usuario = (Usuario) session.getAttribute("usuarioLogueado");

	    if (!(usuario instanceof Propietario propietario)) {
	        return "redirect:/login";
	    }

	    inmueble.setPropietario(propietario);
	    if (comodidadesIds != null) {
	        Set<Comodidad> comodidades = new HashSet<>(
	            comodidadDAO.findAllById(comodidadesIds)
	        );
	        inmueble.setComodidades(comodidades);
	    }

	    inmuebleDAO.save(inmueble);

	    return "/propietario/alta-inmueble-success";
	}
	@ModelAttribute("tiposCalle")
	public TipoCalle[] tiposCalle() {
	    return TipoCalle.values();
	}
	
	@GetMapping("/propietario/home")
	public String getHome(Model model, HttpSession session) {

	    Propietario propietario = (Propietario) session.getAttribute("usuario");

	    if (propietario == null) {
	        return "redirect:/login";
	    }

	    List<Inmueble> todos =
	        inmuebleDAO.findByPropietarioId(propietario.getId());

	    Date hoy = new Date();

	    List<Inmueble> disponibles = new ArrayList<>();
	    List<Inmueble> noDisponibles = new ArrayList<>();

	    for (Inmueble i : todos) {
	        if (i.disponible(hoy, hoy)) {
	            disponibles.add(i);
	        } else {
	            noDisponibles.add(i);
	        }
	    }

	    model.addAttribute("disponibles", disponibles);
	    model.addAttribute("noDisponibles", noDisponibles);

	    return "/propietario/home";
	}
	
	@PostMapping("/propietario/home")
	public String postHome(@RequestParam Long id_inmueble, HttpSession session, Model model, @RequestParam String accion) {
		Usuario usuario = (Usuario) session.getAttribute("usuarioLogueado");

	    if (usuario == null || !(usuario instanceof Propietario)) {
	        return "redirect:/login";
	    }
	    
	    if (accion.equals(add)) {
	    	return "redirect:/inmueble/"+id_inmueble+"/add-availability";
	    } else if (accion.equals(check)){
	    	return "redirect:/inmueble/"+id_inmueble+"/check-availability";
	    }
	    
	    return "/propietario/home";
	}
	
	@GetMapping("/inmueble/{id}/add-availability")
	public String getAddAvailability(@PathVariable Long id, Model model) {

	    Inmueble inmueble = inmuebleDAO.findById(id).orElse(null);
	    
	    Disponibilidad disponibilidad = new Disponibilidad();
	    
	    model.addAttribute("inmueble", inmueble);
	    model.addAttribute("disponibilidad", disponibilidad);
	    model.addAttribute("cancelaciones", PoliticaCancelacion.values());

	    return "add-availability";
	}
	@PostMapping("/inmueble/{id}/add-availability")
	public String postAddAvailability(
			@PathVariable Long id, 
			@ModelAttribute Disponibilidad disponibilidad,
			@RequestParam("accion") String accion,
			HttpSession session ) {
		Usuario usuario = (Usuario) session.getAttribute("usuarioLogueado");

		if (!(usuario instanceof Propietario)) {
		    return "redirect:/login";
		}

		if ("cancelar".equals(accion)) {
		    return "redirect:/propietario/home";
		}

		Inmueble inmueble = inmuebleDAO.findById(id).orElse(null);
		disponibilidad.setInmueble(inmueble);

		disponibilidadDAO.save(disponibilidad);

		if ("add".equals(accion)) {
		    return "redirect:/inmueble/" + id + "/add-availability";
		}

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
