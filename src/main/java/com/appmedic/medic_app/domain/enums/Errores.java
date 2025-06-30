package com.appmedic.medic_app.domain.enums;

import com.appmedic.medic_app.util.Const;
import lombok.Getter;

/**
 * Enumerados de manejo de errores
 * @author Jhon
 * @version 1.0
 * */
@Getter
public enum Errores {

    GENERAL(Const.COD_ERROR, Const.MENSAJE_ERROR),
    PATH_NOTFOUND(Const.COD_ERROR, "PATH NO ENCONTRADO"),
    DATA_WRONG(Const.COD_ERROR, "INFORMACION INCONSISTENTE"),
    RESOURCE_NOTFOUND(Const.COD_ERROR, "RECURSOS NO ENCONTRADOS");
    private final String cod;
    private final String message;

    Errores(String cod, String msge){
    this.cod = cod;
    this.message = msge;
    }

}
