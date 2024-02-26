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
import jakarta.persistence.ManyToOne;
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
	
	private Boolean estadoRegistro;//estado_registro
	
	//parametrizacion menu
	private Boolean restriccionRol;
	private Boolean restriccionPictograma;
	private Boolean restriccionCategoria;
	private Boolean restriccionUsuario;
	
	//Variables de seguridad
	
	@Column(name = "idpregunta_uno")
	private Integer idpreguntaUno;
	@Column(name = "idpregunta_dos")
	private Integer idpreguntaDos;
	@Column(name = "idpregunta_tres")
	private Integer idpreguntaTres;
	@Column(name = "pregunta_uno")
	private String preguntaUno;
	@Column(name = "pregunta_dos")
	private String preguntaDos;
	@Column(name = "pregunta_tres")
	private String preguntaTres;
	
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "rol")
	private Roles rol;
	
	
	
	/*@OneToMany(cascade = CascadeType.ALL, mappedBy ="usuarios")
	private List<AsignacionPictograma> asPictogramas = new ArrayList<>();*/

}
