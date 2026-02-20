package es.uclm.bckroom.business.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import es.uclm.bckroom.business.domain.Usuario;
import es.uclm.bckroom.persistence.UsuarioDAO;

@Controller
public class GestorUsuarios {
	private static final Logger log = LoggerFactory.getLogger(GestorUsuarios.class);
	
	@Autowired
	private UsuarioDAO usuarioDAO;
	
	@GetMapping("/register")
	public String registerForm(Model model) {
		model.addAttribute("register", new Usuario());
		log.info(usuarioDAO.findAll().toString());
		return "register";
	}
}
