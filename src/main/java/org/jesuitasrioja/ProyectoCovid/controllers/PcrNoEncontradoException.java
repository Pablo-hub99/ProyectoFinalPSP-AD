package org.jesuitasrioja.ProyectoCovid.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class PcrNoEncontradoException extends RuntimeException{
	public PcrNoEncontradoException(Long idPcr) {
		super("pcr with " + idPcr + " can not be retrieved");
	}
}
