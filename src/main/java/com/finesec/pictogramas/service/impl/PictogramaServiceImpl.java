package com.finesec.pictogramas.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.finesec.pictogramas.model.Pictogramas;
import com.finesec.pictogramas.repository.IPictogramaRepository;
import com.finesec.pictogramas.service.IPictogramaService;
@Service
@Component

public class PictogramaServiceImpl implements IPictogramaService{
	@Autowired
	private IPictogramaRepository repo;

	@Override
	public void insertarPictograma(Pictogramas nuevo) {
		repo.save(nuevo);
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Pictogramas> ListarPictograma() {
		// TODO Auto-generated method stub
		return repo.findAll();
	}

	@Override
	public Pictogramas buscar(String nombre) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Pictogramas findByIdPictograma(int idPictograma) {
		return repo.findByIdPictograma(idPictograma);
	}

	@Override
	public void eliminarPictograma(int idPictograma) {
		repo.deleteById(idPictograma);
		
	}


}
