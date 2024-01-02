package com.finesec.pictogramas.service;

import java.util.List;


import com.finesec.pictogramas.model.Roles;

public interface IRolService {
	
	public void insertarRol(Roles nuevo);
	public List<Roles> ListarRoles();
	public Roles buscar(String nombre);

}
