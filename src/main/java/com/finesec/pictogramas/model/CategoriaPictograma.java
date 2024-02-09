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
	private int idCategoriaPictograma;
	private String nombre;
	private String nombreIngles;
	private String descripcion;
	
	@OneToMany( mappedBy ="fkCategoriaPictograma", cascade = CascadeType.ALL)
	private List<Pictogramas> pictograma = new ArrayList<>();
	
	@Override
    public String toString() {
        return this.nombre; // Devuelve el nombre de la categoría
    }
}
