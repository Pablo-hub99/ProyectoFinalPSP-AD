package org.jesuitasrioja.ProyectoCovid.persistencia.services;

import org.jesuitasrioja.ProyectoCovid.modelo.PCR.PCR;
import org.jesuitasrioja.ProyectoCovid.persistencia.repositories.PcrRepository;
import org.springframework.stereotype.Service;

@Service
public class PcrService extends BaseService<PCR, Long, PcrRepository>{

}
