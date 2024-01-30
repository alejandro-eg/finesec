package com.finesec.pictogramas.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.finesec.pictogramas.model.Grupos;

public interface IGruposRepository extends JpaRepository<Grupos, Integer> {
	public Grupos findByIdGrupo(int idGrupo);

}