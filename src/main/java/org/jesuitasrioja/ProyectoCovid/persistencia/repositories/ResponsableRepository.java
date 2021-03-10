package org.jesuitasrioja.ProyectoCovid.persistencia.repositories;

import org.jesuitasrioja.ProyectoCovid.modelo.responsable.Responsable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ResponsableRepository extends JpaRepository<Responsable, Long>{

}
