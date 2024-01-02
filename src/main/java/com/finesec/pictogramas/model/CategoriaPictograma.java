package com.finesec.pictogramas.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Data;

@Data
@Entity

public class CategoriaPictograma implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idCategoriaPictograma;//id_rol
	private String nombre;
	private String descripcion;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy ="categoriaPictograma")
	private List<Pictogramas> usuarios = new ArrayList<>();
}
