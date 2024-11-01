package com.appmedic.medic_app.aplication.ports.in.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;


/**
 * Modelo para recibir datos
 * */
public record registrarEmpleadoDTO(

        @Min(1)
        int idPersona,

        @Min(1)
        int idProfecion,

        byte[] imagen,

        @Min(1)
        int experiencia,

        String slogan

) {
}
