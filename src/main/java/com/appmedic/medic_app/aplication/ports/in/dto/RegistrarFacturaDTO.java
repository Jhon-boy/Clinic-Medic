package com.appmedic.medic_app.aplication.ports.in.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

public record RegistrarFacturaDTO(

        Integer descuento,
        @Min(1)
        Integer idFormaPago,
        Integer aplicaIva,
        @NotBlank(message = "Campo MONTOORIGINAL no puede ser vacio / nulo")
        String montoOriginal,
        String montoTotal,
        @NotBlank(message = "Campo DESCRIPCION no puede ser vacio / nulo")
        String descripcion

) {
}
