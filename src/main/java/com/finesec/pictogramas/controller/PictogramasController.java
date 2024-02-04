package com.finesec.pictogramas.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.finesec.pictogramas.model.CategoriaPictograma;
import com.finesec.pictogramas.model.Pictogramas;
import com.finesec.pictogramas.model.Roles;
import com.finesec.pictogramas.service.ICategoriaPictogramaService;
import com.finesec.pictogramas.service.IPictogramaService;
import com.finesec.pictogramas.service.IRolService;

@Controller
public class PictogramasController {
	@Autowired
	private IPictogramaService servicioPictograma;
	private Pictogramas nuevoPictograma;
	
	@Autowired
    private ICategoriaPictogramaService servicioCategoriaPictogramas;
	
	@GetMapping("/pictogramas") //url
	public String listarPictogramas(Model model) { //metodo de ejecucion al leer la url
		List<Pictogramas> datosPictogramaDB= servicioPictograma.ListarPictograma();
		model.addAttribute("ListaP", datosPictogramaDB);
		return "/pictogramas/listarpictograma"; //ruta fisica de la pagina web
	}
	
	@GetMapping("/nuevopictograma") //url
	public String insertarPictograma(Model model) {
		nuevoPictograma = new Pictogramas();
		List<CategoriaPictograma> listaCategoriaPictograma = servicioCategoriaPictogramas.ListarCategoriaPigtograma();
		model.addAttribute("nuevo", nuevoPictograma);//método de ejecución al leer la url
		model.addAttribute("listaCategoriaPictograma", listaCategoriaPictograma);
		return "/pictogramas/nuevopictograma"; //ruta fisica de la pagina web
	}
	
	@PostMapping("/guardarpictograma")
	public String guardarPictograma(@ModelAttribute("nuevo")Pictogramas nuevoPictograma) {
		servicioPictograma.insertarPictograma(nuevoPictograma);
		return "redirect:/pictogramas";
	}
	
	@GetMapping("/editarpictograma/{idPictograma}")
	public String editarPictograma(@PathVariable("idPictograma") int idPictograma, Model model) {
	    Pictogramas recuperadoDB = servicioPictograma.findByIdPictograma(idPictograma);
	    model.addAttribute("nuevo", recuperadoDB);
	    return "/pictogramas/nuevopictograma";
	}
	
	@GetMapping("/eliminarpictograma/{idPictograma}")
	public String eliminarPictograma(@PathVariable(value="idPictograma")int idPictograma) {
		servicioPictograma.eliminarPictograma(idPictograma);
		return "redirect:/pictogramas";
	}
}
