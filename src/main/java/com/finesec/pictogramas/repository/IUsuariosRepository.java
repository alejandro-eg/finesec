package com.finesec.pictogramas.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.finesec.pictogramas.model.Usuarios;

public interface IUsuariosRepository  extends JpaRepository<Usuarios, Integer>{
	public Usuarios findByIdUsuario(int idUsuario);
}
