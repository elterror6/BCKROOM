package es.uclm.bckroom.business.controller;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import es.uclm.bckroom.business.domain.Inmueble;
import es.uclm.bckroom.business.domain.Inquilino;
import es.uclm.bckroom.business.domain.Propietario;
import es.uclm.bckroom.business.domain.TipoCalle;
import es.uclm.bckroom.business.domain.Usuario;
import es.uclm.bckroom.business.dto.RegistroUsuarioDTO;
import es.uclm.bckroom.business.services.ServicioUsuario;
import es.uclm.bckroom.persistence.UsuarioDAO;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import es.uclm.bckroom.persistence.PropietarioDAO;
import es.uclm.bckroom.persistence.InquilinoDAO;

@Controller
public class GestorUsuarios implements IGestorUsuarios{
	
	private final ServicioUsuario usuarioServicio;
	
	public GestorUsuarios(ServicioUsuario usuarioServicio) {
		super();
		this.usuarioServicio = usuarioServicio;
	}
	@GetMapping("/register")
	public String getRegisterPage(Model model) {
		model.addAttribute("usuarioRegistroDTO", new RegistroUsuarioDTO());
		return "register";
	}
	@PostMapping("/register")
	public String inquilinoSubmit(@Valid @ModelAttribute RegistroUsuarioDTO usuarioDTO, 
			BindingResult result, RedirectAttributes redirectAttributes) {
		
		if (result.hasErrors()) {
	        return "register";
	    }
		
		try {
			usuarioServicio.registrar(usuarioDTO);
		} catch (RuntimeException e) {
			redirectAttributes.addFlashAttribute("error", e.getMessage());
			return "redirect:/register";
		}
		
		return "login";
	}
	 
	@GetMapping("/login")
	public String loginForm(Model model) {
		model.addAttribute("usuario", new Usuario());
		return "login";
	}
	@PostMapping("/login")
	public String loginSubmit(@ModelAttribute Usuario usuario, Model model, HttpSession session) {
		return "404";
	}
	
	@ModelAttribute("tiposCalle")
	public TipoCalle[] tiposCalle() {
	    return TipoCalle.values();
	}
}
