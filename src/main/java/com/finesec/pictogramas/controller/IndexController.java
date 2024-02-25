package com.finesec.pictogramas.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.finesec.pictogramas.model.Usuarios;
import com.finesec.pictogramas.service.IUsuarioService;

@Controller
public class IndexController {
	
	@Autowired
	private IUsuarioService servicioUsuarios;
	private Usuarios nuevoUsuario;
	
	
	@GetMapping("/principal") //url
	public String inicio() { //metodo de ejecucion al leer la url
		return "/index"; //ruta fisica de la pagina web
	}
	
	@GetMapping("/")
    public String index(Model model) {
		// Obtener la autenticación actual
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        // Obtener el email del usuario autenticado
        String emailUsuarioActual = authentication.getName();

        // Buscar al usuario por su correo electrónico
        Usuarios usuario = servicioUsuarios.findByEmail(emailUsuarioActual);
        // Agregar el usuario al modelo
        model.addAttribute("usuario", usuario);

        // Devolver el nombre de la vista que quieres renderizar
        return "index";
    }
	
}
