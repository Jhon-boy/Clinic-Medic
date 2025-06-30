package com.appmedic.medic_app.aplication.service.ports;

import com.appmedic.medic_app.aplication.ports.in.dto.RegistrarFacturaDTO;
import com.appmedic.medic_app.infra.out.Response;

public interface FacturaPort {
    Response registrarFactura(RegistrarFacturaDTO dto);
    Response listarFacturas(Integer tipo);
    Response informacionFactura(Integer idFactura);
}
