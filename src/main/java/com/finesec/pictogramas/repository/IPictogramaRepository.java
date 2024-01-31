package com.finesec.pictogramas.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.finesec.pictogramas.model.Pictogramas;

public interface IPictogramaRepository  extends JpaRepository<Pictogramas, Integer>{
	public Pictogramas findByIdPictograma(int idPictograma);
}
