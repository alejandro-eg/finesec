package com.finesec.pictogramas.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.finesec.pictogramas.model.Roles;
import com.finesec.pictogramas.service.IRolService;

@Controller
public class RolesController {
	@Autowired
	private IRolService servicioRoles;
	private Roles nuevoRol;
	private Boolean editMode = false;

	@GetMapping("/roles") // url
	public String listarRoles(Model model) { // metodo de ejecucion al leer la url
		List<Roles> datosRolesDB = servicioRoles.ListarRoles();
		model.addAttribute("ListaR", datosRolesDB);
		return "/roles/listaroles"; // ruta fisica de la pagina web
	}

	@GetMapping("/nuevorol") // url
	public String insertarRoles(Model model) {// metodo de ejecucion al leer la url
		nuevoRol = new Roles();
		model.addAttribute("nuevo", nuevoRol);// método de ejecución al leer la url
		return "/roles/nuevorol"; // ruta fisica de la pagina web
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
		Roles recuperadoDB = servicioRoles.findByIdRol(idRol);
		model.addAttribute("nuevo", recuperadoDB);
		editMode = true;
        model.addAttribute("editMode", editMode);
		return "/roles/nuevorol";
	}

	@GetMapping("/eliminarRol/{idRol}")
	public String eliminarRol(@PathVariable(value = "idRol") int idRol) {
		servicioRoles.eliminarRol(idRol);
		return "redirect:/roles";
	}

}
