package com.appmedic.medic_app.aplication.service.ports;

import com.appmedic.medic_app.aplication.ports.in.dto.actualizarFormaPago;
import com.appmedic.medic_app.aplication.ports.in.dto.registrarFormaPagoDTO;
import com.appmedic.medic_app.infra.out.Response;

public interface formaPago {
    Response registrarFormaPago(registrarFormaPagoDTO dto);
    Response actualizarFormaPago(actualizarFormaPago dto);
    Response listarFormasPagos();
}
