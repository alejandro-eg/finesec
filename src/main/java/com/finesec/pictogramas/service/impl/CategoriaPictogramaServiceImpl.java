package com.finesec.pictogramas.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.finesec.pictogramas.model.CategoriaPictograma;
import com.finesec.pictogramas.repository.ICategoriaPictogramaRepository;
import com.finesec.pictogramas.service.ICategoriaPictogramaService;
@Service
@Component
public class CategoriaPictogramaServiceImpl implements ICategoriaPictogramaService {
	@Autowired
	private ICategoriaPictogramaRepository repo;
	

	@Override
	public void insertarCategoriaPictograma(CategoriaPictograma nuevo) {
		repo.save(nuevo);
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<CategoriaPictograma> ListarCategoriaPigtograma() {
		// TODO Auto-generated method stub
		return repo.findAll();
	}


	@Override
	public CategoriaPictograma findByIdCategoriaPictograma(int idCategoriaPictograma) {
		return repo.findByIdCategoriaPictograma(idCategoriaPictograma);
	}

	@Override
	public void eliminarCategoriaPictograma(int idCategoriaPictograma) {
		// TODO Auto-generated method stub
		repo.deleteById(idCategoriaPictograma);
		
	}

	@Override
	public CategoriaPictograma findByNombre(String nombre) {
		// TODO Auto-generated method stub
		return repo.findByNombre(nombre);
	}

	@Override
	public CategoriaPictograma findByNombreIngles(String nombreIngles) {
		// TODO Auto-generated method stub
		return repo.findByNombreIngles(nombreIngles);
	}

}
