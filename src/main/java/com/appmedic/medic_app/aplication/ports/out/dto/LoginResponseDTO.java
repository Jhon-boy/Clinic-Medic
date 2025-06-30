package com.appmedic.medic_app.aplication.ports.out.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

/**
 * Clase que actua como DTO de respuesta a un Logeo exitoso
 *
 * @author Jhon
 * @version 1.0
 * */
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class LoginResponseDTO {
    @JsonProperty("usuario")
    String usuario;
    @JsonProperty("email")
    String email;
    @JsonProperty("canal")
    String canal;
    @JsonProperty("nombreCompleto")
    String nombres;
    @JsonProperty("idUsuario")
    int idUsuario;
    @JsonProperty("idRol")
    int idRol;
    @JsonProperty("token")
    String token;
}
