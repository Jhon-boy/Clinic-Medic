package com.appmedic.medic_app.aplication.service.mappers;

import com.appmedic.medic_app.aplication.ports.in.dto.registrarEmpleadoDTO;
import com.appmedic.medic_app.domain.entity.personas.TPEREMPLEADO;
import com.appmedic.medic_app.domain.entity.personas.TPERPERSONA;
import com.appmedic.medic_app.domain.entity.personas.TPERPROFECION;
import com.appmedic.medic_app.util.Utils;

/**
 * Mappers para la entidad TPEREMPLEADO
 * @author Jhon C
 * @version 1.0
 * */
public class empleadoMappers {

    public static TPEREMPLEADO toDtoToEntity(registrarEmpleadoDTO dto, TPERPERSONA persona, TPERPROFECION profecion){
        TPEREMPLEADO empleado = new TPEREMPLEADO();
        empleado.setExperiencia(dto.experiencia());
        empleado.setImagen(dto.imagen());
        empleado.setPersona(persona);
        empleado.setProfecion(profecion);

        empleado.setSlogan(Utils.safeString(dto.slogan()));
        return empleado;
    }
}
