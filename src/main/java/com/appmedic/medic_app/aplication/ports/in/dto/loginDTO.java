package com.appmedic.medic_app.aplication.ports.in.dto;

import jakarta.validation.constraints.NotBlank;

public record loginDTO(
        @NotBlank(message = "Campo USER no puede ser vacio / nulo")
        String user,
        @NotBlank(message = "Campo PASSWORD no puede ser vacio / nulo")
        String password,
        String terminalDevice,
        String redName,
        String canal
) {
}
