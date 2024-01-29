package com.finesec.pictogramas.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.finesec.pictogramas.model.Usuarios;
import com.finesec.pictogramas.repository.IUsuariosRepository;
import com.finesec.pictogramas.service.IUsuarioService;

@Service
@Component

public class UsuarioServiceImpl  implements IUsuarioService{
	@Autowired
	private IUsuariosRepository repo;

	@Override
	public void insertarUsuario(Usuarios nuevo) {
		repo.save(nuevo);
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Usuarios> ListarUsuario() {
		// TODO Auto-generated method stub
		return repo.findAll();
	}

	@Override
	public Usuarios buscarCI(String ci) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Usuarios findByIdUsuario(int idUsuario) {
		return repo.findByIdUsuario(idUsuario);
	}

	//Método para inicio de sesión
	@Override
	public Usuarios findByEmail(String emeal) {
		// TODO Auto-generated method stub
		return repo.findByEmail(emeal);
	}
	
}
