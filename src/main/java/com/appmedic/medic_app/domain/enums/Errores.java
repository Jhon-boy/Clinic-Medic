package com.appmedic.medic_app.domain.enums;

import com.appmedic.medic_app.util._CONST;
import lombok.Getter;

/**
 * Enumerados de manejo de errores
 * @author Jhon
 * @version 1.0
 * */
@Getter
public enum Errores {

    GENERAL(_CONST.COD_ERROR, _CONST.MENSAJE_ERROR),
    PATH_NOTFOUND(_CONST.COD_ERROR, "PATH NO ENCONTRADO"),
    DATA_WRONG(_CONST.COD_ERROR, "INFORMACION INCONSISTENTE"),
    RESOURCE_NOTFOUND(_CONST.COD_ERROR, "RECURSOS NO ENCONTRADOS");
    private final String cod, message;

    Errores(String cod, String msge){
    this.cod = cod;
    this.message = msge;
    }

}
