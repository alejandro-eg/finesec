package com.finesec.pictogramas.service;

import java.util.List;
import com.finesec.pictogramas.model.Roles;

public interface IRolService {

    public void insertarRol(Roles nuevo);
    public List<Roles> ListarRoles();
    public Roles buscar(String nombre);
    public Roles findByIdRol(int idRol);
    public void eliminarRol(int idRol);

    // Nuevo método para buscar un rol por nombre
    public Roles findByNombre(String nombre);
}
