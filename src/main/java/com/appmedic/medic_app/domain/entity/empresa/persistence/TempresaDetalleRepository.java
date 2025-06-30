package com.appmedic.medic_app.domain.entity.empresa.persistence;

import com.appmedic.medic_app.domain.entity.empresa.TEMPEMPRESADETALLE;
import org.springframework.data.jpa.repository.JpaRepository;

/*
* Interfaz que persite a la entidad TEMPEMPRESADETALLE
* */
public interface TempresaDetalleRepository extends JpaRepository<TEMPEMPRESADETALLE, Integer> {
}
