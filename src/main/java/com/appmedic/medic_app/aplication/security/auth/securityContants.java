package com.appmedic.medic_app.aplication.security.auth;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Clase constante respecto a la seguridad de la aplicacion
 * @author John C
 * @version 1.0
 * */
@Data
@Component
public class securityContants {

    public static Long JWT_EXPIRATION = 360000L;
}
