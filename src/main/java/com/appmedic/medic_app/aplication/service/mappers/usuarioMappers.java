package com.appmedic.medic_app.aplication.service.mappers;

import com.appmedic.medic_app.aplication.ports.in.dto.registrarPersonaDTO;
import com.appmedic.medic_app.domain.entity.personas.TPERPERSONA;
import com.appmedic.medic_app.domain.entity.seguridad.TSEGROL;
import com.appmedic.medic_app.domain.entity.seguridad.TSEGUSUARIO;
import com.appmedic.medic_app.util.Utils;
/**
 * Mapper que permite tratar Objectos de TSEGUSUARIO
 * @author Jhon
 * @version 1.0
 * */
public class usuarioMappers {

    public static TSEGUSUARIO toDTOtoEntity(registrarPersonaDTO dto, TSEGROL rol, TPERPERSONA personas){
        TSEGUSUARIO usuario = new TSEGUSUARIO();
        usuario.setRol(rol);
        usuario.setPersona(personas);
        usuario.setUsuario(Utils.safeString(dto.usuario()));
        usuario.setClave(Utils.safeString(dto.password()));
        usuario.setActivo(true);
        usuario.setSesion(false);
        usuario.setFechaIngreso(Utils.getDateNow());

        return usuario;

    }
}
