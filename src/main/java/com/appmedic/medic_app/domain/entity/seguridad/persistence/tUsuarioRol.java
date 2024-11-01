package com.appmedic.medic_app.domain.entity.seguridad.persistence;

import com.appmedic.medic_app.domain.entity.seguridad.TSEGUSUARIO;
import com.appmedic.medic_app.domain.entity.seguridad.TSEGUSUARIOROL;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Interfaz que persiste a la entidad TSEGROL
 * */
public interface tUsuarioRol extends JpaRepository<TSEGUSUARIOROL, Integer> {
    List<TSEGUSUARIO> findByIdUsuario(TSEGUSUARIO idusuario);
}
