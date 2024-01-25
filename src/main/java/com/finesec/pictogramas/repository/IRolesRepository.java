package com.finesec.pictogramas.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.finesec.pictogramas.model.Roles;
import com.finesec.pictogramas.model.Usuarios;

public interface IRolesRepository extends JpaRepository<Roles, Integer> {
	public Roles findByIdRol(int idRol);
}
