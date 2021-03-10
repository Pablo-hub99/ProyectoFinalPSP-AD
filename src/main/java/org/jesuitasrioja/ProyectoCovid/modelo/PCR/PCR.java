package org.jesuitasrioja.ProyectoCovid.modelo.PCR;

import java.io.Serializable;
import java.sql.Date;

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
@Table(name = "Pcr")
public class PCR implements Serializable{
	
	private Date fecha;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Include
	private long identificador;
	private String tipo;
	private String estado;

}
