package com.appmedic.medic_app.config.exceptions;

import com.appmedic.medic_app.infra.out.Response;
import com.appmedic.medic_app.util.Utils;
import com.appmedic.medic_app.util.Const;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * Manejador de excepciones global de la aplicacion
 *
 * @author John c
 * @version 1.0
 * */
@ControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Handle Excepciones generales en Ejecucion
     * */
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<Response> handleRuntimeException(RuntimeException ex) {
        Response response = Utils.generateBadResponseDefault();
        response.setMessage(Const.MENSAJE_ERROR);
        response.setData(ex.getMessage());
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /**
     * Handle genérico de cualquier excepción no contemplada previamente
     * */
    public ResponseEntity<Response> handleGlobalException(Exception ex){
        Response response = Utils.generateBadResponseDefault();
        response.setMessage(Const.MENSAJE_ERROR);
        response.setData(ex.getMessage());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

}
