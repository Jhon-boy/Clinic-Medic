package com.appmedic.medic_app.aplication.service.ports;

import com.appmedic.medic_app.aplication.ports.in.dto.loginDTO;
import com.appmedic.medic_app.aplication.ports.in.dto.registrarPersonaDTO;
import com.appmedic.medic_app.domain.entity.personas.TPERPERSONA;
import com.appmedic.medic_app.infra.out.Response;

public interface usuarioServicePort {

    Response registrarUsuario(registrarPersonaDTO dto,  TPERPERSONA persona);
    Response obtenerUsuarioByUser(String username);
    Response getAllUsers(String estado);
}
