package com.finesec.pictogramas.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.finesec.pictogramas.model.Grupos;
import com.finesec.pictogramas.repository.IGruposRepository;
import com.finesec.pictogramas.service.IGruposService;
@Service
@Component
public class GruposServiceImpl implements IGruposService{
	@Autowired
	private IGruposRepository repo;
	@Override
	public void insertarGrupo(Grupos nuevo) {
		repo.save(nuevo);
		// TODO Auto-generated method stub

	}

	@Override
	public List<Grupos> ListarGrupos() {
		// TODO Auto-generated method stub
		return repo.findAll();
	}

	@Override
	public Grupos buscar(String nombre) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Grupos findByIdGrupo(int idGrupo) {
		// TODO Auto-generated method stub
		return repo.findByIdGrupo(idGrupo);
	}

}