package com.appmedic.medic_app.aplication.ports.in.dto;


import jakarta.validation.constraints.*;
/*
* Modelo para recibir datos
* */
public record registrarUsuarioDTO(
        @Min(1)
        int idRol,

        @NotNull(message = "Campo IDROL no puede ser nulo ")
        @Min(1)
        int idPersona,

        @Size(min = 5, message = "Campo USUARIO debe tener mas caracteres")
        @NotBlank(message = "Campo USUARIO no puede ser vacio")
        String usuario,


        @Size(min = 10, message = "Campo CLAVE debe tener mas caracteres")
        @NotBlank(message = "Campo ACTIVO no puede ser vacio")
        String clave,

        boolean activo
) {
}
