package com.appmedic.medic_app.aplication.service.ports;

import com.appmedic.medic_app.aplication.ports.in.dto.ActualizarPersonaDTO;
import com.appmedic.medic_app.aplication.ports.in.dto.RegistrarPersonaDTO;
import com.appmedic.medic_app.domain.entity.personas.TPERPERSONA;
import com.appmedic.medic_app.infra.out.Response;

public interface UsuarioServicePort {

    Response registrarUsuario(RegistrarPersonaDTO dto, TPERPERSONA persona);
    Response obtenerUsuarioByUser(String username);
    Response getAllUsers(String estado);
    Response updateUser(ActualizarPersonaDTO dto, TPERPERSONA persona);
}
