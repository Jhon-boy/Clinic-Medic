package com.appmedic.medic_app;

import com.appmedic.medic_app.config.AplicationProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(AplicationProperties.class)
public class MedicApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(MedicApiApplication.class, args);
	}

}
