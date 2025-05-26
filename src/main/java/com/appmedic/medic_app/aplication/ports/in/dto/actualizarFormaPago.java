package com.appmedic.medic_app.aplication.ports.in.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record actualizarFormaPago(
        @NotNull(message = "Campo ID no puede ser nulo")
        Integer idFormaPago,
        @NotNull(message = "Campo ESTADO no puede ser nulo")
        Integer estado,
        @NotBlank(message = "Campo NOMBRE no puede ser vacio / nulo")
        String nombre,
        @NotBlank(message = "Campo DESCRIPCION no puede ser vacio / nulo")
        String descripcion
) {
}
