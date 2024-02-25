package com.finesec.pictogramas.service;

import java.util.List;

import com.finesec.pictogramas.model.Usuarios;

public interface IUsuarioService {

	public void insertarUsuario(Usuarios nuevo);
	public List<Usuarios> ListarUsuario();
	public List<Usuarios> buscarUsuarios(String nombres, Integer rolId);
	public Usuarios findByCi(String ci);
	public Usuarios findByIdUsuario(int idUsuario);
	//Método para inicio de sesión
	public Usuarios findByEmail(String emeal);
	public void eliminarUsuario(int idUsuario);
	
	//metodo para buscar
	public Usuarios buscar(String nombres);
	
	public void actualizarUsuario(int idUsuario, Usuarios usuarioActualizado);
	
	
}
