package com.appmedic.medic_app.domain.entity.seguridad.persistence;

import com.appmedic.medic_app.domain.entity.seguridad.TSEGUSUARIO;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Interfaz que persiste a la entidad TSEGUSUARIO
 * */
public interface tUsuarioRepository extends JpaRepository<TSEGUSUARIO, Integer> {
}
