package com.appmedic.medic_app.aplication.service.ports;

import com.appmedic.medic_app.aplication.ports.in.dto.registrarPersonaDTO;
import com.appmedic.medic_app.infra.out.Response;

public interface personaServicePort {
    Response<?> registrarPersona(registrarPersonaDTO dto);
}
