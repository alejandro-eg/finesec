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
import com.finesec.pictogramas.model.PreguntasSeguridad;
import com.finesec.pictogramas.service.IPreguntasSeguridadService;
import com.finesec.pictogramas.service.IUsuarioService;

import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

@Controller
public class LoginController {
	@Autowired
	private IUsuarioService servicioUsuarios;
	@Autowired
	private IPreguntasSeguridadService servicioPreguntasSeguridad;

	@GetMapping("/olvide-contrasenia")
	public String olvide(@RequestParam("email") String email, Model model) {
		Usuarios usuarioActualizar = new Usuarios(); // Crear una nueva instancia de Usuarios

		System.out.println(email);

		Usuarios usuarioExistente = servicioUsuarios.findByEmail(email);
		

		if (usuarioExistente == null) {
			model.addAttribute("alerta2", "El correo es inválido o no existe");
			return "/login";
		} else {
			model.addAttribute("email", email);
			model.addAttribute("usuarioActualizar", usuarioActualizar); // Agregar usuarioActualizar al modelo
			int id1 = usuarioExistente.getIdpreguntaUno();
			int id2 = usuarioExistente.getIdpreguntaDos();
			int id3 = usuarioExistente.getIdpreguntaTres();
			// PASO DE PREGUNTAS DE SEGURIDAD
			PreguntasSeguridad pregunta1 = servicioPreguntasSeguridad.findByIdPregunta(id1);
			PreguntasSeguridad pregunta2 = servicioPreguntasSeguridad.findByIdPregunta(id2);
			PreguntasSeguridad pregunta3 = servicioPreguntasSeguridad.findByIdPregunta(id3);
			model.addAttribute("pregunta1", pregunta1.getPregunta());
			model.addAttribute("pregunta2", pregunta2.getPregunta());
			model.addAttribute("pregunta3", pregunta3.getPregunta());
			// PASO DE RESPUESTAS
			model.addAttribute("respuesta1", usuarioExistente.getPreguntaUno());
			model.addAttribute("respuesta2", usuarioExistente.getPreguntaDos());
			model.addAttribute("respuesta3", usuarioExistente.getPreguntaTres());
			return "olvide-contrasenia";
		}
	}

	@PostMapping("/cambiar-contrasenia")
	public String cambiar(@RequestParam("email") String email,
			@RequestParam("nuevaContrasenia") String nuevaContrasenia,
			@RequestParam("confirmarContrasenia") String confirmarContrasenia, Model model) {
		// Verificar si el usuario existe por email
		Usuarios usuarioExistente = servicioUsuarios.findByEmail(email);

		System.out.println(usuarioExistente);
		System.out.println(nuevaContrasenia);
		System.out.println(confirmarContrasenia);

		if (!nuevaContrasenia.equals(confirmarContrasenia)) {
			model.addAttribute("alerta2", "Las contraseñas no coinciden, intentelo de nuevo.");
			return "/login";
		} else {
			// Actualizar la contraseña del usuario
			usuarioExistente.setPassword(nuevaContrasenia);

			servicioUsuarios.insertarUsuario(usuarioExistente); // Asegúrate de tener un método guardarUsuario en tu
																// servicio
			model.addAttribute("alerta3", "Contraseña editada con éxito, inicie sesión.");

			return "/login";
		}
	}
}
