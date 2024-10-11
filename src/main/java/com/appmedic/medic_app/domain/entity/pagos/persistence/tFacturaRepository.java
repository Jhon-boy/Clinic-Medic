package com.appmedic.medic_app.domain.entity.pagos.persistence;

import com.appmedic.medic_app.domain.entity.pagos.TPAGFACTURA;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Interfaz que persite a la entidad TPAGFACTURA
 * */
public interface tFacturaRepository extends JpaRepository<TPAGFACTURA, Integer> {
}
