package com.finesec.pictogramas;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.finesec.pictogramas.model.CategoriaPictograma;
import com.finesec.pictogramas.model.Roles;
import com.finesec.pictogramas.model.Usuarios;
import com.finesec.pictogramas.service.ICategoriaPictogramaService;
import com.finesec.pictogramas.service.IRolService;
import com.finesec.pictogramas.service.IUsuarioService;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrlPattern;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
class FinesecApplicationTests {
	
	@Autowired
	private IRolService servicioRol;
	
	@Autowired
	private IUsuarioService servicioUsr;
	
	@Autowired
	private ICategoriaPictogramaService serviciocatp;
	

	@Test
	void contextLoads() {
		
		Roles nuevoRol = new Roles(); 
		nuevoRol.setNombre("Administrador");
		nuevoRol.setDescripcion("Administrador BDD");
		nuevoRol.setEstado(true);
		//nuevoRol.setIdRol(1);
		
		//servicioRol.insertarRol(nuevoRol);
		
		//Usuario
		Usuarios nuevoUsr = new Usuarios();
		nuevoUsr.setApellidos("Pizarra");
		nuevoUsr.setCi("1725358525");
		nuevoUsr.setDireccion("Solanda");
		nuevoUsr.setEmail("test@mail.com");
		nuevoUsr.setEstadoRegistro(true);
		nuevoUsr.setIdpreguntaUno(1);
		nuevoUsr.setIdpreguntaDos(2);
		nuevoUsr.setIdpreguntaTres(3);
		nuevoUsr.setNombres("Xavier");
		nuevoUsr.setPassword("123123");
		nuevoUsr.setPreguntaUno("1");
		nuevoUsr.setPreguntaDos("2");
		nuevoUsr.setPreguntaTres("3");
		nuevoUsr.setRestriccionRol(false);
		nuevoUsr.setTelefono("0990803129");	
		//nuevoUsr.setRol(nuevoRol);
	
		
		//servicioUsr.insertarUsuario(nuevoUsr);
		
		
	}

}
