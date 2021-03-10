package org.jesuitasrioja.ProyectoCovid.controllers;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import org.jesuitasrioja.ProyectoCovid.modelo.alumno.Alumno;
import org.jesuitasrioja.ProyectoCovid.modelo.user.GetUserDTO;
import org.jesuitasrioja.ProyectoCovid.modelo.user.UserDTOConverter;
import org.jesuitasrioja.ProyectoCovid.modelo.user.UserEntity;
import org.jesuitasrioja.ProyectoCovid.persistencia.services.UserEntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
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
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import io.swagger.annotations.ApiOperation;

@RestController
public class UserEntityController {
	@Autowired
	private UserEntityService ues;
	
	@Autowired
	UserDTOConverter userDTOConverter;
	@PostMapping("/usuario")
	public ResponseEntity<UserEntity> nuevoUsuario(@RequestBody UserEntity newUser) {
		try {
			if (!ues.findByUserName(newUser.getUsername()).isPresent()) {
				return ResponseEntity.status(HttpStatus.CREATED).body(ues.nuevoUsuario(newUser));
			} else {
				return ResponseEntity.status(HttpStatus.CONFLICT).build();
			}
		} catch (DataIntegrityViolationException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}
	
	@GetMapping("/usuario/{id}")
	public ResponseEntity<UserEntity> getUser(@PathVariable String id) {

		Optional<UserEntity> userOptional = ues.findById(id);
		if (userOptional.isPresent()) {
			return ResponseEntity.status(HttpStatus.OK).body(userOptional.get());
		} else {
			throw new AlumnoNoEncontradoException(id);
		}
	}
	
	@DeleteMapping("/usuario/{id}")
	public String deleteUsuario(@PathVariable String id) {
		ues.deleteById(id);
		return "OK";
	}
	@GetMapping("/usuarios")
	public ResponseEntity<?> getUsuarios(@PageableDefault(size = 10, page = 0) Pageable pageable) {
		try {
			Page<UserEntity> pagina = ues.findAll(pageable);
			Page<GetUserDTO> paginaDTO = pagina.map(new Function<UserEntity, GetUserDTO>() {
				@Override
				public GetUserDTO apply(UserEntity u) {
					return userDTOConverter.convertUserEntityToGetUserDto(u);
				}
			});
			return ResponseEntity.status(HttpStatus.OK).body(paginaDTO);
		} catch (DataIntegrityViolationException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}
	@PutMapping("/usuario")
	public String putUsuario(@RequestBody UserEntity editadoUsuario) {
		return ues.edit(editadoUsuario).toString();
	}

}
