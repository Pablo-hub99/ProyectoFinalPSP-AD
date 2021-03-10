package org.jesuitasrioja.ProyectoCovid.modelo.responsable;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

public class ResponsableDTOConverter {
	@Autowired
	private final ModelMapper modelMapper;

	public ResponsableDTOConverter(ModelMapper modelMapper) {
		this.modelMapper = modelMapper;
	}
	
	public ResponsableDTO convertResponsableToResponsableDTO(Responsable responsable) {
		
		ResponsableDTO dto = modelMapper.map(responsable, ResponsableDTO.class);
		
		return dto;
	}
}
