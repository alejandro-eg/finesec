package com.finesec.pictogramas.service;

import java.util.List;

import com.finesec.pictogramas.model.Pictogramas;

public interface IPictogramaService {
	
	
	public void insertarPictograma(Pictogramas nuevo);
	public List<Pictogramas> ListarPictograma();
	public Pictogramas buscar(String nombre);
	public Pictogramas findByIdPictograma(int idPictograma);
	public void eliminarPictograma(int idPictograma);

}
