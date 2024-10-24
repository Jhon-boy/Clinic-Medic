package com.appmedic.medic_app.aplication.ports.in.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;
/**
 * Modelo para recibir datos usados para el registro del Persona y Usuario
 * */
public record registrarPersonaDTO(

        @NotNull(message = "Campo NOMBRE no puede ser nulo")
        @Size(min = 3, message = "Campo NOMBRE debe tener mas caracteres")
        String nombre,

        @NotNull(message = "Campo APELLIDO no puede ser nulo")
        @Size(min = 3, message = "Campo APELLIDO debe tener mas caracteres")
        String apellido,

        @NotNull(message = "Campo FNACIMIENTO no puede ser nulo")
        Date fnacimiento,

        @NotNull(message = "Campo IDENTIFICACION no puede ser nulo")
        @Size(min = 10, message = "Campo TELEFONO debe tener mas caracteres")
        String identificacion,

        @Email(message = "campo EMAIL formato invalido")
        String email,
        @Size(min = 9, message = "Campo TELEFONO debe tener mas caracteres")
        String telefono,

        @NotNull(message = "Campo IDROL no puede ser nulo ")
        @Min(1)
        int idRol,
        @NotNull(message = "Campo USUARIO no puede ser nulo")
        @Size(min = 5, message = "Campo USUARIO debe tener mas caracteres")
        String usuario,

        @NotNull(message = "Campo CONTRASENIA no puede ser nulo")
        @Size(min = 10, message = "Campo CONTRASENIA debe tener mas caracteres")
        String password

) {
}
