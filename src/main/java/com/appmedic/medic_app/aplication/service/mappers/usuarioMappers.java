package com.appmedic.medic_app.aplication.service.mappers;

import com.appmedic.medic_app.aplication.ports.in.dto.registrarPersonaDTO;
import com.appmedic.medic_app.aplication.ports.out.dto.UsuarioDTO;
import com.appmedic.medic_app.aplication.ports.out.dto.loginResponseDTO;
import com.appmedic.medic_app.domain.entity.personas.TPERPERSONA;
import com.appmedic.medic_app.domain.entity.seguridad.TSEGROL;
import com.appmedic.medic_app.domain.entity.seguridad.TSEGUSUARIO;
import com.appmedic.medic_app.util.Utils;

import java.util.List;
import java.util.stream.Collectors;

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
    public  static loginResponseDTO toEntityToLogiinDto(TSEGUSUARIO tsegusuario) {
        loginResponseDTO dto = new loginResponseDTO();
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
}
