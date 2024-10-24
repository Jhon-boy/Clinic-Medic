package com.appmedic.medic_app.aplication.service.ports;

import com.appmedic.medic_app.aplication.ports.in.dto.registrarRolDTO;
import com.appmedic.medic_app.infra.out.Response;

public interface rolServicePort {
    Response<?> registrarRol(registrarRolDTO dto);
    Response<?> findById(int id);
}
