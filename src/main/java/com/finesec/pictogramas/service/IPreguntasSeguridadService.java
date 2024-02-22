package com.finesec.pictogramas.service;

import java.util.List;

import com.finesec.pictogramas.model.PreguntasSeguridad;

public interface IPreguntasSeguridadService {
	public List<PreguntasSeguridad> ListarPreguntasSeguridad();
    public PreguntasSeguridad buscar(String nombre);
    public PreguntasSeguridad findByIdPregunta(int idPregunta);
}
