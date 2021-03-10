package org.jesuitasrioja.ProyectoCovid.modelo.alumno;


import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AlumnoDTOConverter {
	@Autowired
	private final ModelMapper modelMapper;

	public AlumnoDTOConverter(ModelMapper modelMapper) {
		this.modelMapper = modelMapper;
	}	
	public AlumnoDTO convertAlumnoToAlumnoDTO(Alumno alumno) {
		AlumnoDTO dto = modelMapper.map(alumno, AlumnoDTO.class);
		return dto;
	}
}
