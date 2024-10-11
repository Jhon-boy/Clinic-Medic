package com.appmedic.medic_app.util;

import lombok.Getter;

/**
 * Enumerados de manejo de errores
 * */
@Getter
public enum Errores {

    GENERAL(_CONST.COD_ERROR, _CONST.MENSAJE_ERROR),
    PATH_NOTFOUND(_CONST.COD_ERROR, "PATH NO ENCONTRADO");
    private final String cod, message;

    Errores(String cod, String msge){
    this.cod = cod;
    this.message = msge;
    }

}
