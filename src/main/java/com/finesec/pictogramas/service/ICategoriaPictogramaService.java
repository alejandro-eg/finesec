package com.finesec.pictogramas.service;

import java.util.List;

import com.finesec.pictogramas.model.CategoriaPictograma;


public interface ICategoriaPictogramaService {
	
	public void insertarCategoriaPictograma(CategoriaPictograma nuevo);
	public List<CategoriaPictograma> ListarCategoriaPigtograma();
	public CategoriaPictograma findByNombre(String nombre);
	public CategoriaPictograma findByNombreIngles(String nombreIngles);
	public CategoriaPictograma findByIdCategoriaPictograma(int idCategoriaPictograma);
	public void eliminarCategoriaPictograma(int idCategoriaPictograma);

}
