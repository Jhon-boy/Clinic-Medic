package com.appmedic.medic_app.aplication.service.mappers;

import com.appmedic.medic_app.aplication.ports.in.dto.registrarPersonaDTO;
import com.appmedic.medic_app.domain.entity.personas.TPERPERSONA;
import com.appmedic.medic_app.util.Utils;


/**
 * Mappers para la entidad TPERPERSONA
 * @author Jhon C
 * @version 1.0
 * */
public class personaMappers {

    public static TPERPERSONA toDTOtoEntity(registrarPersonaDTO dto){
        TPERPERSONA entity = new TPERPERSONA();
        entity.setNombre(Utils.safeString(dto.nombre()));
        entity.setApellido(Utils.safeString(dto.apellido()));
        entity.setEmail(Utils.safeString(dto.email()));
        entity.setIDENTIFICACION(Utils.safeString(dto.identificacion()));
        entity.setTelefono(Utils.safeString(dto.telefono()));
        entity.setFechaIngreso(Utils.getDateNow());
        entity.setFnacimiento(dto.fnacimiento());

        return entity;
    }
}
