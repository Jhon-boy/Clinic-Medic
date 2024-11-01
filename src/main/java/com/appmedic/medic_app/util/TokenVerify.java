package com.appmedic.medic_app.util;

import lombok.Getter;

@Getter
public enum TokenVerify {
    OK(1,"TN001","ACCESO AUTORIZADO"),
    EXPIRADO(2, "TN002","TOKEN EXPIRADO"),
    BAD_FORMED(3, "TN003","TOKEN INVALIDO"),
    NO_ASOCIADO(4, "TN004", "ROLES NO ASOCIADO AL USUARIO"),
    DEFAULT(5, "TN005","ERROR AL VALIDAR EL TOKEN");

    private final int id;
    private final String code;
    private final String message;
    TokenVerify(int id, String code,String message){
        this.id = id;
        this.code = code;
        this.message = message;
    }
}
