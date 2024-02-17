package com.finesec.pictogramas.model;

import java.io.Serializable;
import java.util.List;
import java.util.ArrayList;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "roles")//Establece el nombre de la tabla que se asigne

public class Roles implements Serializable{
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idRol;//id_rol
	private String nombre;
	private String descripcion;
	private Boolean estado;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy ="rol")
	private List<Usuarios> usuarios = new ArrayList<>();
	
	@Override
    public String toString() {
        return this.nombre; // Devuelve el nombre del rol
    }
}
