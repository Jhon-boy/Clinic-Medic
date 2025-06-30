package com.appmedic.medic_app.aplication.service.mappers;

import com.appmedic.medic_app.aplication.ports.in.dto.ActualizarPersonaDTO;
import com.appmedic.medic_app.aplication.ports.in.dto.RegistrarPersonaDTO;
import com.appmedic.medic_app.aplication.ports.out.dto.UsuarioDTO;
import com.appmedic.medic_app.aplication.ports.out.dto.LoginResponseDTO;
import com.appmedic.medic_app.domain.Exceptions.ExceptionGeneric;
import com.appmedic.medic_app.domain.entity.personas.TPERPERSONA;
import com.appmedic.medic_app.domain.entity.seguridad.TSEGROL;
import com.appmedic.medic_app.domain.entity.seguridad.TSEGUSUARIO;
import com.appmedic.medic_app.util.Utils;
import com.appmedic.medic_app.util.Const;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Mapper que permite tratar Objectos de TSEGUSUARIO
 * @author Jhon
 * @version 1.0
 * */
public class UsuarioMappers {

    private UsuarioMappers(){
        throw new ExceptionGeneric(Const.CLASE_NO_INSTANCIADA);
    }
    public static TSEGUSUARIO toDTOtoEntity(RegistrarPersonaDTO dto, TSEGROL rol, TPERPERSONA personas){
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
    public  static LoginResponseDTO toEntityToLogiinDto(TSEGUSUARIO tsegusuario) {
        LoginResponseDTO dto = new LoginResponseDTO();
        dto.setIdUsuario(tsegusuario.getId());
        dto.setEmail(tsegusuario.getPersona().getEmail());
        dto.setIdRol(tsegusuario.getRol().getId());
        dto.setNombres(tsegusuario.getPersona().getNombre());
        dto.setUsuario(tsegusuario.getUsuario());
        return dto;
    }
    public static List<UsuarioDTO> toListUsuarioDto(List<TSEGUSUARIO> users){
        return users.stream().map(
                user -> UsuarioDTO.builder()
                        .id(user.getId())
                        .nombre(user.getPersona().getNombre())
                        .apellido(user.getPersona().getApellido())
                        .identificacion(user.getPersona().getIdentificacion())
                        .idRol(user.getRol().getId())
                        .nombreRol(user.getRol().getNombre())
                        .email(user.getPersona().getEmail())
                        .fechaIngreso(user.getPersona().getFnacimiento())
                        .fechaIngreso(user.getPersona().getFechaIngreso())
                        .fechaActualizacion(user.getFechaActualizacion())
                        .telefono(user.getPersona().getTelefono())
                        .build()
        ).collect(Collectors.toList());
    }
    public static TSEGUSUARIO toDTOtoEntityUpdate(ActualizarPersonaDTO dto, TSEGROL rol, TPERPERSONA personas){
        TSEGUSUARIO usuario = new TSEGUSUARIO();
        usuario.setRol(rol);
        usuario.setPersona(personas);
        usuario.setUsuario(Utils.safeString(dto.usuario()));
        usuario.setClave(Utils.safeString(dto.password()));
        usuario.setActivo(dto.activo() == 1);
        usuario.setFechaActualizacion(Utils.getDateNow());
        return usuario;

    }
}
