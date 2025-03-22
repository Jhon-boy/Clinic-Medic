package com.appmedic.medic_app.aplication.security.auth;

import com.appmedic.medic_app.aplication.service.loginService;
import com.appmedic.medic_app.infra.out.Response;
import com.appmedic.medic_app.util.TokenVerify;
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
public class jwtAuthenticationFilter extends OncePerRequestFilter {

    private final jwtGenerator jwtGenerator;
    private final loginService userDetailsService;
    private ObjectMapper objectMapper;

    private jwtAuthenticationFilter(jwtGenerator _jwtGenerator, loginService _userDetailsService, ObjectMapper objectMapper){
        this.jwtGenerator = _jwtGenerator;
        this.userDetailsService = _userDetailsService;
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
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        objectMapper.writeValue(response.getOutputStream(), errorResponse);
    }
    /**
     * Metodo que extrae el token desde el Headers
     * */
    private String getJwtFromRequest(HttpServletRequest request){
        String token = request.getHeader("Authorization");
        if(StringUtils.hasText(token) && token.startsWith("Bearer ")){
            return token.substring(7);
        }
        return null;
    }
}
