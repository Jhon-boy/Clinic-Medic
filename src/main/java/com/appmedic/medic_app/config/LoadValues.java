package com.appmedic.medic_app.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
/**
 * CLase que se encarga de leer las variables de Entorno
 *
 * @author Jhon c
 * @version  1.0
 * */
@Data
@Configuration
@PropertySource("classpath:application.properties")
@ConfigurationProperties(prefix = "spring.mediapp")
public class LoadValues {

    private String url_endpoint;
}
