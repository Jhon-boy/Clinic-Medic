package com.appmedic.medic_app.domain.entity.seguridad.persistence;

import com.appmedic.medic_app.domain.entity.seguridad.TSEGUSUARIO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

/**
 * Interfaz que persiste a la entidad TSEGUSUARIO
 * */
public interface TusuarioRepository extends JpaRepository<TSEGUSUARIO, Integer> {
    Optional<TSEGUSUARIO> findByUsuario(String usuario);
    List<TSEGUSUARIO> findAll();
}
