package org.jesuitasrioja.ProyectoCovid.modelo.responsable;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

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
@Table(name = "Responsable")
public class Responsable implements Serializable{
	@Id
	@Include
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long identificador;
	private String telefono;
	private String parentesco;
	private String nombre;
}
