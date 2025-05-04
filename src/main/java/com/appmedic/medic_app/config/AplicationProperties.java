package com.appmedic.medic_app.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
/**
 * Clase que permite leer las variable de entorno para una mejor administracion
 *
 * @author Jhon
 * */
@Configuration
@PropertySource("classpath:application.properties")
@ConfigurationProperties(prefix = "spring.mediapp")
@Data
public class AplicationProperties {
    private Security security;
    private Redis redis;
    private Config config;

    @Data
    public static class  Security {
        private Long expiration;
        private String key;
    }

    @Data
    public static class  Redis{
        private String host;
        private Integer port;
        private String url;
    }

    @Data
    public static class  Config{
        private String url;
    }
}
