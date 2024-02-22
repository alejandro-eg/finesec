package com.finesec.pictogramas.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.finesec.pictogramas.model.PreguntasSeguridad;
import com.finesec.pictogramas.repository.IPreguntasSeguridadRepository;
import com.finesec.pictogramas.service.IPreguntasSeguridadService;
@Service
@Component
public class PreguntasSeguridadServiceImpl  implements IPreguntasSeguridadService{
	@Autowired
    private IPreguntasSeguridadRepository repo;
	
	@Override
	public List<PreguntasSeguridad> ListarPreguntasSeguridad() {
		// TODO Auto-generated method stub
		return repo.findAll();
	}

	@Override
	public PreguntasSeguridad buscar(String nombre) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PreguntasSeguridad findByIdPregunta(int idPregunta) {
		return repo.findByIdPregunta(idPregunta);
	}
}
