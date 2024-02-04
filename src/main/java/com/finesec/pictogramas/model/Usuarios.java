package com.finesec.pictogramas.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "usuarios")//Establece el nombre de la tabla que se asigne

public class Usuarios implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idUsuario;//idUsuario
	private String nombres;
	private String apellidos;
	private String direccion;
	private String telefono;
	//Variables para inicio de sesión
	@Column(name = "user_correo")//me permite cambiar de nombre el atributo 
	private String email;
	private String password;
	@Column(name = "user_identificacion",length = 15)
	private String ci;
	private boolean estadoRegistro;//estado_registro
	
	
	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "rol")
	private Roles rol;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy ="usuarios")
	private List<AsignacionPictograma> asPictogramas = new ArrayList<>();

}
