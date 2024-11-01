package com.appmedic.medic_app.domain.entity.personas.persistence;

import com.appmedic.medic_app.domain.entity.personas.TPERPERSONA;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

/**
 * Interfaz que persistente a la entidad TPERPERSONA
 * */
public interface tPersonaRepository extends JpaRepository<TPERPERSONA, Integer> {
    Optional<TPERPERSONA> findByIdentificacion(String identificacion);
}
