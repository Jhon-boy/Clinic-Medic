package com.appmedic.medic_app.aplication.security;

import com.appmedic.medic_app.aplication.security.auth.jwtAuthEntryPoint;
import com.appmedic.medic_app.aplication.security.auth.jwtAuthenticationFilter;
import com.appmedic.medic_app.aplication.service.loginService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
/**
 * Clase que controla los accesos a los EndPoints
 *
 * @author Jhon
 * @version 1.0
 * */
@Configuration
@EnableWebSecurity
public class securityConfig {

    private final loginService service;
    private static final String url_endpoint = "api/v1/mediapp";
    private final jwtAuthEntryPoint jwtAuthEntryPoint;
    private final jwtAuthenticationFilter jwtAuthenticationFilter;

    public securityConfig(loginService service, jwtAuthEntryPoint jwtAuthEntryPoint, jwtAuthenticationFilter jwtAuthenticationFilter ){
        this.service = service;
        this.jwtAuthenticationFilter = jwtAuthenticationFilter;
        this.jwtAuthEntryPoint = jwtAuthEntryPoint;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception{
        return configuration.getAuthenticationManager();
    }

    @Bean
    public AuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(service);
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        return authenticationProvider;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(
                        authorization -> authorization
                               .requestMatchers(url_endpoint + "/isAlive").permitAll()
                                .requestMatchers(url_endpoint+ "/usuario/login").permitAll()
                                .requestMatchers(url_endpoint+ "/persona/registrar").permitAll()
                                .anyRequest().authenticated()
                )
                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .exceptionHandling(exceptionHandling ->
                        exceptionHandling.authenticationEntryPoint(jwtAuthEntryPoint)
                )
                .headers(AbstractHttpConfigurer::disable)
                .build();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new PasswordEncoder() {
            @Override
            public String encode(CharSequence rawPassword) {
                return rawPassword.toString();
            }

            @Override
            public boolean matches(CharSequence rawPassword, String encodedPassword) {
                return rawPassword.toString().equals(encodedPassword);
            }
        };
    }
}
