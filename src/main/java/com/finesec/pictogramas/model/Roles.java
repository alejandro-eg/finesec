package com.finesec.pictogramas.model;

import java.io.Serializable;
import java.util.List;
import java.util.ArrayList;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Data;

@Data
@Entity

public class Roles implements Serializable{
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idRol;//id_rol
	private String nombre;
	private String descripcion;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy ="rol")
	private List<Usuarios> usuarios = new ArrayList<>();
}
