package com.appmedic.medic_app.domain.entity.personas.persistence;

import com.appmedic.medic_app.domain.entity.personas.TPERPERSONA;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Interfaz que persistente a la entidad TPERPERSONA
 * */
public interface TpersonaRepository extends JpaRepository<TPERPERSONA, Integer> {
    Optional<TPERPERSONA> findByIdentificacion(String identificacion);
}
