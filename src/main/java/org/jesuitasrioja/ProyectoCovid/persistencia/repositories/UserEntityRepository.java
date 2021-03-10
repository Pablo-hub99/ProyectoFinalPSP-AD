package org.jesuitasrioja.ProyectoCovid.persistencia.repositories;

import java.util.Optional;

import org.jesuitasrioja.ProyectoCovid.modelo.user.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserEntityRepository extends JpaRepository<UserEntity, String>{
	Optional<UserEntity> findByUsername(String username);
}
