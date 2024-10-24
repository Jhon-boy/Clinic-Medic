package com.appmedic.medic_app.aplication.ports.in.dto;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
/**
 * Modelo de atributos a enviar/recibir
 * */
@JsonIgnoreProperties(ignoreUnknown = true)
public record registrarRolDTO(
        @NotNull(message = "Campo NOMBRE no puede ser nulo")
        @Size(min = 1, message = "Campo NOMBRE debe tener mas caracteres")
        String nombre,

        @NotNull(message = "Campo DESCRIPCION no puede ser nulo")
        @Size(min = 5, message = "Campo DESCRIPCION debe tener mas caracteres")
        String descripcion,

        boolean activo
) {
}
