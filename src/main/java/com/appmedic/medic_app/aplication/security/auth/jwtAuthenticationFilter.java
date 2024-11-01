package com.appmedic.medic_app.aplication.security.auth;

import com.appmedic.medic_app.aplication.service.loginService;
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

    private jwtAuthenticationFilter(jwtGenerator _jwtGenerator, loginService _userDetailsService){
        this.jwtGenerator = _jwtGenerator;
        this.userDetailsService = _userDetailsService;
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
            String token = getJwtFromRequest(request);
        if(StringUtils.hasText(token) && jwtGenerator.validateToken(token)){
            String username = jwtGenerator.getUsernameFromJWT(token);

            UserDetails userDetails = userDetailsService.loadUserByUsername(username);
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetails,null,
                    userDetails.getAuthorities());
            authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        }
        filterChain.doFilter(request,response);
    }

    private String getJwtFromRequest(HttpServletRequest request){
        String token = request.getHeader("Authorization");
        if(StringUtils.hasText(token) && token.startsWith("Bearer ")){
            return token.substring(7);
        }
        return null;
    }
}
