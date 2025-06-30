package com.appmedic.medic_app.domain.entity.personas.persistence;

import com.appmedic.medic_app.domain.entity.personas.TPEREMPLEADO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TempleadoRepository extends JpaRepository<TPEREMPLEADO, Integer> {
    Optional<TPEREMPLEADO> findByPersonaIdentificacion(String identificacion);
}
