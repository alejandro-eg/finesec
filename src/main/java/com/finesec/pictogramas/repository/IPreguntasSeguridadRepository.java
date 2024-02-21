package com.finesec.pictogramas.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.finesec.pictogramas.model.PreguntasSeguridad;

public interface IPreguntasSeguridadRepository  extends JpaRepository<PreguntasSeguridad, Integer> {
	public PreguntasSeguridad findByIdPregunta(int idPregunta);

}
