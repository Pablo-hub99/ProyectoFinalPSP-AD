package org.jesuitasrioja.ProyectoCovid.persistencia.repositories;

import org.jesuitasrioja.ProyectoCovid.modelo.PCR.PCR;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PcrRepository extends JpaRepository<PCR, Long>{

}
