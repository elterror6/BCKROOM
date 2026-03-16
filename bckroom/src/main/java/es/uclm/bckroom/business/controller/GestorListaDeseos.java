package es.uclm.bckroom.business.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import es.uclm.bckroom.persistence.InmuebleDAO;
import es.uclm.bckroom.persistence.InquilinoDAO;
import es.uclm.bckroom.persistence.ListaDeseosDAO;
import es.uclm.bckroom.persistence.UsuarioDAO;
import es.uclm.bckroom.business.domain.Inmueble;
import es.uclm.bckroom.business.domain.Inquilino;
import es.uclm.bckroom.business.domain.ListaDeseos;
import es.uclm.bckroom.business.domain.Usuario;
import jakarta.servlet.http.HttpSession;

import java.security.Principal;
import java.util.Optional;

@Controller
public class GestorListaDeseos {
	@Autowired
	private ListaDeseosDAO listaDeseosDAO;
	@Autowired
	private InquilinoDAO inquilinoDAO;
	@Autowired
	private InmuebleDAO inmuebleDAO;
	
	@PostMapping("/deseados/toggle/{id}")
	public String toggleDeseado(@PathVariable Long id, Principal principal) {
		if (principal == null) {
	        return "redirect:/login";
	    }
	    String username = principal.getName();

	    Inquilino inquilino = inquilinoDAO.findByUsername(username);
	    Inmueble inmueble = inmuebleDAO.findById(id).orElseThrow();
	    
	    
	    ListaDeseos lista = inquilino.getListaDeseos();

	    if(lista.getInmueblesDeseados().contains(inmueble)){
	        lista.delInmueble(inmueble);
	    } else {
	        lista.addInmueble(inmueble);
	    }

	    listaDeseosDAO.save(lista);

	    return "redirect:/search";
	}
}
