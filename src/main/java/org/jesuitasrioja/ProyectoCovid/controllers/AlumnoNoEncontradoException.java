package org.jesuitasrioja.ProyectoCovid.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class AlumnoNoEncontradoException extends RuntimeException{
	public AlumnoNoEncontradoException(String idAlumno) {
		super("alumno with " + idAlumno + " can not be retrieved");
	}
	public AlumnoNoEncontradoException(String idAlumno, String idProfesor) {
		super("alumno y profesor with " + idAlumno + " and " + idProfesor + " can not be retrieved");
	}
}
