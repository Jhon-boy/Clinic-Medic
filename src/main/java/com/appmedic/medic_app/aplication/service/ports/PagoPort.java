package com.appmedic.medic_app.aplication.service.ports;

import com.appmedic.medic_app.aplication.ports.in.dto.ListarPagos;
import com.appmedic.medic_app.aplication.ports.in.dto.RegistrarPagoDTO;
import com.appmedic.medic_app.infra.out.Response;

public interface PagoPort {
    Response registrarPago(RegistrarPagoDTO dto);
    Response informacionPago(ListarPagos dto);
}
