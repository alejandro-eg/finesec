package com.finesec.pictogramas.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.finesec.pictogramas.model.Usuarios;

public interface IUsuariosRepository extends JpaRepository<Usuarios, Integer> {
	public Usuarios findByIdUsuario(int idUsuario);

	// Método para inicio de sesión
	public Usuarios findByEmail(String emeal);
	
	public Usuarios findByCi(String ci);

	List<Usuarios> findByNombresAndRol_IdRol(String nombres, Integer rolId);

	List<Usuarios> findByNombres(String nombres);

	List<Usuarios> findByRol_IdRol(Integer rolId);
	
	
}
