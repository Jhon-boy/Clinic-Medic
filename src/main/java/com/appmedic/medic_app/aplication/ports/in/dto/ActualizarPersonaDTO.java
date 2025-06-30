package com.appmedic.medic_app.aplication.ports.in.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.Date;

public record ActualizarPersonaDTO(
        @NotBlank(message = "Campo NOMBRE no puede ser nulo")
        @NotBlank(message = "Campo NOMBRE no puede ser vacio")
        @Size(min = 3, message = "Campo NOMBRE debe tener mas caracteres")
        String nombre,


        @Size(min = 3, message = "Campo APELLIDO debe tener mas caracteres")
        @NotBlank(message = "Campo NOMBRE no puede ser vacio")
        String apellido,

        Date fnacimiento,


        @Email(message = "campo EMAIL formato invalido")
        String email,

        @Size(min = 9, message = "Campo TELEFONO debe tener mas caracteres")
        String telefono,

        @Size(min = 9, message = "Campo IDENTIFICACION debe tener mas caracteres")
        String identificacion,

        @Min(1)
        int idRol,

        Integer activo,

        @Size(min = 5, message = "Campo USUARIO debe tener mas caracteres")
        String usuario,
        @Size(min = 10, message = "Campo CONTRASENIA debe tener mas caracteres")
        String password) {
}
