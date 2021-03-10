package org.jesuitasrioja.ProyectoCovid.modelo.profesor;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.jesuitasrioja.ProyectoCovid.modelo.alumno.Alumno;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.EqualsAndHashCode.Include;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Profesor")
public class Profesor implements Serializable{
	@Id
	@Include
	private String identificador;
	private String nombre;
	private String telefono;
}
