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
import org.springframework.web.bind.annotation.RequestParam;

import com.finesec.pictogramas.model.Roles;
import com.finesec.pictogramas.model.Usuarios;
import com.finesec.pictogramas.service.IRolService;
import com.finesec.pictogramas.service.IUsuarioService;

 
@Controller
public class UsuariosController {
 
	@Autowired
	private IUsuarioService servicioUsuarios;
	private Usuarios nuevoUsuario;
	private Boolean editMode = false;
	
	@Autowired
    private IRolService servicioRol;
	@GetMapping("/usuarios") //url
	public String listarUsuarios(Model model) { //metodo de ejecucion al leer la url
		List<Usuarios> datosUsuariosDB= servicioUsuarios.ListarUsuario();
		model.addAttribute("ListaU", datosUsuariosDB);
		model.addAttribute("editMode", editMode); // Pasa el modo de edición al modelo
		return "/usuario/listarusuario"; //ruta fisica de la pagina web
	}
	
	@GetMapping("/nuevou") //url
	public String insertarUsuario(Model model) {
		nuevoUsuario = new Usuarios();
		List<Roles> listaRoles = servicioRol.ListarRoles();
		model.addAttribute("nuevo", nuevoUsuario);//método de ejecución al leer la url
		model.addAttribute("listaRol", listaRoles);
		return "/usuario/nuevousario"; //ruta fisica de la pagina web
	}
	
	@PostMapping("/guardarusuario")
	public String guardarUsuario(@ModelAttribute("nuevo")Usuarios nuevoUsuario, BindingResult result, Model model) {
	    Usuarios emailExistente = servicioUsuarios.findByCi(nuevoUsuario.getCi());
	    Usuarios cedulaExistente = servicioUsuarios.findByEmail(nuevoUsuario.getEmail());

	    if (emailExistente != null) {
	        // Validación condicional para evitar errores en el campo ci cuando estamos en modo de edición
	        if (!editMode) {
	            // si el usuario con ese email existe mostrara una alerta
	            model.addAttribute("alerta", "Ya ha sido creado un usuario con esa cedula, por favor proporciona una nueva");
	            model.addAttribute("listaRol", servicioRol.ListarRoles());
	            editMode = false;
	            model.addAttribute("editMode", editMode);
	            return "/usuario/nuevousario";
	        }
	    } else if (cedulaExistente != null) {
	    	
	    	if (!editMode) {
	    		// si el usuario con esa cedula existe mostrara una alerta
		        model.addAttribute("alerta2", "Ya ha sido creado un usuario con ese email, por favor proporciona una nueva");
		        model.addAttribute("listaRol", servicioRol.ListarRoles());
		        editMode = false;
		        model.addAttribute("editMode", editMode);
		        return "/usuario/nuevousario";
			}
	        
	    }

	    servicioUsuarios.insertarUsuario(nuevoUsuario);
	    
	    // Reinicia editMode
	    editMode = false;
	    model.addAttribute("editMode", editMode);

	    return "redirect:/usuarios";
	}

	
	@GetMapping("/editarusuario/{idUsuario}")
	public String editarUsuario(@PathVariable("idUsuario") int idUsuario, Model model) {
	    Usuarios recuperadoDB = servicioUsuarios.findByIdUsuario(idUsuario);
	    List<Roles> listaRoles = servicioRol.ListarRoles();
	    model.addAttribute("nuevo", recuperadoDB);
	    model.addAttribute("listaRol", listaRoles);
	    editMode = true;
        model.addAttribute("editMode", editMode);
	    /*model.addAttribute("rolSeleccionado", rolId);*/
	    return "/usuario/nuevousario";
	}
	@GetMapping("/eliminarusuario/{idUsuario}")
	public String eliminarUsuario(@PathVariable(value="idUsuario")int idUsuario) {
		servicioUsuarios.eliminarUsuario(idUsuario);
		return "redirect:/usuarios";
	}
	
	@GetMapping("/buscarusuarios")
	public String buscarUsuarios(@RequestParam(name = "nombres", required = false) String nombres,
	                             @RequestParam(name = "rolId", required = false) Integer rolId,
	                             Model model) {
	    List<Usuarios> usuariosEncontrados = servicioUsuarios.buscarUsuarios(nombres, rolId);
	    model.addAttribute("ListaU", usuariosEncontrados);
	    return "/usuario/listarusuario";
	}

}
