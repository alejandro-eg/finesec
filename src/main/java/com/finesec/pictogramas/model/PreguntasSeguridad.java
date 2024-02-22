package com.finesec.pictogramas.model;

import java.io.Serializable;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class PreguntasSeguridad  implements Serializable{
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idPregunta;//id_rol
	private String pregunta;
	
	@Override
    public String toString() {
        return this.pregunta; // Devuelve el nombre del rol
    }
}
