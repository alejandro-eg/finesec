package com.finesec.pictogramas.service;

import java.util.List;

import com.finesec.pictogramas.model.Usuarios;

public interface IUsuarioService {

	public void insertarUsuario(Usuarios nuevo);
	public List<Usuarios> ListarUsuario();
	public Usuarios buscarCI(String ci);
	public Usuarios findByIdUsuario(int idUsuario);
	//Método para inicio de sesión
	public Usuarios findByEmail(String emeal);
	
}
