package com.appmedic.medic_app.domain.entity.citas.persistence;

import com.appmedic.medic_app.domain.entity.citas.TCITCITAS;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Interfaz de persistencia para la entidad TCITCITAS
 * */
public interface TcitasRepository extends JpaRepository<TCITCITAS, Integer> {
}
