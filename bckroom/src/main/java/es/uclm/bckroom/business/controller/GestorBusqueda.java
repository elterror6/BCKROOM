package es.uclm.bckroom.business.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import org.springframework.ui.Model;
import es.uclm.bckroom.business.domain.Inmueble;
import es.uclm.bckroom.persistence.InmuebleDAO;
import jakarta.servlet.http.HttpSession;

@Controller
public class GestorBusqueda {
	
	@Autowired
	private InmuebleDAO inmuebleDAO;
	
	@GetMapping("/search")
	public String searchGet(@RequestParam(required = false) String ciudad,
	        @RequestParam(required = false) Double precioMin,
	        @RequestParam(required = false) Double precioMax,
	        Model model,
	        HttpSession session) {
		
		List<Inmueble> resultados = inmuebleDAO.buscarPorCiudadPrecioMinMax(ciudad, precioMin, precioMax);
		model.addAttribute("resultados", resultados);
		model.addAttribute("ciudad", ciudad);
	    model.addAttribute("precioMin", precioMin);
	    model.addAttribute("precioMax", precioMax);
		
		return "search";
	}
}
