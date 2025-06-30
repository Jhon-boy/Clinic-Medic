package com.appmedic.medic_app.aplication.service.mappers;

import com.appmedic.medic_app.aplication.ports.in.dto.RegistrarProfecionDTO;
import com.appmedic.medic_app.domain.Exceptions.ExceptionGeneric;
import com.appmedic.medic_app.domain.entity.personas.TPERPROFECION;
import com.appmedic.medic_app.util.Utils;
import com.appmedic.medic_app.util.Const;

/**
 * Mappers para la entidad TPERPROFECION
 * @author Jhon C
 * @version 1.0
 * */
public class ProfecionMappers {
    private ProfecionMappers(){
        throw new ExceptionGeneric(Const.CLASE_NO_INSTANCIADA);
    }
    public  static TPERPROFECION toDTOtoEntity(RegistrarProfecionDTO dto){
        TPERPROFECION profecion = new TPERPROFECION();
        profecion.setNombre(Utils.safeString(dto.nombre()));
        profecion.setDescripcion(Utils.safeString(dto.descripcion()));
        return profecion;
    }
}
