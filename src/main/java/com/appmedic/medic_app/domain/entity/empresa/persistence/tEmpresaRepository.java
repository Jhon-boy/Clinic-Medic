package com.appmedic.medic_app.domain.entity.empresa.persistence;

import com.appmedic.medic_app.domain.entity.empresa.TEMPEMPRESA;
import org.springframework.data.jpa.repository.JpaRepository;

/*
* Interfaz que persiste a la entidad de TEMPEMPRESA
* */
public interface tEmpresaRepository extends JpaRepository<TEMPEMPRESA, String> {
}
