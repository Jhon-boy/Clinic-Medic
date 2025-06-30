package com.appmedic.medic_app.aplication.service.ports;

import com.appmedic.medic_app.aplication.ports.in.dto.ActualizarRolDTO;
import com.appmedic.medic_app.aplication.ports.in.dto.RegistrarRolDTO;
import com.appmedic.medic_app.infra.out.Response;

public interface RolServicePort {
    Response registrarRol(RegistrarRolDTO dto);
    Response findById(int id);
    Response updateRol(ActualizarRolDTO dto);
    Response listRols();
}
