package com.appmedic.medic_app.aplication.service.ports;

import com.appmedic.medic_app.aplication.ports.in.dto.RegistrarEmpleadoDTO;
import com.appmedic.medic_app.infra.out.Response;

public interface EmpeladoServicePort {
    Response registrarEmpleado(RegistrarEmpleadoDTO dto);
    Response listaEmpleados();
}
