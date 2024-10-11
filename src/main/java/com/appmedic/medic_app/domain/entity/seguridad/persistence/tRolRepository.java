package com.appmedic.medic_app.domain.entity.seguridad.persistence;

import com.appmedic.medic_app.domain.entity.seguridad.TSEGROL;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Interfaz que persiste a la entidad TSEGROL
 * */
public interface tRolRepository extends JpaRepository<TSEGROL, Integer> {
}
