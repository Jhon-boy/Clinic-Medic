package com.appmedic.medic_app.aplication.ports.in.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record RegistrarPagoDTO(
        @NotNull(message = "Campo ID no puede ser nulo")
        Integer idFormaPago,
        @NotNull(message = "Campo IDFACTURA no puede ser nulo")
        Integer idFactura,
        @NotBlank(message = "Campo MONTO no puede ser vacio / nulo")
        String monto,
        @NotBlank(message = "Campo BENEFICIARIO no puede ser vacio / nulo")
        String beneficario
) {
}
