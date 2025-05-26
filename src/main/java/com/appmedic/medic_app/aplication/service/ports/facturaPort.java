package com.appmedic.medic_app.aplication.service.ports;

import com.appmedic.medic_app.aplication.ports.in.dto.registrarFacturaDTO;
import com.appmedic.medic_app.infra.out.Response;

public interface facturaPort {
    Response registrarFactura(registrarFacturaDTO dto);
    Response listarFacturas(Integer tipo);
    Response informacionFactura(Integer idFactura);
}
