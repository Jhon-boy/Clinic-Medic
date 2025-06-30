package com.appmedic.medic_app.aplication.security.auth;

import com.appmedic.medic_app.aplication.service.LoginService;
import com.appmedic.medic_app.infra.out.Response;
import com.appmedic.medic_app.domain.enums.TokenVerify;
import com.appmedic.medic_app.util.Utils;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
/**
 * Clase que maneja un token
 *
 * @author John C.
 * @version 1.0
 * */
@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtGenerator jwtGenerator;
    private final LoginService userDetailsService;
    private final ObjectMapper objectMapper;

    public JwtAuthenticationFilter(JwtGenerator jwtGenerator, LoginService userDetailsService, ObjectMapper objectMapper){
        this.jwtGenerator = jwtGenerator;
        this.userDetailsService = userDetailsService;
        this.objectMapper = objectMapper;
    }

    /**
     * @param request
     * @param response
     * @param filterChain
     */
    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request,
                                    @NonNull HttpServletResponse response,
                                    @NonNull FilterChain filterChain) throws ServletException, IOException {
        try {
            String token = getJwtFromRequest(request);
            if (StringUtils.hasText(token)) {
                // Validar el token - esto lanzará excepciones si el token no es válido
                if(LoginService.isBlackList(token)){
                    handleTokenError(response, TokenVerify.LOG_OUT.getCode(),TokenVerify.LOG_OUT.getMessage());
                    return;
                }
                if (jwtGenerator.validateToken(token)) {
                    String username = jwtGenerator.getUsernameFromJWT(token);
                    UserDetails userDetails = userDetailsService.loadUserByUsername(username);
                    UsernamePasswordAuthenticationToken authenticationToken =
                            new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                    authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                    filterChain.doFilter(request, response);
                } else {
                    handleTokenError(response,  TokenVerify.DEFAULT.getCode(), TokenVerify.DEFAULT.getMessage());
                }
            } else {
                filterChain.doFilter(request, response);
            }
        } catch (ExpiredJwtException e) {
            handleTokenError(response, TokenVerify.EXPIRADO.getCode(), TokenVerify.EXPIRADO.getMessage());
        } catch (JwtException e) {
            handleTokenError(response, TokenVerify.DEFAULT.getCode(), TokenVerify.DEFAULT.getMessage());
        }
    }
    /**
     * Manejo de Error de Tokens
     * */
    private void handleTokenError(HttpServletResponse response, String code, String message) throws IOException {
        Response errorResponse = Utils.generateResponse(code, message, null);
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setContentType(SecurityConstants.CONTENT_TYPE);
        response.setCharacterEncoding(SecurityConstants.TYPE_ENCODING);
        objectMapper.writeValue(response.getOutputStream(), errorResponse);
    }
    /**
     * Metodo que extrae el token desde el Headers
     * */
    private String getJwtFromRequest(HttpServletRequest request){
        String token = request.getHeader(SecurityConstants.HEADER_NAME);
        if(StringUtils.hasText(token) && token.startsWith("Bearer ")){
            return token.substring(7);
        }
        return null;
    }
}
