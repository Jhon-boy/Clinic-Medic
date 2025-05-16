package com.appmedic.medic_app.aplication.ports.in.dto;

import jakarta.validation.constraints.NotBlank;

public record actualizarFormaPago(
        Integer idFormaPago,
        Integer estado,
        @NotBlank(message = "Campo NOMBRE no puede ser vacio / nulo")
        String nombre,
        @NotBlank(message = "Campo DESCRIPCION no puede ser vacio / nulo")
        String descripcion
) {
}
