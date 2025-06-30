package com.appmedic.medic_app.aplication.service.mappers;

import com.appmedic.medic_app.aplication.ports.in.dto.RegistrarEmpleadoDTO;
import com.appmedic.medic_app.domain.Exceptions.ExceptionGeneric;
import com.appmedic.medic_app.domain.entity.personas.TPEREMPLEADO;
import com.appmedic.medic_app.domain.entity.personas.TPERPERSONA;
import com.appmedic.medic_app.domain.entity.personas.TPERPROFECION;
import com.appmedic.medic_app.util.Utils;
import com.appmedic.medic_app.util.Const;

/**
 * Mappers para la entidad TPEREMPLEADO
 * @author Jhon C
 * @version 1.0
 * */
public class EmpleadoMappers {
    private EmpleadoMappers(){
        throw  new ExceptionGeneric(Const.CLASE_NO_INSTANCIADA);
    }

    public static TPEREMPLEADO toDtoToEntity(RegistrarEmpleadoDTO dto, TPERPERSONA persona, TPERPROFECION profecion){
        TPEREMPLEADO empleado = new TPEREMPLEADO();
        empleado.setExperiencia(dto.experiencia());
        empleado.setImagen(dto.imagen());
        empleado.setPersona(persona);
        empleado.setProfecion(profecion);

        empleado.setSlogan(Utils.safeString(dto.slogan()));
        return empleado;
    }
}
