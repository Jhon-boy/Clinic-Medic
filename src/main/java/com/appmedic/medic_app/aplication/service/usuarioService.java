package com.appmedic.medic_app.aplication.service;

import com.appmedic.medic_app.aplication.ports.in.dto.registrarPersonaDTO;
import com.appmedic.medic_app.aplication.ports.out.dto.UsuarioDTO;
import com.appmedic.medic_app.aplication.service.mappers.usuarioMappers;
import com.appmedic.medic_app.aplication.service.ports.usuarioServicePort;
import com.appmedic.medic_app.config.logger.Loggers;
import com.appmedic.medic_app.domain.entity.personas.TPERPERSONA;
import com.appmedic.medic_app.domain.entity.seguridad.TSEGROL;
import com.appmedic.medic_app.domain.entity.seguridad.TSEGUSUARIO;
import com.appmedic.medic_app.domain.entity.seguridad.persistence.tRolRepository;
import com.appmedic.medic_app.domain.entity.seguridad.persistence.tUsuarioRepository;
import com.appmedic.medic_app.domain.enums.Estados;
import com.appmedic.medic_app.infra.out.Response;
import com.appmedic.medic_app.util.Utils;
import com.appmedic.medic_app.util._CONST;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * La logica de Negocio para la entidad TSEGUSUARIO
 * @author  John C
 * @version 1.0
 * */
@Service
public class usuarioService implements usuarioServicePort {

    private final Loggers logs = new Loggers();
    private final tUsuarioRepository repository;
    private final tRolRepository rolRepository;

    public  usuarioService(tUsuarioRepository _repository, tRolRepository _rolService) {
        this.repository = _repository;
        this.rolRepository = _rolService;
        this.logs.setLogger(usuarioService.class);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Response registrarUsuario(registrarPersonaDTO dto, TPERPERSONA persona) {
        logs.info(_CONST.ML_INI + Utils.toJson(dto));
        Response response =  Utils.generateBadResponseDefault();
        try{
            Optional<TSEGROL> rolOptional = rolRepository.findById(dto.idRol());
            if(rolOptional.isPresent()){
                TSEGUSUARIO usuario = usuarioMappers.toDTOtoEntity(dto, rolOptional.get(), persona);
                repository.save(usuario);
                response =Utils.generateOKResponse(usuario);
            }
        }catch (Exception e){
            logs.error(_CONST.COD_ERROR ,e);
            response.setMessage(e.getMessage());
        }
        logs.info(_CONST.ML_FIN + Utils.toJson(response));
        return response;
    }

    /** Method search for a username
     * @param username
     * @return
     */
    @Override
    public Response obtenerUsuarioByUser(String username) {
        if(repository.findByUsuario(username).isPresent()){
            return  Utils.generateOKResponse("Usuario Existe");
        }
        return  Utils.generateBadResponseDefault();
    }

    /**
     * @param estado
     * @return
     */
    @Override
    public Response getAllUsers(String estado) {
        logs.info(_CONST.ML_INI + estado);
        Response response =  Utils.generateBadResponseDefault();
        List<TSEGUSUARIO> users = repository.findAll().stream().filter(user -> user.isActivo()).collect(Collectors.toList());
        List<UsuarioDTO> listUsers =  users.stream().map(
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

        return Utils.generateOKResponse(listUsers);
    }

}
