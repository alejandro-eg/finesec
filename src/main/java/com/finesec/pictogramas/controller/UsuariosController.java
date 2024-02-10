package com.finesec.pictogramas.controller;
 
import java.util.List;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
	
	@Autowired
    private IRolService servicioRol;
	@GetMapping("/usuarios") //url
	public String listarUsuarios(Model model) { //metodo de ejecucion al leer la url
		List<Usuarios> datosUsuariosDB= servicioUsuarios.ListarUsuario();
		model.addAttribute("ListaU", datosUsuariosDB);
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
	public String guardarUsuario(@ModelAttribute("nuevo")Usuarios nuevoUsuario) {
		servicioUsuarios.insertarUsuario(nuevoUsuario);
		return "redirect:/usuarios";
	}
	
	@GetMapping("/editarusuario/{idUsuario}")
	public String editarUsuario(@PathVariable("idUsuario") int idUsuario, Model model) {
	    Usuarios recuperadoDB = servicioUsuarios.findByIdUsuario(idUsuario);
	    List<Roles> listaRoles = servicioRol.ListarRoles();
	    model.addAttribute("nuevo", recuperadoDB);
	    model.addAttribute("listaRol", listaRoles);
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