package com.appmedic.medic_app.aplication.service.mappers;

import com.appmedic.medic_app.aplication.ports.in.dto.registrarRolDTO;
import com.appmedic.medic_app.domain.entity.seguridad.TSEGROL;
import com.appmedic.medic_app.util.Utils;

/**
 * Mappers para la entidad TSEGROL
 * @author Jhon C
 * @version 1.0
 * */
public class rolMappers {
    public static TSEGROL toRolEntityMapper(registrarRolDTO dto ){
        TSEGROL rol = new TSEGROL();
        rol.setNombre(Utils.safeString(dto.nombre()));
        rol.setDescripcion(Utils.safeString(dto.descripcion()));
        rol.setActivo(dto.activo());
        return rol;
    }
}
