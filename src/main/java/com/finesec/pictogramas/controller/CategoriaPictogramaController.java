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
import com.finesec.pictogramas.service.ICategoriaPictogramaService;

@Controller
public class CategoriaPictogramaController {
	@Autowired
	private ICategoriaPictogramaService servicioCategoriaPictograma;
	private CategoriaPictograma nuevaCategoriaPictograma;
	@GetMapping("/categoriapictograma")
	public String listarCategoriaPictograma(Model model) {
		List<CategoriaPictograma> datosCategoriaPictogramasDB= servicioCategoriaPictograma.ListarCategoriaPigtograma();
		model.addAttribute("ListaCP", datosCategoriaPictogramasDB);
		 return "/categoriapictogramas/listarcategoriapictograma";
		
	}
	@GetMapping("/nuevacategoriapictograma")
	public String insertarCategoriaPictograma(Model model) {
		nuevaCategoriaPictograma = new CategoriaPictograma();
		model.addAttribute("nuevo", nuevaCategoriaPictograma);
		return "/categoriapictogramas/nuevacategoriapictograma";
	}
	
	@PostMapping("/guardarcategoriapictograma")
	public String guardarCategoriaPictograma(@ModelAttribute("nuevo")CategoriaPictograma nuevaCategoriaPictograma, Model model){
		CategoriaPictograma nombreExistente = servicioCategoriaPictograma.findByNombre(nuevaCategoriaPictograma.getNombre());
		CategoriaPictograma keywordExistente = servicioCategoriaPictograma.findByNombreIngles(nuevaCategoriaPictograma.getNombreIngles());
		if (nombreExistente != null) {
            // si la categoria existe mostrara una alerta
            model.addAttribute("alerta", "La categoria ya existe. Por favor, elige otro nombre.");
            return "/categoriapictogramas/nuevacategoriapictograma";
        }else if (keywordExistente != null) {
        	 model.addAttribute("alerta2", "La categoria ya existe. Por favor, elige otro keyword.");
             return "/categoriapictogramas/nuevacategoriapictograma";
		}
		servicioCategoriaPictograma.insertarCategoriaPictograma(nuevaCategoriaPictograma);
		return "redirect:/categoriapictograma";
	}
	
	@GetMapping("/editarcategoriapictograma/{idCategoriaPictograma}")
	public String editarCategoriaPictograma(@PathVariable("idCategoriaPictograma") int idCategoriaPictograma, Model model) {
	    CategoriaPictograma recuperadoDB = servicioCategoriaPictograma.findByIdCategoriaPictograma(idCategoriaPictograma);
	    model.addAttribute("nuevo", recuperadoDB);
	    return "/categoriapictogramas/nuevacategoriapictograma";
	}
	
	@GetMapping("/eliminarcategoriapictograma/{idCategoriaPictograma}")
	public String eliminarCategoriaPictograma(@PathVariable(value="idCategoriaPictograma")int idCategoriaPictograma) {
		servicioCategoriaPictograma.eliminarCategoriaPictograma(idCategoriaPictograma);
		return "redirect:/categoriapictograma";
	}

}
