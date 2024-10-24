package com.appmedic.medic_app.aplication.ports.in.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Modelo para recibir datos
 * */
public record registrarEmpleadoDTO(

        @NotNull(message = "Campo IDPERSONA no puede ser nulo ")
        @Min(1)
        int idPersona,

        @NotNull(message = "Campo IDPROFECION no puede ser nulo ")
        @Min(1)
        int idProfecion,

        byte[] imagen

) {
}
