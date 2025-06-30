package com.appmedic.medic_app.aplication.service.ports;

import com.appmedic.medic_app.aplication.ports.in.dto.ActualizarCitas;
import com.appmedic.medic_app.aplication.ports.in.dto.RegistrarCitasDTO;
import com.appmedic.medic_app.infra.out.Response;

public interface CitaPort {
    Response registrarCita(RegistrarCitasDTO dto);
    Response actualizarCita(ActualizarCitas dto);
    Response cancelarCita(Integer id);
    Response listarCitas(Integer tipo);
}
