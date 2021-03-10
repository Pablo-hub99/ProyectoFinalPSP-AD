package org.jesuitasrioja.ProyectoCovid.modelo.alumno;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AlumnoDTO implements Serializable{
	
	private String identificador;	
	private String dni;
	private String nombre;	

}
