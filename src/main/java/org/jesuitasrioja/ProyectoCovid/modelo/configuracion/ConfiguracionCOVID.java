package org.jesuitasrioja.ProyectoCovid.modelo.configuracion;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Configuracion")
public class ConfiguracionCOVID implements Serializable {

	@Id
	private Integer diasCuarentena;

}
