package com.finesec.pictogramas.controller;
 
import java.util.List;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
 
import com.finesec.pictogramas.model.Usuarios;
import com.finesec.pictogramas.service.IUsuarioService;
 
@Controller
public class UsuariosController {
 
	@Autowired
	private IUsuarioService servicioUsuarios;
	@GetMapping("/usuarios") //url
	public String listarUsuarios(Model model) { //metodo de ejecucion al leer la url
		List<Usuarios> datosUsuariosDB= servicioUsuarios.ListarUsuario();
		model.addAttribute("ListaU", datosUsuariosDB);
		return "/usuario/listarusuario"; //ruta fisica de la pagina web
	}
	@GetMapping("/nuevou") //url
	public String insertarUsuario() {//metodo de ejecucion al leer la url
		return "/usuario/nuevousario"; //ruta fisica de la pagina web
	}
}