package com.appmedic.medic_app.aplication.service.ports;

import com.appmedic.medic_app.aplication.ports.in.dto.registrarEmpleadoDTO;
import com.appmedic.medic_app.infra.out.Response;

public interface empeladoServicePort {
    Response<?> registrarEmpleado(registrarEmpleadoDTO dto);
}
