package es.uclm.bckroom.business.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import es.uclm.bckroom.persistence.InmuebleDAO;
import es.uclm.bckroom.persistence.ListaDeseosDAO;
import es.uclm.bckroom.persistence.UsuarioDAO;
import es.uclm.bckroom.business.domain.Inmueble;
import es.uclm.bckroom.business.domain.ListaDeseos;
import es.uclm.bckroom.business.domain.Usuario;
import jakarta.servlet.http.HttpSession;
import java.util.Optional;

public class GestorListaDeseos {
	@Autowired
	private ListaDeseosDAO listaDeseosDAO;
	@Autowired
	private UsuarioDAO usuarioDAO;
	@Autowired
	private InmuebleDAO inmuebleDAO;
	
	@PostMapping("/deseados/add")
	public String addDeseado(@RequestParam Long inmuebleId, HttpSession session) {

		Usuario usuario = (Usuario) session.getAttribute("usuario");

	    ListaDeseos lista = listaDeseosDAO.findByUsuario(usuario);
	    Inmueble inmueble = inmuebleDAO.findById(inmuebleId).orElse(null);

	    lista.getInmueblesDeseados().add(inmueble);

	    listaDeseosDAO.save(lista);

	    return "redirect:/busqueda";
	}
}
