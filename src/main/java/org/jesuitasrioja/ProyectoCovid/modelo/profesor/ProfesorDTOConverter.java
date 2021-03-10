package org.jesuitasrioja.ProyectoCovid.modelo.profesor;

import org.jesuitasrioja.ProyectoCovid.modelo.responsable.Responsable;
import org.jesuitasrioja.ProyectoCovid.modelo.responsable.ResponsableDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

public class ProfesorDTOConverter {
	@Autowired
	private final ModelMapper modelMapper;

	public ProfesorDTOConverter(ModelMapper modelMapper) {
		this.modelMapper = modelMapper;
	}
	
	public ResponsableDTO convertResponableToResponsableDTO(Responsable responsable) {
		
		ResponsableDTO dto = modelMapper.map(responsable, ResponsableDTO.class);
		
		return dto;
	}
}
