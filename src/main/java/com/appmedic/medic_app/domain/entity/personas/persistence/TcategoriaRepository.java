package com.appmedic.medic_app.domain.entity.personas.persistence;

import com.appmedic.medic_app.domain.entity.personas.TPEREMPCATEGORIA;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Intefaz que persiste a la entidad CATEGORIA
 * */
public interface TcategoriaRepository extends JpaRepository<TPEREMPCATEGORIA, Integer> {
}
