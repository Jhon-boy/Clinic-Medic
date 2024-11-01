package com.appmedic.medic_app.aplication.service.mappers;

import com.appmedic.medic_app.aplication.ports.in.dto.registrarProfecionDTO;
import com.appmedic.medic_app.domain.entity.personas.TPERPROFECION;
import com.appmedic.medic_app.util.Utils;

/**
 * Mappers para la entidad TPERPROFECION
 * @author Jhon C
 * @version 1.0
 * */
public class profecionMappers {

    public  static TPERPROFECION toDTOtoEntity(registrarProfecionDTO dto){
        TPERPROFECION profecion = new TPERPROFECION();
        profecion.setNombre(Utils.safeString(dto.nombre()));
        profecion.setDescripcion(Utils.safeString(dto.descripcion()));
        return profecion;
    }
}
