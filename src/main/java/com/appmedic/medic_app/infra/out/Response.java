package com.appmedic.medic_app.infra.out;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
/**
 * Clase abstracta que usa como respuesta a cualquier peticion HTTP
 * @author John C
 * @version 1.0
 * */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Response<T> {

    @JsonProperty("code")
    private String code;

    @JsonProperty("message")
    private String message;

    @JsonProperty("data")
    private T data;
}
