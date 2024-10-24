package com.appmedic.medic_app.domain.entity.personas.persistence;

import com.appmedic.medic_app.domain.entity.personas.TPERPROFECION;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Interfaz que persistente a la entidad TPERESPECIALIDAD
 * */
public interface tEspecialidadRepository extends JpaRepository<TPERPROFECION, Integer> {
}
