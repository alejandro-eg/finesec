package com.finesec.pictogramas.controller;
 
import java.util.List;
import java.util.stream.Collectors;

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
import org.springframework.web.bind.annotation.RequestParam;

import com.finesec.pictogramas.model.PreguntasSeguridad;
import com.finesec.pictogramas.model.Roles;
import com.finesec.pictogramas.model.Usuarios;
import com.finesec.pictogramas.service.IPreguntasSeguridadService;
import com.finesec.pictogramas.service.IRolService;
import com.finesec.pictogramas.service.IUsuarioService;

 
@Controller
public class UsuariosController {
 
	@Autowired
	private IUsuarioService servicioUsuarios;
	private Usuarios nuevoUsuario;
	private Boolean editMode = false;
	private int idUsuarioSeleccionado = 0;
	
	@Autowired
    private IRolService servicioRol;
	@Autowired
    private IPreguntasSeguridadService servicioPreguntasSeguridad;
	
	
	@GetMapping("/usuarios") //url
	public String listarUsuarios(Model model) { //metodo de ejecucion al leer la url
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String email = authentication.getName(); //Obtengo el nombre de usuario (en el security confing se conoce como Name)
		Usuarios usuario = servicioUsuarios.findByEmail(email);
		
		if (usuario != null && !usuario.getRestriccionUsuario()) {
	        // Si restriccionUsuario es true, redirige a la página de inicio ("/")
	        return "redirect:/";
	    } else {
	    	List<Usuarios> datosUsuariosDB= servicioUsuarios.ListarUsuario();
			editMode = false;
			model.addAttribute("ListaU", datosUsuariosDB);
			model.addAttribute("editMode", editMode); // Pasa el modo de edición al modelo
			return "/usuario/listarusuario"; //ruta fisica de la pagina web
	    }
	}
	
	/*
	@GetMapping("/nuevou") //url
	public String insertarUsuario(Model model) {
		nuevoUsuario = new Usuarios();
		editMode = false;
	    System.out.println("Directo a nuevo usuario"+editMode);
		List<Roles> listaRoles = servicioRol.ListarRoles();
		List<PreguntasSeguridad> listaPreguntasSeguridad = servicioPreguntasSeguridad.ListarPreguntasSeguridad();
		model.addAttribute("nuevo", nuevoUsuario);//método de ejecución al leer la url
		model.addAttribute("listaRol", listaRoles);
		model.addAttribute("listaPreguntas", listaPreguntasSeguridad);
		return "/usuario/nuevousario"; //ruta fisica de la pagina web
	}*/
	
	
	@GetMapping("/nuevou") //url
	public String insertarUsuario(Model model) {
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String email = authentication.getName(); //Obtengo el nombre de usuario (en el security confing se conoce como Name)
		Usuarios usuario = servicioUsuarios.findByEmail(email);
		
		if (usuario != null && !usuario.getRestriccionUsuario()) {
	        // Si restriccionUsuario es true, redirige a la página de inicio ("/")
	        return "redirect:/";
	    } else {
	    	nuevoUsuario = new Usuarios();
	 	    editMode = false;
	 	    System.out.println("Directo a nuevo usuario" + editMode);
	 	    
	 	    // Obtener la lista de roles con estado true
	 	    List<Roles> listaRoles = servicioRol.ListarRoles().stream()
	 	                                .filter(rol -> rol.getEstado() != null && rol.getEstado())
	 	                                .collect(Collectors.toList());
	 	    
	 	    List<PreguntasSeguridad> listaPreguntasSeguridad = servicioPreguntasSeguridad.ListarPreguntasSeguridad();
	 	    model.addAttribute("nuevo", nuevoUsuario);//método de ejecución al leer la url
	 	    model.addAttribute("listaRol", listaRoles);
	 	    model.addAttribute("listaPreguntas", listaPreguntasSeguridad);
	 	    return "/usuario/nuevousario"; //ruta fisica de la pagina web
	    }
	
	}
	
	@PostMapping("/guardarusuario")
	public String guardarUsuario(@ModelAttribute("nuevo") Usuarios nuevoUsuario, BindingResult result, Model model) {
	    // Verificar si el usuario es nuevo o si es una actualización
		System.out.println("ID USUARIO:"+nuevoUsuario.getIdUsuario());
		if (!editMode) { // Nuevo usuario (asumiendo que el ID 0 indica un nuevo usuario)
	        // Verificar si ya existe un usuario con el mismo email o cédula
	    	System.out.println("Entro a crear el usuario");

	        Usuarios emailExistente = servicioUsuarios.findByEmail(nuevoUsuario.getEmail());
	        Usuarios cedulaExistente = servicioUsuarios.findByCi(nuevoUsuario.getCi());

	        if (emailExistente != null) {
	            // Mostrar alerta si ya existe un usuario con el mismo email
	            model.addAttribute("alerta", "Ya ha sido creado un usuario con ese email, por favor proporciona uno nuevo");
	            model.addAttribute("listaRol", servicioRol.ListarRoles());
	            model.addAttribute("listaPreguntas", servicioPreguntasSeguridad.ListarPreguntasSeguridad());
	            System.out.println("ENTRO AQUIIIIII");
	            return "/usuario/nuevousario";
	        } else if (cedulaExistente != null) {
	            // Mostrar alerta si ya existe un usuario con la misma cédula
	            model.addAttribute("alerta2", "Ya ha sido creado un usuario con esa cédula, por favor proporciona una nueva");
	            model.addAttribute("listaRol", servicioRol.ListarRoles());
	            model.addAttribute("listaPreguntas", servicioPreguntasSeguridad.ListarPreguntasSeguridad());
	            return "/usuario/nuevousario";
	        }

	        // Guardar el nuevo usuario
	        servicioUsuarios.insertarUsuario(nuevoUsuario);
	    } else { // Usuario existente, por lo tanto es una actualización
	    	System.out.println("Entro a actualizar");
	        // Actualizar el usuario existente
	        servicioUsuarios.actualizarUsuario(idUsuarioSeleccionado, nuevoUsuario);
	    }
	    editMode = false;
        model.addAttribute("editMode", editMode);
	    // Redirigir a la página de usuarios después de guardar o actualizar
	    return "redirect:/usuarios";
	}

	@GetMapping("/editarusuario/{idUsuario}")
	public String editarUsuario(@PathVariable("idUsuario") int idUsuario, Model model) {
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String email = authentication.getName(); //Obtengo el nombre de usuario (en el security confing se conoce como Name)
		Usuarios usuario = servicioUsuarios.findByEmail(email);
		
		if (usuario != null && !usuario.getRestriccionUsuario()) {
	        // Si restriccionUsuario es true, redirige a la página de inicio ("/")
	        return "redirect:/";
	    } else {
	    	Usuarios recuperadoDB = servicioUsuarios.findByIdUsuario(idUsuario);
		    List<Roles> listaRoles = servicioRol.ListarRoles();
		    List <PreguntasSeguridad> listaPreguntasSeguridad = servicioPreguntasSeguridad.ListarPreguntasSeguridad();
		    model.addAttribute("nuevo", recuperadoDB);
		    model.addAttribute("listaRol", listaRoles);
		    model.addAttribute("listaPreguntas", listaPreguntasSeguridad);
		    editMode = true;
		    idUsuarioSeleccionado = idUsuario;
	        model.addAttribute("editMode", editMode);
		    System.out.println("Aplasto en editar: "+ editMode);
		    return "/usuario/nuevousario";
	    }  
	}
	
	@GetMapping("/eliminarusuario/{idUsuario}")
	public String eliminarUsuario(@PathVariable(value="idUsuario")int idUsuario) {
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String email = authentication.getName(); //Obtengo el nombre de usuario (en el security confing se conoce como Name)
		Usuarios usuario = servicioUsuarios.findByEmail(email);
		
		if (usuario != null && !usuario.getRestriccionUsuario()) {
	        // Si restriccionUsuario es true, redirige a la página de inicio ("/")
	        return "redirect:/";
	    } else {
	    	servicioUsuarios.eliminarUsuario(idUsuario);
			return "redirect:/usuarios";
	    }  
	}
	
	@GetMapping("/buscarusuarios")
	public String buscarUsuarios(@RequestParam(name = "nombres", required = false) String nombres,
	                             @RequestParam(name = "rolId", required = false) Integer rolId,
	                             Model model) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String email = authentication.getName(); //Obtengo el nombre de usuario (en el security confing se conoce como Name)
		Usuarios usuario = servicioUsuarios.findByEmail(email);
		
		if (usuario != null && !usuario.getRestriccionUsuario()) {
	        // Si restriccionUsuario es true, redirige a la página de inicio ("/")
	        return "redirect:/";
	    } else {
	    	List<Usuarios> usuariosEncontrados = servicioUsuarios.buscarUsuarios(nombres, rolId);
	  	    model.addAttribute("ListaU", usuariosEncontrados);
	  	    return "/usuario/listarusuario";
	    }   
	}

}
