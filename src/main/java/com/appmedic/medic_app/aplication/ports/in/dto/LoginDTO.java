package com.appmedic.medic_app.aplication.ports.in.dto;

import jakarta.validation.constraints.NotBlank;
/*
 * Modelo para recibir datos
 * */
public record LoginDTO(
        @NotBlank(message = "Campo USER no puede ser vacio / nulo")
        String user,
        @NotBlank(message = "Campo PASSWORD no puede ser vacio / nulo")
        String password,
        String terminalDevice,
        String redName,
        String canal
) {
}
