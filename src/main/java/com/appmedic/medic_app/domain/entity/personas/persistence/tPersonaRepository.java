package com.appmedic.medic_app.domain.entity.personas.persistence;

import com.appmedic.medic_app.domain.entity.personas.TPERPERSONA;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Interfaz que persistente a la entidad TPERPERSONA
 * */
public interface tPersonaRepository extends JpaRepository<TPERPERSONA, Integer> {
}
