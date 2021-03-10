package org.jesuitasrioja.ProyectoCovid.modelo.responsable;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponsableDTO implements Serializable{
	
	private long identificador;
	private String telefono;
	private String parentesco;
	private String nombre;
}
