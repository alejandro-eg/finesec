package com.finesec.pictogramas.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.finesec.pictogramas.model.Roles;
import com.finesec.pictogramas.model.Usuarios;
import com.finesec.pictogramas.service.IRolService;

@Controller
public class RolesController {
	@Autowired
	private IRolService servicioRoles;
	private Roles nuevoRol;
	@GetMapping("/roles") //url
	public String listarRoles(Model model) { //metodo de ejecucion al leer la url
		List<Roles> datosRolesDB= servicioRoles.ListarRoles();
		model.addAttribute("ListaR", datosRolesDB);
		return "/roles/listaroles"; //ruta fisica de la pagina web
	}
	
	@GetMapping("/nuevorol") //url
	public String insertarRoles(Model model) {//metodo de ejecucion al leer la url
		nuevoRol = new Roles();
		model.addAttribute("nuevo", nuevoRol);//método de ejecución al leer la url
		return "/roles/nuevorol"; //ruta fisica de la pagina web
	}
	
	@PostMapping("/guardarrol")
	public String guardarRol(@ModelAttribute("nuevo")Roles nuevoRoles) {
		servicioRoles.insertarRol(nuevoRoles);
		return "redirect:/roles";
	}
	
	@GetMapping("/editarrol/{idRol}")
	public String editarRol(@PathVariable("idRol") int idRol, Model model) {
	    Roles recuperadoDB = servicioRoles.findByIdRol(idRol);
	    model.addAttribute("nuevo", recuperadoDB);
	    return "/roles/nuevorol";
	}
	
	@GetMapping("/eliminarRol/{idRol}")
	public String eliminarRol(@PathVariable(value="idRol")int idRol) {
		servicioRoles.eliminarRol(idRol);
		return "redirect:/roles";
	}
	
}
