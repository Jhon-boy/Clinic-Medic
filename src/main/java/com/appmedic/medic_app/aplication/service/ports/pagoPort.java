package com.appmedic.medic_app.aplication.service.ports;

import com.appmedic.medic_app.aplication.ports.in.dto.listarPagos;
import com.appmedic.medic_app.aplication.ports.in.dto.registrarPagoDTO;
import com.appmedic.medic_app.infra.out.Response;

public interface pagoPort {
    Response registrarPago(registrarPagoDTO dto);
    Response informacionPago(listarPagos dto);
}
