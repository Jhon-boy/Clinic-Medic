package com.appmedic.medic_app.aplication.ports.in.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record registrarProfecionDTO (

        @Size(min = 1, message = "Campo NOMBRE debe tener mas caracteres")
        @NotBlank(message = "Campo NOMBRE no puede ser vacio")
        String nombre,


        @Size(min = 5, message = "Campo DESCRIPCION debe tener mas caracteres")
        @NotBlank(message = "Campo DESCRIPCION no puede ser vacio")
        String descripcion
){ }
