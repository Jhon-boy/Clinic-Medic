package com.appmedic.medic_app.domain.Exceptions;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Maneja las excepcciones de la aplicacion
 * */
@EqualsAndHashCode(callSuper = true)
@Data
public class ExceptionGeneric extends  RuntimeException {

    public ExceptionGeneric(String mensajeError) {
        super(mensajeError);
    }

    public ExceptionGeneric(String mensajeError, Throwable e) {
        super(mensajeError, e);
    }
}
