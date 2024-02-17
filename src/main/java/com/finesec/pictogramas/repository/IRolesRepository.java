package com.finesec.pictogramas.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.finesec.pictogramas.model.Roles;

public interface IRolesRepository extends JpaRepository<Roles, Integer> {
	public Roles findByIdRol(int idRol);
	public Roles findByNombre(String nombre);
}
