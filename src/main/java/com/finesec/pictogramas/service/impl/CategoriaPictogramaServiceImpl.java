package com.finesec.pictogramas.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.finesec.pictogramas.model.CategoriaPictograma;
import com.finesec.pictogramas.model.Roles;
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
	public CategoriaPictograma buscar(String nombre) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CategoriaPictograma findByIdCategoriaPictograma(int idCategoriaPictograma) {
		return repo.findByIdCategoriaPictograma(idCategoriaPictograma);
	}
	
	

}
