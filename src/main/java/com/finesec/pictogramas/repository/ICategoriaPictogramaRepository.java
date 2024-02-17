package com.finesec.pictogramas.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.finesec.pictogramas.model.CategoriaPictograma;

public interface ICategoriaPictogramaRepository extends JpaRepository<CategoriaPictograma, Integer>{
	public CategoriaPictograma findByIdCategoriaPictograma(int idCategoriaPictograma);
	public CategoriaPictograma findByNombre(String nombre);
	public CategoriaPictograma findByNombreIngles(String nombreIngles);
}
