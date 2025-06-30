package com.appmedic.medic_app.aplication.service.ports;

import com.appmedic.medic_app.aplication.ports.in.dto.ActualizarPersonaDTO;
import com.appmedic.medic_app.aplication.ports.in.dto.RegistrarPersonaDTO;
import com.appmedic.medic_app.infra.out.Response;

public interface PersonaServicePort {
    Response registrarPersona(RegistrarPersonaDTO dto);
    Response updateInformation(ActualizarPersonaDTO dto);
}
