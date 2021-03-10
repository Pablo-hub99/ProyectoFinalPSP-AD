package org.jesuitasrioja.ProyectoCovid.persistencia.repositories;

import org.jesuitasrioja.ProyectoCovid.modelo.clase.Clase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClaseRepository extends JpaRepository<Clase, String>{

}
