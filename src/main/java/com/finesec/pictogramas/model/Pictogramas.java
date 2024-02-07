package com.finesec.pictogramas.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.Data;

@Data
@Entity

public class Pictogramas implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idPictograma;//id_rol
	private String nombre;
	private String descripcion;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "fkCategoriaPictograma")
	private CategoriaPictograma fkCategoriaPictograma;
	
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy ="pictograma")
	private List<AsignacionPictograma> asPictogramas = new ArrayList<>();
}
