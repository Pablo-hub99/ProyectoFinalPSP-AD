package org.jesuitasrioja.ProyectoCovid.controllers;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import org.jesuitasrioja.ProyectoCovid.modelo.alumno.Alumno;
import org.jesuitasrioja.ProyectoCovid.modelo.alumno.AlumnoDTO;
import org.jesuitasrioja.ProyectoCovid.modelo.alumno.AlumnoDTOConverter;
import org.jesuitasrioja.ProyectoCovid.modelo.clase.Clase;
import org.jesuitasrioja.ProyectoCovid.modelo.datosMedicos.DatosMedicos;
import org.jesuitasrioja.ProyectoCovid.modelo.profesor.Profesor;
import org.jesuitasrioja.ProyectoCovid.modelo.responsable.Responsable;
import org.jesuitasrioja.ProyectoCovid.persistencia.services.AlumnoService;
import org.jesuitasrioja.ProyectoCovid.persistencia.services.ClaseService;
import org.jesuitasrioja.ProyectoCovid.persistencia.services.ProfesorService;
import org.jesuitasrioja.ProyectoCovid.persistencia.services.ResponsableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;

@RestController
public class AlumnoController {
	@Autowired
	private AlumnoService as;
	@Autowired
	private ProfesorService ps;
	@Autowired
	private ResponsableService rs;
	@Autowired
	private ClaseService cs;
		@Autowired
	AlumnoDTOConverter alumnoDTOConverter;
		@ApiOperation(value = "Obtener todos los alumnos",
			 notes = "Mandamos los alumnos de 10 en 10 para que sea mas facil recoger los datos")
	@GetMapping("/alumnos")
	public ResponseEntity<?> allAlumnos(@PageableDefault(size = 10, page = 0) Pageable pageable) {
		Page<Alumno> pagina = as.findAll(pageable);
					Page<AlumnoDTO> paginaDTO = pagina.map(new Function<Alumno, AlumnoDTO>() {
					@Override
					public AlumnoDTO apply(Alumno a) {
						return alumnoDTOConverter.convertAlumnoToAlumnoDTO(a);
					}
				});

		return ResponseEntity.status(HttpStatus.OK).body(paginaDTO);
	}	
	@ApiOperation(value = "Alumno por identificador",
			 notes = "Informacionde un alumno expecifico")
	@GetMapping("/alumno/{id}")
	public ResponseEntity<Alumno> getAlumno(@PathVariable String id) {

		Optional<Alumno> alumnoOptional = as.findById(id);
		if (alumnoOptional.isPresent()) {
			return ResponseEntity.status(HttpStatus.OK).body(alumnoOptional.get());
		} else {
			throw new AlumnoNoEncontradoException(id);
		}
	}		
	@ApiOperation(value = "Añadir alumno",
			 notes = " añadimos un Alumno.")
	@PostMapping("/alumno")
	public String postAlumno(@RequestBody Alumno nuevoAlumno) {
		return as.save(nuevoAlumno).toString();
	}
	
	@ApiOperation(value = "Modificar alumno",
			 notes = "Modificamos un Alumno.")
	@PutMapping("/alumno")
	public String putAlumno(@RequestBody Alumno editadoAlumno) {
		return as.edit(editadoAlumno).toString();
	}
	
	@ApiOperation(value = "Borrar un alumno",
			 notes = " borrar un Alumno específico.")
	@DeleteMapping("/alumno/{id}")
	public String deleteAlumno(@PathVariable String id) {
		as.deleteById(id);
		return "OK";
	}
	@ApiOperation(value = "Añadir un Responsable a un Alumno",
			 notes = "añadir un Responsable a un Alumno.")
	@PostMapping("/alumno/{idAlumno}/responsable")
	public ResponseEntity<String> aniadirResponsableAlumno(@PathVariable String idAlumno, @RequestBody Responsable nuevoResponsable) {
		Optional<Alumno> alumnoOptional = as.findById(idAlumno);
		if(alumnoOptional.isPresent()) {
			Alumno alumnoModificado = alumnoOptional.get();
			List<Responsable> lista = alumnoModificado.getResponsables();
			lista.add(nuevoResponsable);
			alumnoModificado.setResponsables(lista);
			return ResponseEntity.status(HttpStatus.OK).body(as.save(alumnoModificado).toString());
		} else {
			throw new AlumnoNoEncontradoException(idAlumno);
		}
	}
	
	@ApiOperation(value = "Eliminar un Responsable a un Alumno",
			 notes = "Borramos un responsable pero sin eliminarlo de la BD solo lo desvincula del alumno")
	@DeleteMapping("/alumno/{idAlumno}/responsable/{idResponsable}")
	public ResponseEntity<String> borrarResponsableAlumno(@PathVariable String idAlumno, @PathVariable Long idResponsable) {
		
		Optional<Alumno> alumnoOptional = as.findById(idAlumno);
		if(alumnoOptional.isPresent()) {
			Alumno alumnoModificado = alumnoOptional.get();
			List<Responsable> lista = alumnoModificado.getResponsables();
			Optional<Responsable> responsableBorrar = rs.findById(idResponsable);
			lista.remove(responsableBorrar.get());
			alumnoModificado.setResponsables(lista);
			return ResponseEntity.status(HttpStatus.OK).body(as.save(alumnoModificado).toString());
		} else {
			throw new AlumnoNoEncontradoException(idAlumno);
		}
	}
	@ApiOperation(value = "Asociar un profesor a un Alumno",
			 notes = "Asociar un Profesor a un Alumno.")
	@PutMapping("/alumno/{idAlumno}/profesor/{idProfesor}")
	public ResponseEntity<String> asociarProfesorAlumno(@PathVariable String idAlumno, @PathVariable String idProfesor) {
		Optional<Alumno> alumnoOptional = as.findById(idAlumno);
		Optional<Profesor> profesorOptional = ps.findById(idProfesor);
		if (alumnoOptional.isPresent() && profesorOptional.isPresent()) {
			Alumno alumnoEditado = alumnoOptional.get();
			alumnoEditado.setTutor(profesorOptional.get());
			return ResponseEntity.status(HttpStatus.OK).body(as.edit(alumnoEditado).toString());
		} else {
			throw new AlumnoNoEncontradoException(idAlumno, idProfesor);
		}
	}
	

	

	@ApiOperation(value = "Añadir los datos medicos de un Alumno",
			 notes = " añadir los datos medicos de un Alumno.")
	@PostMapping("/alumno/{idAlumno}/datosmedicos")
	public ResponseEntity<String> aniadirDatosMedicosAlumno(@PathVariable String idAlumno, @RequestBody DatosMedicos nuevosDatosmedicos) {
		
		Optional<Alumno> alumnoOptional = as.findById(idAlumno);
		if(alumnoOptional.isPresent()) {
			Alumno alumnoModificado = alumnoOptional.get();
			alumnoModificado.setDatosMedicos(nuevosDatosmedicos);
			return ResponseEntity.status(HttpStatus.OK).body(as.save(alumnoModificado).toString());
		} else {
			throw new AlumnoNoEncontradoException(idAlumno);
		}
	}
	@ApiOperation(value = "Modificar los datos medicos de un Alumno",
			 notes = " modificar los datos medicos de un Alumno.")
	@PutMapping("/alumno/{idAlumno}/datosmedicos")
	public ResponseEntity<String> modificarDatosMedicosAlumno(@PathVariable String idAlumno, @RequestBody DatosMedicos nuevosDatosmedicos) {
		Optional<Alumno> alumnoOptional = as.findById(idAlumno);
		if(alumnoOptional.isPresent()) {
			Alumno alumnoModificado = alumnoOptional.get();
			alumnoModificado.setDatosMedicos(nuevosDatosmedicos);
			return ResponseEntity.status(HttpStatus.OK).body(as.edit(alumnoModificado).toString());
		} else {
			throw new AlumnoNoEncontradoException(idAlumno);
		}
	}
	@ApiOperation(value = "Ver todos los datos medicos de un Alumno",
			 notes = " todos los datos medicos de un Alumno.")
	@GetMapping("/alumno/{idAlumno}/datosmedicos")
	public ResponseEntity<DatosMedicos> verDatosMedicosAlumno(@PathVariable String idAlumno) {
		Optional<Alumno> alumnoOptional = as.findById(idAlumno);
		if(alumnoOptional.isPresent()) {
			Alumno alumnoModificado = alumnoOptional.get();
			DatosMedicos datos = alumnoModificado.getDatosMedicos();
			
			return ResponseEntity.status(HttpStatus.OK).body(datos);
		} else {
			throw new AlumnoNoEncontradoException(idAlumno);
		}
	}
	
	@ApiOperation(value = "Asociar una Clase a un Alumno",
			 notes = "asociar una Clase a un Alumno.")
	@PutMapping("/alumno/{idAlumno}/clase/{idClase}")
	public ResponseEntity<String> asociarClaseAlumno(@PathVariable String idAlumno, @PathVariable String idClase) {
		Optional<Alumno> alumnoOptional = as.findById(idAlumno);
		Optional<Clase> claseOptional = cs.findById(idClase);
		
		if (alumnoOptional.isPresent() && claseOptional.isPresent()) {
			Alumno alumnoEditado = alumnoOptional.get();
			alumnoEditado.setClase(claseOptional.get());
			return ResponseEntity.status(HttpStatus.OK).body(as.edit(alumnoEditado).toString());
		} else {
			throw new AlumnoNoEncontradoException(idAlumno, idClase);
		}
	}
		
		

}
