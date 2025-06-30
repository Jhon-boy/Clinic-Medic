package com.appmedic.medic_app.domain.entity.pagos.persistence;

import com.appmedic.medic_app.domain.entity.pagos.TPAGPAGO;
import org.springframework.data.jpa.repository.JpaRepository;


public interface TpagoRepository extends JpaRepository<TPAGPAGO, Integer> {
}
