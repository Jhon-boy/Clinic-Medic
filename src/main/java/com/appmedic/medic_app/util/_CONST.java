package com.appmedic.medic_app.util;

import org.springframework.stereotype.Component;

/**
 * Constantes usadas en toda la aplicacion
 * @author Jhon
 * @version  1.0
 * */
@Component
public class _CONST {

    public static final String COD_OK = "1";
    public static final String COD_ERROR = "ERROR";
    public static final String TOKEN_EXPIRED = "TKEXP00";
    public static final String TOKEN_INVALID = "TKNVP00";
    public static final String MENSAJE_OK = "Transacción OK";
    public static final String MENSAJE_ERROR = "ERROR, INTENTE NUEVAMENTE MÁS TARDE";

    // |-- MENSAJES LOGS
    public static final String DIR_IP = "\nIP --> ";
    public static final String ML_INI = "\nINICIANDO... --> ";
    public static final String ML_FIN = "\nFINALIZADO... --> ";
    public static final String ML_ERROR = "\nERROR... --> ";

    // ERRORES EN SOLICITUD
    public static final String CLASS_ERROR = "\nCLASE ERROR --> ";
    public static String URL_ENDPOINT;

}
