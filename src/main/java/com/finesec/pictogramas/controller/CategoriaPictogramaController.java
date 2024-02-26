package com.finesec.pictogramas.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.finesec.pictogramas.model.CategoriaPictograma;
import com.finesec.pictogramas.model.Pictogramas;
import com.finesec.pictogramas.model.Usuarios;
import com.finesec.pictogramas.service.ICategoriaPictogramaService;
import com.finesec.pictogramas.service.IUsuarioService;

@Controller
public class CategoriaPictogramaController {
	@Autowired
	private ICategoriaPictogramaService servicioCategoriaPictograma;
	private CategoriaPictograma nuevaCategoriaPictograma;
	private Boolean editMode = false;
	
	@Autowired
	private IUsuarioService servicioUsuarios;

	@GetMapping("/categoriapictograma")
	public String listarCategoriaPictograma(Model model) {
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String email = authentication.getName(); //Obtengo el nombre de usuario (en el security confing se conoce como Name)
		Usuarios usuario = servicioUsuarios.findByEmail(email);
		
		if (usuario != null && !usuario.getRestriccionCategoria()) {
	        // Si restriccionUsuario es true, redirige a la página de inicio ("/")
	        return "redirect:/";
	    } else {
	    	List<CategoriaPictograma> datosCategoriaPictogramasDB = servicioCategoriaPictograma.ListarCategoriaPigtograma();
			model.addAttribute("ListaCP", datosCategoriaPictogramasDB);
			return "/categoriapictogramas/listarcategoriapictograma";
	    }
	
	}

	@GetMapping("/nuevacategoriapictograma")
	public String insertarCategoriaPictograma(Model model) {
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String email = authentication.getName(); //Obtengo el nombre de usuario (en el security confing se conoce como Name)
		Usuarios usuario = servicioUsuarios.findByEmail(email);
		
		if (usuario != null && !usuario.getRestriccionCategoria()) {
	        // Si restriccionUsuario es true, redirige a la página de inicio ("/")
	        return "redirect:/";
	    } else {
	    	nuevaCategoriaPictograma = new CategoriaPictograma();
			model.addAttribute("nuevo", nuevaCategoriaPictograma);
			return "/categoriapictogramas/nuevacategoriapictograma";
	    }	
	}
	

	@PostMapping("/guardarcategoriapictograma")
	public String guardarCategoriaPictograma(@ModelAttribute("nuevo") CategoriaPictograma nuevaCategoriaPictograma, Model model) {
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String email = authentication.getName(); //Obtengo el nombre de usuario (en el security confing se conoce como Name)
		Usuarios usuario = servicioUsuarios.findByEmail(email);
		
		if (usuario != null && !usuario.getRestriccionCategoria()) {
	        // Si restriccionUsuario es true, redirige a la página de inicio ("/")
	        return "redirect:/";
	    } else {
	    	CategoriaPictograma nombreExistente = servicioCategoriaPictograma
					.findByNombre(nuevaCategoriaPictograma.getNombre());
			CategoriaPictograma keywordExistente = servicioCategoriaPictograma
					.findByNombreIngles(nuevaCategoriaPictograma.getNombreIngles());
			if (nombreExistente != null) {

				if (!editMode) {
					// si la categoria existe mostrara una alerta
					model.addAttribute("alerta", "La categoria ya existe. Por favor, elige otro nombre.");
					editMode = false;
					model.addAttribute("editMode", editMode);
					return "/categoriapictogramas/nuevacategoriapictograma";
				}
			} else if (keywordExistente != null) {
				if (!editMode) {
					model.addAttribute("alerta2", "La categoria ya existe. Por favor, elige otro keyword.");
					editMode = false;
					model.addAttribute("editMode", editMode);
					return "/categoriapictogramas/nuevacategoriapictograma";
				}

			}
			servicioCategoriaPictograma.insertarCategoriaPictograma(nuevaCategoriaPictograma);
			// Reinicia editMode
		    editMode = false;
		    model.addAttribute("editMode", editMode);
			return "redirect:/categoriapictograma";
	    }	
		
		
	}

	@GetMapping("/editarcategoriapictograma/{idCategoriaPictograma}")
	public String editarCategoriaPictograma(@PathVariable("idCategoriaPictograma") int idCategoriaPictograma, Model model) {
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String email = authentication.getName(); //Obtengo el nombre de usuario (en el security confing se conoce como Name)
		Usuarios usuario = servicioUsuarios.findByEmail(email);
		
		if (usuario != null && !usuario.getRestriccionCategoria()) {
	        // Si restriccionUsuario es true, redirige a la página de inicio ("/")
	        return "redirect:/";
	    } else {
	    	CategoriaPictograma recuperadoDB = servicioCategoriaPictograma
					.findByIdCategoriaPictograma(idCategoriaPictograma);
			model.addAttribute("nuevo", recuperadoDB);
			return "/categoriapictogramas/nuevacategoriapictograma";
	    }		
	}

	@GetMapping("/eliminarcategoriapictograma/{idCategoriaPictograma}")
	public String eliminarCategoriaPictograma(@PathVariable(value = "idCategoriaPictograma") int idCategoriaPictograma) {
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String email = authentication.getName(); //Obtengo el nombre de usuario (en el security confing se conoce como Name)
		Usuarios usuario = servicioUsuarios.findByEmail(email);
		
		if (usuario != null && !usuario.getRestriccionCategoria()) {
	        // Si restriccionUsuario es true, redirige a la página de inicio ("/")
	        return "redirect:/";
	    } else {
	    	servicioCategoriaPictograma.eliminarCategoriaPictograma(idCategoriaPictograma);
			return "redirect:/categoriapictograma";
	    }
		
	}

}
