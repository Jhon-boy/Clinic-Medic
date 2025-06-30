package com.appmedic.medic_app.domain.entity.personas.persistence;

import com.appmedic.medic_app.domain.entity.personas.TPERPROFECION;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Interfaz que persiste a la entidad TPERPROFECIONAL
 * */
public interface TprofecionRepository extends JpaRepository<TPERPROFECION, Integer> {
}
