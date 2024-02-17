package com.finesec.pictogramas.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.finesec.pictogramas.model.Roles;
import com.finesec.pictogramas.repository.IRolesRepository;
import com.finesec.pictogramas.service.IRolService;
@Service
@Component

public class RolServiceImpl implements IRolService{
	@Autowired
     private IRolesRepository repo;
	@Override
	public void insertarRol(Roles nuevo) {
		repo.save(nuevo);
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Roles> ListarRoles() {
		// TODO Auto-generated method stub
		return repo.findAll();
	}

	@Override
	public Roles buscar(String nombre) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Roles findByIdRol(int idRol) {
		return repo.findByIdRol(idRol);
	}

	@Override
	public void eliminarRol(int idRol) {
		// TODO Auto-generated method stub
		repo.deleteById(idRol);
	}

	@Override
	public Roles findByNombre(String nombre) {
		return repo.findByNombre(nombre);
	}

}
