package es.uclm.bckroom.business.controller;

import java.security.Principal;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import org.springframework.ui.Model;
import es.uclm.bckroom.business.domain.Inmueble;
import es.uclm.bckroom.business.domain.Inquilino;
import es.uclm.bckroom.business.domain.ListaDeseos;
import es.uclm.bckroom.business.domain.Usuario;
import es.uclm.bckroom.persistence.InmuebleDAO;
import es.uclm.bckroom.persistence.InquilinoDAO;
import es.uclm.bckroom.persistence.ListaDeseosDAO;

@Controller
public class GestorBusqueda {
	
	@Autowired
	private InmuebleDAO inmuebleDAO;
	@Autowired
	private ListaDeseosDAO listaDeseosDAO;
	@Autowired
	private InquilinoDAO inquilinoDAO;
	
	@GetMapping("/search")
	public String searchGet(@RequestParam(required = false) String ciudad,
	        @RequestParam(required = false) Double precioMin,
	        @RequestParam(required = false) Double precioMax,
	        Model model,
	        Principal principal) {
		Inquilino inquilino = inquilinoDAO.findByUsername(principal.getName());
		model.addAttribute("usuario", inquilino);
		List<Inmueble> resultados = inmuebleDAO.buscarPorCiudadPrecioMinMax(ciudad, precioMin, precioMax);
	
		model.addAttribute("resultados", resultados);
		model.addAttribute("ciudad", ciudad);
	    model.addAttribute("precioMin", precioMin);
	    model.addAttribute("precioMax", precioMax);
	    
	    Set<Inmueble> deseados = new HashSet<>();

        if (inquilino != null && inquilino.getListaDeseos() != null) {
            deseados = inquilino.getListaDeseos().getInmueblesDeseados();
        }

	    model.addAttribute("deseados", deseados);
		
		return "search";
	}
}
