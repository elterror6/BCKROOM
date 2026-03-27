package es.uclm.bckroom.business.controller;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import org.springframework.ui.Model;

import es.uclm.bckroom.business.domain.FiltroDTO;
import es.uclm.bckroom.business.domain.Inmueble;
import es.uclm.bckroom.business.domain.Inquilino;
import es.uclm.bckroom.business.domain.ListaDeseos;
import es.uclm.bckroom.business.domain.Usuario;
import es.uclm.bckroom.persistence.InmuebleDAO;
import es.uclm.bckroom.persistence.InquilinoDAO;
import es.uclm.bckroom.persistence.ListaDeseosDAO;
import jakarta.servlet.http.HttpSession;

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
	                       HttpSession session) {
	    Usuario usuario = (Usuario) session.getAttribute("usuarioLogueado");

	    if (usuario == null) {
	        return "redirect:/login";
	    }

	    Inquilino inquilino = null;
	    if (usuario instanceof Inquilino) {
	        inquilino = (Inquilino) usuario;
	        model.addAttribute("inquilino", inquilino);
	    }

	    model.addAttribute("usuario", usuario);

	    List<Inmueble> resultados = inmuebleDAO.buscarPorCiudadPrecioMinMax(ciudad, precioMin, precioMax);

	    model.addAttribute("resultados", resultados);
	    model.addAttribute("ciudad", ciudad);
	    model.addAttribute("precioMin", precioMin);
	    model.addAttribute("precioMax", precioMax);
	    
	    ListaDeseos listaDeseos = null;
	    Set<Inmueble> deseados = new HashSet<>();

	    if (inquilino != null) {
	        listaDeseos = listaDeseosDAO.findByInquilino(inquilino);
	        if (listaDeseos != null) {
	            deseados = new HashSet<>(listaDeseos.getInmueblesDeseados());
	        }
	    }
	    model.addAttribute("deseados", deseados);

	    return "search";
	}
	@PostMapping("/buscar/filtrar")
	public String filtrar(
	    @RequestParam(required = false) Date fechaInicio,
	    @RequestParam(required = false) Date fechaFin,
	    @RequestBody FiltroDTO filtros,
	    Model model
	) {

	    List<Inmueble> resultados = inmuebleDAO.filtrar(
	        fechaInicio,
	        fechaFin,
	        filtros.getReservaInmediata(),
	        filtros.getComodidades(),
	        filtros.getPoliticas(),
	        filtros.getComodidades() != null ? filtros.getComodidades().size() : 0
	    );

	    model.addAttribute("resultados", resultados);

	    return "fragments/resultados :: resultadosList";
	}
}
	
