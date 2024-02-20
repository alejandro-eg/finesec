package com.finesec.pictogramas.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.ui.Model;

@Controller
public class LoginController {

	@GetMapping("/olvide-contrasenia")
	public String olvide(@RequestParam("email") String email, Model model) {
		model.addAttribute("email", email);
		return "olvide-contrasenia";
	}
}
