package org.jesuitasrioja.ProyectoCovid.persistencia.services;

import java.util.HashSet;
import java.util.Optional;
import java.util.Random;
import java.util.Set;

import org.jesuitasrioja.ProyectoCovid.modelo.user.UserEntity;
import org.jesuitasrioja.ProyectoCovid.modelo.user.UserRole;
import org.jesuitasrioja.ProyectoCovid.persistencia.repositories.UserEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserEntityService extends BaseService<UserEntity, String, UserEntityRepository> {

	@Autowired
	@Lazy
	private PasswordEncoder passwordEncoder;
	public Optional<UserEntity> findByUserName(String username) {
		return this.repositorio.findByUsername(username);
	}
	public UserEntity nuevoUsuario(UserEntity userEntity) {

		userEntity.setId(String.valueOf(Math.abs(new Random().nextInt())));
		userEntity.setPassword(passwordEncoder.encode(userEntity.getPassword()));
		Set<UserRole> defaultRoles = new HashSet<UserRole>();
		defaultRoles.add(UserRole.USER);
		if (userEntity.getRoles() == null) {
			userEntity.setRoles(defaultRoles);
		} else if (userEntity.getRoles().size() == 0) {
			userEntity.setRoles(defaultRoles);
		}
		return this.repositorio.save(userEntity);
	}
}
