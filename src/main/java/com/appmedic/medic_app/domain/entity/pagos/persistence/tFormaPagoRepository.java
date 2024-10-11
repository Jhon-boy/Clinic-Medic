package com.appmedic.medic_app.domain.entity.pagos.persistence;

import com.appmedic.medic_app.domain.entity.pagos.TPAGFORMAPAGO;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Interfaz que persiste a la entidad TPAGFORMAPAGO
 * */
public interface tFormaPagoRepository extends JpaRepository<TPAGFORMAPAGO, Integer> {
}
