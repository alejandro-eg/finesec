package com.finesec.pictogramas.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.finesec.pictogramas.model.CategoriaPictograma;
import com.finesec.pictogramas.model.Roles;
import com.finesec.pictogramas.model.Usuarios;
import com.finesec.pictogramas.service.IUsuarioService;

import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

@Controller
public class LoginController {
	@Autowired
	private IUsuarioService servicioUsuarios;
	
	@GetMapping("/olvide-contrasenia")
	public String olvide(@RequestParam("email") String email, Model model) {
        Usuarios usuarioActualizar = new Usuarios(); // Crear una nueva instancia de Usuarios
        System.out.println(email);
		model.addAttribute("email", email);
        model.addAttribute("usuarioActualizar", usuarioActualizar); // Agregar usuarioActualizar al modelo
		return "olvide-contrasenia";
	}
	
	@PostMapping("/cambiar-contrasenia")
	public String cambiar(@RequestParam("email") String email, @RequestParam("nuevaContrasenia") String nuevaContrasenia, Model model) {
	    // Verificar si el usuario existe por email
	    Usuarios usuarioExistente = servicioUsuarios.findByEmail(email);

	    if (usuarioExistente == null) {
	        // Si el usuario no existe, mostrar una alerta
	        model.addAttribute("alerta", "El correo electrónico no existe. Por favor, verifica los datos.");
	        return "olvide-contrasenia";
	    }

	    // Actualizar la contraseña del usuario
	    usuarioExistente.setPassword(nuevaContrasenia);
	    servicioUsuarios.insertarUsuario(usuarioExistente); // Asegúrate de tener un método guardarUsuario en tu servicio

	    // Redirigir a algún lugar, como una página de confirmación
	    return "redirect:/cambio-contrasenia-confirmacion";
	}
}
