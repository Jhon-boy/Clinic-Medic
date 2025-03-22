package com.appmedic.medic_app.aplication.security.auth;

import com.appmedic.medic_app.config.logger.Loggers;
import com.appmedic.medic_app.infra.out.Response;
import com.appmedic.medic_app.util.TokenVerify;
import com.appmedic.medic_app.util.Utils;
import com.appmedic.medic_app.util._CONST;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.security.SignatureException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * Clase que controla los acceso
 *
 * @author John C
 * @version 1.0
 * */
@Component
public class jwtAuthEntryPoint  implements AuthenticationEntryPoint {

    private final Loggers log = new Loggers();
    private final ObjectMapper mapper;

    public jwtAuthEntryPoint(ObjectMapper mapper) {
        this.mapper = mapper;
        log.setLogger(jwtGenerator.class);
    }
    /**
     * Manejo de acceso a no autorizados
     * @param request
     * @param response
     * @param authException
     * @throws IOException
     */
    @Override
    public void commence( HttpServletRequest request,  HttpServletResponse response,
            AuthenticationException authException) throws IOException {

        Response responseApp = new Response();
        TokenVerify tokenVerify;

        if (authException instanceof InsufficientAuthenticationException) {
            String message = authException.getMessage();
            if (message != null && message.contains("Invalid token")) {
                tokenVerify = TokenVerify.BAD_FORMED;
            } else if (message != null && message.contains("expired")) {
                tokenVerify = TokenVerify.EXPIRADO;
            } else {
                // Verificar si hay una causa específica de JWT
                Throwable cause = authException.getCause();
                if (cause instanceof ExpiredJwtException) {
                    tokenVerify = TokenVerify.EXPIRADO;
                } else if (cause instanceof MalformedJwtException
                        || cause instanceof UnsupportedJwtException
                        || cause instanceof SignatureException) {
                    tokenVerify = TokenVerify.BAD_FORMED;
                } else if (message != null && message.contains("Full authentication is required")) {
                    // Este es el caso cuando no se proporciona token
                    tokenVerify = TokenVerify.BAD_FORMED;
                } else {
                    tokenVerify = TokenVerify.DEFAULT;
                }
            }
        } else {
            tokenVerify = TokenVerify.DEFAULT;
        }

        responseApp.setCode(tokenVerify.getCode());
        responseApp.setMessage(tokenVerify.getMessage());

        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setCharacterEncoding(StandardCharsets.UTF_8.name());

        log.info(_CONST.ML_FIN + Utils.toJson(responseApp));
        mapper.writeValue(response.getOutputStream(), responseApp);
    }
}