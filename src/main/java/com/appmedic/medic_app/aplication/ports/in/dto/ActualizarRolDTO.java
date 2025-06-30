package com.appmedic.medic_app.aplication.ports.in.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@JsonIgnoreProperties(ignoreUnknown = true)
public record ActualizarRolDTO(
        Integer idRol,

        @Size(min = 1, message = "Campo NOMBRE debe tener mas caracteres")
        @NotBlank(message = "Campo NOMBRE no puede ser vacio")
        String nombre,


        @Size(min = 5, message = "Campo DESCRIPCION debe tener mas caracteres")
        @NotBlank(message = "Campo DESCRIPCION no puede ser vacio")
        String descripcion,

        Boolean activo) {
}
