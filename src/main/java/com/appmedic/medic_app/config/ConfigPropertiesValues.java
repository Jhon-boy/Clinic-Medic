package com.appmedic.medic_app.config;


import org.hibernate.boot.model.naming.Identifier;
import org.hibernate.boot.model.naming.PhysicalNamingStrategyStandardImpl;
import org.hibernate.engine.jdbc.env.spi.JdbcEnvironment;
import org.springframework.context.annotation.Configuration;

/**
 * Clase que se encarga de mapear los atributos de las clases sin realizar ninguna  modificacion
 * por parte de Hibernate
 * @author  John C
 * @version 1.0
 */
@Configuration
public class ConfigPropertiesValues extends PhysicalNamingStrategyStandardImpl {

    @Override
    public Identifier toPhysicalColumnName(Identifier logicalColumn, JdbcEnvironment jdbcEnvironment){
        return logicalColumn;
    }
}
