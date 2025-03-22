package com.appmedic.medic_app.aplication.security.auth;

import com.appmedic.medic_app.config.logger.Loggers;
import com.appmedic.medic_app.util.TokenVerify;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.security.Key;
import java.util.Date;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Clase que permite generar Tokens y actualizarlos
 * @author Jhon
 * @version 1.0
 * */
@Component
public class jwtGenerator {
    private final Loggers logs = new Loggers();

    public  jwtGenerator(){
        logs.setLogger(jwtGenerator.class);
    }

    @Value("${security.jwt.secret-key}")
    private String secretKey;

    public String generateToken(Authentication authentication) {
        UserDetails userPrincipal = (UserDetails) authentication.getPrincipal();
        List<String> roles = userPrincipal.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority).collect(Collectors.toList());

        Date currentDate = new Date(System.currentTimeMillis());
        Date expireDate = new Date(System.currentTimeMillis()+ securityContants.JWT_EXPIRATION);

        return Jwts.builder()
                .subject(userPrincipal.getUsername())
                .claim("roles", roles)
                .issuedAt(currentDate)
                .expiration(expireDate)
                .signWith(getKey())
                .compact();
    }

    public Key getKey() {
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public <T> T getClaims(String token, Function<Claims, T> claimsResolver) {
        return claimsResolver.apply(Jwts.parser()
                .verifyWith((SecretKey) getKey())
                .build()
                .parseSignedClaims(token)
                .getPayload());
    }

    public String getUsernameFromJWT(String token) {
        return getClaims(token, Claims::getSubject);
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parser().verifyWith((SecretKey) getKey()).build().parseSignedClaims(token).getPayload();
            return true;
        } catch (MalformedJwtException | UnsupportedJwtException e) {
            logs.error("Token mal formado " , e);
            throw e;
        } catch (ExpiredJwtException e) {
            logs.error("Token expirado " , e);
            throw e;
        } catch (IllegalArgumentException e) {
            logs.error("Token vacio " , e);
            throw e;
        } catch (SignatureException e) {
            logs.error("Token vacio  " , e);
            throw e;
        }
    }

    public String refreshToken(Authentication authentication) {
        try {
            UserDetails userPrincipal = (UserDetails) authentication.getPrincipal();
            List<String> roles = userPrincipal.getAuthorities().stream()
                    .map(GrantedAuthority::getAuthority).collect(Collectors.toList());

            Date currentDate = new Date();
            Date expireDate = new Date(currentDate.getTime()  + 9000000);

            return Jwts.builder()
                    .subject(userPrincipal.getUsername())
                    .claim("roles", roles)
                    .issuedAt(currentDate)
                    .expiration(expireDate)
                    .signWith(getKey())
                    .compact();

        } catch (Exception e) {
            throw new RuntimeException("Error internal server");
        }
    }
}
