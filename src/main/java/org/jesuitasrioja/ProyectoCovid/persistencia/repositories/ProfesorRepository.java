package org.jesuitasrioja.ProyectoCovid.persistencia.repositories;

import org.jesuitasrioja.ProyectoCovid.modelo.profesor.Profesor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfesorRepository extends JpaRepository<Profesor, String>{

}
