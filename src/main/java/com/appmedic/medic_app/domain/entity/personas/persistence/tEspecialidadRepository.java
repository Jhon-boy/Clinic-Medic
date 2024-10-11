package com.appmedic.medic_app.domain.entity.personas.persistence;

import com.appmedic.medic_app.domain.entity.personas.TPERESPECIALIDAD;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Interfaz que persistente a la entidad TPERESPECIALIDAD
 * */
public interface tEspecialidadRepository extends JpaRepository<TPERESPECIALIDAD, Integer> {
}
