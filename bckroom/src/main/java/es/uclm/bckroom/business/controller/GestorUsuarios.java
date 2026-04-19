package es.uclm.bckroom.business.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import es.uclm.bckroom.business.domain.Inmueble;
import es.uclm.bckroom.business.domain.TipoCalle;
import es.uclm.bckroom.business.dto.InicioSesionUsuarioDTO;
import es.uclm.bckroom.business.dto.InquilinoDTO;
import es.uclm.bckroom.business.dto.PropietarioDTO;
import es.uclm.bckroom.business.dto.RegistroUsuarioDTO;
import es.uclm.bckroom.business.dto.UsuarioDTO;
import es.uclm.bckroom.business.services.ServicioUsuario;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

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
	public String getLogin(Model model) {
		model.addAttribute("InicioSesionUsuario", new InicioSesionUsuarioDTO());
		return "login";
	}
	@PostMapping("/login")
	public String postLogin(@Valid @ModelAttribute InicioSesionUsuarioDTO usuario, 
			BindingResult result, HttpSession session, RedirectAttributes redirectAttributes) {
			UsuarioDTO datosUsuarioConSesionIniciada;
		
		if (result.hasErrors()) {
	        return "login";
	    }
		
		try {
			datosUsuarioConSesionIniciada = usuarioServicio.inicioSesion(usuario);
		} catch (RuntimeException e) {
			redirectAttributes.addFlashAttribute("error", e.getMessage());
			return "redirect:/login";
		}
		session.setAttribute("usuarioLogueado", datosUsuarioConSesionIniciada);
		if (datosUsuarioConSesionIniciada instanceof InquilinoDTO) {
			return "search";
		} else if (datosUsuarioConSesionIniciada instanceof PropietarioDTO) {
			List<Inmueble> inmueblesDelPropietario = ((PropietarioDTO) datosUsuarioConSesionIniciada).getInmuebles();
			if (inmueblesDelPropietario.size() == 0) {
				return "propietario/alta-inmueble";
			}
			return "propietario/home";
		}
		return "404";
	}
	
	@ModelAttribute("tiposCalle")
	public TipoCalle[] tiposCalle() {
	    return TipoCalle.values();
	}
}
