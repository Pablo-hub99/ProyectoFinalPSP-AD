package org.jesuitasrioja.ProyectoCovid.modelo.datosMedicos;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.jesuitasrioja.ProyectoCovid.modelo.alumno.Alumno;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.EqualsAndHashCode.Include;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "DatosMedicos")
public class DatosMedicos implements Serializable {

	@Id
	@Include
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long identificador;
	private String alergias;
	private String otros;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "id_alumno")
	private Alumno alumno;

}
