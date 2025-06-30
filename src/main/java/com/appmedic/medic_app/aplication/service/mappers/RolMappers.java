package com.appmedic.medic_app.aplication.service.mappers;

import com.appmedic.medic_app.aplication.ports.in.dto.ActualizarRolDTO;
import com.appmedic.medic_app.aplication.ports.in.dto.RegistrarRolDTO;
import com.appmedic.medic_app.domain.Exceptions.ExceptionGeneric;
import com.appmedic.medic_app.domain.entity.seguridad.TSEGROL;
import com.appmedic.medic_app.util.Utils;
import com.appmedic.medic_app.util.Const;

/**
 * Mappers para la entidad TSEGROL
 * @author Jhon C
 * @version 1.0
 * */
public class RolMappers {

    private RolMappers(){
        throw new ExceptionGeneric(Const.CLASE_NO_INSTANCIADA);
    }

    public static TSEGROL toDTOtoEntity(RegistrarRolDTO dto ){
        TSEGROL rol = new TSEGROL();
        rol.setNombre(Utils.safeString(dto.nombre()));
        rol.setDescripcion(Utils.safeString(dto.descripcion()));
        rol.setActivo(dto.activo());
        return rol;
    }
    public static TSEGROL updateDTOtoEntity(ActualizarRolDTO dto ){
        TSEGROL rol = new TSEGROL();
        rol.setId(dto.idRol());
        rol.setNombre(Utils.safeString(dto.nombre()));
        rol.setDescripcion(Utils.safeString(dto.descripcion()));
        rol.setActivo(dto.activo());
        return rol;
    }
}
