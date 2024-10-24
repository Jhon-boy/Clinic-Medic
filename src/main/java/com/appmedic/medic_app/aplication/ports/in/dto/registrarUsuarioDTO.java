package com.appmedic.medic_app.aplication.ports.in.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/*
* Modelo para recibir datos
* */
public record registrarUsuarioDTO(
        @NotNull(message = "Campo IDROL no puede ser nulo ")
        @Min(1)
        int idRol,

        @NotNull(message = "Campo IDROL no puede ser nulo ")
        @Min(1)
        int idPersona,

        @NotNull(message = "Campo USUARIO no puede ser nulo")
        @Size(min = 5, message = "Campo USUARIO debe tener mas caracteres")
        String usuario,

        @NotNull(message = "Campo CLAVE no puede ser nulo")
        @Size(min = 10, message = "Campo CLAVE debe tener mas caracteres")
        String clave,
        boolean activo
) {
}
