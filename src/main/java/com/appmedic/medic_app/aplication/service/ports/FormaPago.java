package com.appmedic.medic_app.aplication.service.ports;

import com.appmedic.medic_app.aplication.ports.in.dto.ActualizarFormaPago;
import com.appmedic.medic_app.aplication.ports.in.dto.RegistrarFormaPagoDTO;
import com.appmedic.medic_app.infra.out.Response;

public interface FormaPago {
    Response registrarFormaPago(RegistrarFormaPagoDTO dto);
    Response actualizarFormaPago(ActualizarFormaPago dto);
    Response listarFormasPagos();
}
