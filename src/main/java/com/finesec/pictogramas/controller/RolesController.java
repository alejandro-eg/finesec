package com.finesec.pictogramas.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.finesec.pictogramas.model.Roles;
import com.finesec.pictogramas.model.Usuarios;
import com.finesec.pictogramas.repository.IUsuariosRepository;
import com.finesec.pictogramas.service.IRolService;
import com.finesec.pictogramas.service.IUsuarioService;

@Controller
public class RolesController {
	@Autowired
	private IRolService servicioRoles;
	private Roles nuevoRol;
	
	@Autowired
	private IUsuarioService servicioUsuarios;
	
	@Autowired
	private IUsuariosRepository repo;
	
	
	private Boolean editMode = false;

	/*
	@GetMapping("/roles") // url
	public String listarRoles(Model model) { // metodo de ejecucion al leer la url
		List<Roles> datosRolesDB = servicioRoles.ListarRoles();
		model.addAttribute("ListaR", datosRolesDB);
		return "/roles/listaroles"; // ruta fisica de la pagina web
	}*/
	
	
	@GetMapping("/roles")
	public String listarRoles(Model model) {
		
	    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
	    String email = authentication.getName(); //Obtengo el nombre de usuario (en el security confing se conoce como Name)
	    Usuarios usuario = servicioUsuarios.findByEmail(email);
		 
	    if (usuario != null && !usuario.getRestriccionRol()) {
	        // Si restriccionRol es true, redirige a la página de inicio ("/")
	        return "redirect:/";
	    } else {
	        // Si restriccionRol es false, muestra la página de roles ("/roles")
	        List<Roles> datosRolesDB = servicioRoles.ListarRoles();
	        model.addAttribute("ListaR", datosRolesDB);
	        return "/roles/listaroles";
	    }
	}
	

	@GetMapping("/nuevorol") // url
	public String insertarRoles(Model model) {// metodo de ejecucion al leer la url
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
	    String email = authentication.getName(); //Obtengo el nombre de usuario (en el security confing se conoce como Name)
	    Usuarios usuario = servicioUsuarios.findByEmail(email);
	    
	    if (usuario != null && !usuario.getRestriccionRol()) {
	        // Si restriccionRol es true, redirige a la página de inicio ("/")
	        return "redirect:/";
	    } else {
	        // Si restriccionRol es false, muestra la página de roles ("/roles")
	    	nuevoRol = new Roles();
			model.addAttribute("nuevo", nuevoRol);// método de ejecución al leer la url
			return "/roles/nuevorol"; // ruta fisica de la pagina web
	    }
	}

	@PostMapping("/guardarrol")
	public String guardarRol(@ModelAttribute("nuevo") Roles nuevoRoles, BindingResult result, Model model) {
		// Verificar si el rol ya existe por nombre
		Roles rolExistente = servicioRoles.findByNombre(nuevoRoles.getNombre());

		if (rolExistente != null) {

			if (!editMode) {
				// si el rol existe mostrara una alerta
				model.addAttribute("alerta", "El rol ya existe. Por favor, elige otro nombre.");
				editMode = false;
				model.addAttribute("editMode", editMode);
				return "/roles/nuevorol";
			}

		}

		// Guardar el rol si no existe
		servicioRoles.insertarRol(nuevoRoles);
		 // Reinicia editMode
	    editMode = false;
	    model.addAttribute("editMode", editMode);
		return "redirect:/roles";
	}

	@GetMapping("/editarrol/{idRol}")
	public String editarRol(@PathVariable("idRol") int idRol, Model model) {
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
	    String email = authentication.getName(); //Obtengo el nombre de usuario (en el security confing se conoce como Name)
	    Usuarios usuario = servicioUsuarios.findByEmail(email);
	    
	    if (usuario != null && !usuario.getRestriccionRol()) {
	        // Si restriccionRol es true, redirige a la página de inicio ("/")
	        return "redirect:/";
	    } else {
	        // Si restriccionRol es false, muestra la página de roles ("/roles")
	    	Roles recuperadoDB = servicioRoles.findByIdRol(idRol);
			model.addAttribute("nuevo", recuperadoDB);
			editMode = true;
	        model.addAttribute("editMode", editMode);
			return "/roles/nuevorol";
	    }	
	}

	@GetMapping("/eliminarRol/{idRol}")
	public String eliminarRol(@PathVariable(value = "idRol") int idRol) {
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
	    String email = authentication.getName(); //Obtengo el nombre de usuario (en el security confing se conoce como Name)
	    Usuarios usuario = servicioUsuarios.findByEmail(email);
	    
	    if (usuario != null && !usuario.getRestriccionRol()) {
	        // Si restriccionRol es true, redirige a la página de inicio ("/")
	        return "redirect:/";
	    } else {
	        // Si restriccionRol es false, muestra la página de roles ("/roles")
	    	servicioRoles.eliminarRol(idRol);
			return "redirect:/roles";
	    }	
	}

}
