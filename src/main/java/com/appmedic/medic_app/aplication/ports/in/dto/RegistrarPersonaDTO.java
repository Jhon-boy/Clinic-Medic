package com.appmedic.medic_app.aplication.ports.in.dto;

import jakarta.validation.constraints.*;

import java.util.Date;
/**
 * Modelo para recibir datos usados para el registro del Persona y Usuario
 * */
public record RegistrarPersonaDTO(

        @NotBlank(message = "Campo NOMBRE no puede ser nulo")
        @NotBlank(message = "Campo NOMBRE no puede ser vacio")
        @Size(min = 3, message = "Campo NOMBRE debe tener mas caracteres")
        String nombre,


        @Size(min = 3, message = "Campo APELLIDO debe tener mas caracteres")
        @NotBlank(message = "Campo NOMBRE no puede ser vacio")
        String apellido,

        Date fnacimiento,

        @NotBlank(message = "Campo IDENTIFICACION no puede ser nulo")
        @Size(min = 10, message = "Campo TELEFONO debe tener mas caracteres")
        String identificacion,

        @Email(message = "campo EMAIL formato invalido")
        String email,

        @Size(min = 9, message = "Campo TELEFONO debe tener mas caracteres")
        String telefono,

        @Min(1)
        int idRol,

        @Size(min = 5, message = "Campo USUARIO debe tener mas caracteres")
        String usuario,

        @Size(min = 10, message = "Campo CONTRASENIA debe tener mas caracteres")
        String password

) {
}
