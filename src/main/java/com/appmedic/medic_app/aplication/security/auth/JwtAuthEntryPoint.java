package com.appmedic.medic_app.aplication.security.auth;

import com.appmedic.medic_app.config.logger.Loggers;
import com.appmedic.medic_app.infra.out.Response;
import com.appmedic.medic_app.domain.enums.TokenVerify;
import com.appmedic.medic_app.util.Utils;
import com.appmedic.medic_app.util.Const;
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
public class JwtAuthEntryPoint implements AuthenticationEntryPoint {

    private final Loggers log = new Loggers();
    private final ObjectMapper mapper;

    public JwtAuthEntryPoint(ObjectMapper mapper) {
        this.mapper = mapper;
        log.setLogger(JwtGenerator.class);
    }
    /**
     * Manejo de acceso a no autorizados
     * @param request
     * @param response
     * @param authException
     * @throws IOException
     */
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
                         AuthenticationException authException) throws IOException {

        Response responseApp = new Response();
        TokenVerify tokenVerify = resolveTokenStatus(authException);

        responseApp.setCode(tokenVerify.getCode());
        responseApp.setMessage(tokenVerify.getMessage());

        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setCharacterEncoding(StandardCharsets.UTF_8.name());

        log.info(Const.ML_FIN + Utils.toJson(responseApp));
        mapper.writeValue(response.getOutputStream(), responseApp);
    }

    private TokenVerify resolveTokenStatus(AuthenticationException authException) {
        if (!(authException instanceof InsufficientAuthenticationException)) {
            return TokenVerify.DEFAULT;
        }

        String message = authException.getMessage();
        Throwable cause = authException.getCause();

        if (message != null) {
            if (message.contains("Invalid token")) return TokenVerify.BAD_FORMED;
            if (message.contains("expired")) return TokenVerify.EXPIRADO;
            if (message.contains("Full authentication is required")) return TokenVerify.BAD_FORMED;
        }

        if (cause instanceof ExpiredJwtException) return TokenVerify.EXPIRADO;
        if (cause instanceof MalformedJwtException
                || cause instanceof UnsupportedJwtException
                || cause instanceof SignatureException) {
            return TokenVerify.BAD_FORMED;
        }

        return TokenVerify.DEFAULT;
    }

}