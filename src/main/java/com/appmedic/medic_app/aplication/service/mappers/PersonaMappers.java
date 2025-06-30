package com.appmedic.medic_app.aplication.service.mappers;

import com.appmedic.medic_app.aplication.ports.in.dto.ActualizarPersonaDTO;
import com.appmedic.medic_app.aplication.ports.in.dto.RegistrarPersonaDTO;
import com.appmedic.medic_app.domain.Exceptions.ExceptionGeneric;
import com.appmedic.medic_app.domain.entity.personas.TPERPERSONA;
import com.appmedic.medic_app.util.Utils;
import com.appmedic.medic_app.util.Const;


/**
 * Mappers para la entidad TPERPERSONA
 * @author Jhon C
 * @version 1.0
 * */
public class PersonaMappers {

    private PersonaMappers(){
        throw new ExceptionGeneric(Const.CLASE_NO_INSTANCIADA);
    }
    public static TPERPERSONA toDTOtoEntity(RegistrarPersonaDTO dto){
        TPERPERSONA entity = new TPERPERSONA();
        entity.setNombre(Utils.safeString(dto.nombre()));
        entity.setApellido(Utils.safeString(dto.apellido()));
        entity.setEmail(Utils.safeString(dto.email()));
        entity.setIdentificacion(Utils.safeString(dto.identificacion()));
        entity.setTelefono(Utils.safeString(dto.telefono()));
        entity.setFechaIngreso(Utils.getDateNow());
        entity.setFnacimiento(dto.fnacimiento());

        return entity;
    }

    public static TPERPERSONA toDtoEntityUpdate(ActualizarPersonaDTO dto){
        TPERPERSONA entity = new TPERPERSONA();
        entity.setNombre(dto.nombre());
        entity.setTelefono(dto.telefono());
        entity.setEmail(dto.email());
        entity.setApellido(dto.apellido());
        entity.setFnacimiento(dto.fnacimiento());
        entity.setFechaActualizacion(Utils.getDateNow());
        return  entity;
    }
}
