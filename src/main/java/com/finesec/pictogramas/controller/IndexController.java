package com.finesec.pictogramas.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {
	@GetMapping("/principal") //url
	public String inicio() { //metodo de ejecucion al leer la url
		return "/index"; //ruta fisica de la pagina web
	}
}
