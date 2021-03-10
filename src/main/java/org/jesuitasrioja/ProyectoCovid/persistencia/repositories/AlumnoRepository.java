package org.jesuitasrioja.ProyectoCovid.persistencia.repositories;

import org.jesuitasrioja.ProyectoCovid.modelo.alumno.Alumno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AlumnoRepository extends JpaRepository<Alumno, String>{

}
