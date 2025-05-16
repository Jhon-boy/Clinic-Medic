package com.appmedic.medic_app.aplication.service.ports;

import com.appmedic.medic_app.aplication.ports.in.dto.actualizarCitas;
import com.appmedic.medic_app.aplication.ports.in.dto.registrarCitasDTO;
import com.appmedic.medic_app.infra.out.Response;

public interface citaPort {
    Response registrarCita(registrarCitasDTO dto);
    Response actualizarCita(actualizarCitas dto);
    Response cancelarCita(Integer id);
    Response listarCitas(Integer tipo);
}
