package com.finesec.pictogramas.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.finesec.pictogramas.model.CategoriaPictograma;
import com.finesec.pictogramas.model.Pictogramas;
import com.finesec.pictogramas.model.Roles;
import com.finesec.pictogramas.model.Usuarios;
import com.finesec.pictogramas.service.ICategoriaPictogramaService;
import com.finesec.pictogramas.service.IPictogramaService;
import com.finesec.pictogramas.service.IRolService;
import com.finesec.pictogramas.service.IUsuarioService;

@Controller
public class PictogramasController {
	@Autowired
	private IPictogramaService servicioPictograma;
	private Pictogramas nuevoPictograma;
	
	@Autowired
    private ICategoriaPictogramaService servicioCategoriaPictogramas;
	
	@Autowired
	private IUsuarioService servicioUsuarios;
	
	@GetMapping("/pictogramas") //url
	public String listarPictogramas(Model model) { //metodo de ejecucion al leer la url
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String email = authentication.getName(); //Obtengo el nombre de usuario (en el security confing se conoce como Name)
		Usuarios usuario = servicioUsuarios.findByEmail(email);
		
		if (usuario != null && !usuario.getRestriccionPictograma()) {
	        // Si restriccionUsuario es true, redirige a la página de inicio ("/")
	        return "redirect:/";
	    } else {
	    	List<Pictogramas> datosPictogramaDB= servicioPictograma.ListarPictograma();
			model.addAttribute("ListaP", datosPictogramaDB);
			return "/pictogramas/listarpictograma"; //ruta fisica de la pagina web
	    }
		
		
	}
	
	@GetMapping("/nuevopictograma") //url
	public String insertarPictograma(Model model) {
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String email = authentication.getName(); //Obtengo el nombre de usuario (en el security confing se conoce como Name)
		Usuarios usuario = servicioUsuarios.findByEmail(email);
		
		if (usuario != null && !usuario.getRestriccionPictograma()) {
	        // Si restriccionUsuario es true, redirige a la página de inicio ("/")
	        return "redirect:/";
	    } else {
	    	nuevoPictograma = new Pictogramas();
			List<CategoriaPictograma> listaCategoriaPictograma = servicioCategoriaPictogramas.ListarCategoriaPigtograma();
			model.addAttribute("nuevo", nuevoPictograma);//método de ejecución al leer la url
			model.addAttribute("listaCategoriaPictograma", listaCategoriaPictograma);
			return "/pictogramas/nuevopictograma"; //ruta fisica de la pagina web
	    }
		
	}
	
	@PostMapping("/guardarpictograma")
	public String guardarPictograma(@ModelAttribute("nuevo")Pictogramas nuevoPictograma) {
		servicioPictograma.insertarPictograma(nuevoPictograma);
		return "redirect:/pictogramas";
	}
	
	@GetMapping("/editarpictograma/{idPictograma}")
	public String editarPictograma(@PathVariable("idPictograma") int idPictograma, @RequestParam("categoriaId") int categoriaId, Model model) {
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String email = authentication.getName(); //Obtengo el nombre de usuario (en el security confing se conoce como Name)
		Usuarios usuario = servicioUsuarios.findByEmail(email);
		
		if (usuario != null && !usuario.getRestriccionPictograma()) {
	        // Si restriccionUsuario es true, redirige a la página de inicio ("/")
	        return "redirect:/";
	    } else {
	    	Pictogramas recuperadoDB = servicioPictograma.findByIdPictograma(idPictograma);
		    List<CategoriaPictograma> listaCategoriaPictograma = servicioCategoriaPictogramas.ListarCategoriaPigtograma();
		    model.addAttribute("nuevo", recuperadoDB);
		    model.addAttribute("listaCategoriaPictograma", listaCategoriaPictograma);
		    model.addAttribute("categoriaSeleccionada", categoriaId); // Pasar el ID de la categoría seleccionada
		    return "/pictogramas/nuevopictograma";
	    }
		
	}


	@GetMapping("/eliminarpictograma/{idPictograma}")
	public String eliminarPictograma(@PathVariable(value="idPictograma")int idPictograma) {
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String email = authentication.getName(); //Obtengo el nombre de usuario (en el security confing se conoce como Name)
		Usuarios usuario = servicioUsuarios.findByEmail(email);
		
		if (usuario != null && !usuario.getRestriccionPictograma()) {
	        // Si restriccionUsuario es true, redirige a la página de inicio ("/")
	        return "redirect:/";
	    } else {
	    	servicioPictograma.eliminarPictograma(idPictograma);
			return "redirect:/pictogramas";
	    }
		
	}
	
	
	@GetMapping("/vercategoriasdepictogramas/{nombreIngles}")
	public String verCategoriaPictograma(@PathVariable("nombreIngles") String nombreIngles, Model model) {
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String email = authentication.getName(); //Obtengo el nombre de usuario (en el security confing se conoce como Name)
		Usuarios usuario = servicioUsuarios.findByEmail(email);
		
		if (usuario != null && !usuario.getRestriccionPictograma()) {
	        // Si restriccionUsuario es true, redirige a la página de inicio ("/")
	        return "redirect:/";
	    } else {
	    	System.out.println(nombreIngles);
			model.addAttribute("nombreIngles", nombreIngles);
			return "/categoriapictogramas/vercategoriasdepictogramas";
	    }
		
	}
}
