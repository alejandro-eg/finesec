package com.finesec.pictogramas.service;

import java.util.List;

import com.finesec.pictogramas.model.Grupos;


public interface IGruposService {

	public void insertarGrupo (Grupos nuevo);
	public List<Grupos> ListarGrupos();
	public Grupos buscar(String nombre);
	public Grupos findByIdGrupo(int idGrupo);
}