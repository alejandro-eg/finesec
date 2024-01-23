package com.finesec.pictogramas.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.finesec.pictogramas.model.Roles;
import com.finesec.pictogramas.service.IRolService;

@Controller
public class RolesController {
	@Autowired
	private IRolService servicioRoles;
	@GetMapping("/roles") //url
	public String listarRoles(Model model) { //metodo de ejecucion al leer la url
		List<Roles> datosRolesDB= servicioRoles.ListarRoles();
		model.addAttribute("ListaR", datosRolesDB);
		return "/roles/listaroles"; //ruta fisica de la pagina web
	}
	
	@GetMapping("/nuevorol") //url
	public String insertarRoles() {//metodo de ejecucion al leer la url
		return "/roles/nuevorol"; //ruta fisica de la pagina web
	}
}
