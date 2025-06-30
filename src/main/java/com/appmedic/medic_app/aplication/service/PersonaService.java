package com.appmedic.medic_app.aplication.service;

import com.appmedic.medic_app.aplication.ports.in.dto.ActualizarPersonaDTO;
import com.appmedic.medic_app.aplication.ports.in.dto.RegistrarPersonaDTO;
import com.appmedic.medic_app.aplication.service.mappers.PersonaMappers;
import com.appmedic.medic_app.aplication.service.ports.PersonaServicePort;
import com.appmedic.medic_app.config.logger.Loggers;
import com.appmedic.medic_app.domain.entity.personas.TPERPERSONA;
import com.appmedic.medic_app.domain.entity.personas.persistence.TpersonaRepository;
import com.appmedic.medic_app.infra.out.Response;
import com.appmedic.medic_app.util.Utils;
import com.appmedic.medic_app.util.Const;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * La logica de Negocio para la entidad TPERPERSONA
 * @author  John C
 * @version 1.0
 * */
@Service
public class PersonaService implements PersonaServicePort {
    private final Loggers logs = new Loggers();
    private  final TpersonaRepository repository;
    private final UsuarioService usuarioService;

    public PersonaService(TpersonaRepository repository, UsuarioService usuarioService){
        this.repository = repository;
        this.usuarioService = usuarioService;
        logs.setLogger(PersonaService.class);
    }

    /**
     * Registra una persona en la entidad TPERPERSONA
     * @param  dto: Objeto DTO de registro
     * @return Response<?>>
     * */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Response registrarPersona(RegistrarPersonaDTO dto) {
        logs.info(Const.ML_INI + Utils.toJson(dto));
        Response response = Utils.generateBadResponseDefault();
        try {
            if(existePersona(dto.identificacion()) || usuarioService.obtenerUsuarioByUser(dto.usuario()).getCode().equals(Const.COD_OK)){
                response.setMessage("ESTE USUARIO YA SE ENCUENTRA REGISTRADO");
                return response;
            }
            TPERPERSONA persona = PersonaMappers.toDTOtoEntity(dto);
            TPERPERSONA personaCreada = repository.save(persona);
            Response responseRegisterUser = usuarioService.registrarUsuario(dto, personaCreada);
            if(responseRegisterUser.getCode().equals(Const.COD_OK) ){
                response = Utils.generateOKResponse(personaCreada);
            }
        } catch (Exception e){
            logs.error(Const.COD_ERROR ,e);
            response.setMessage(Const.MENSAJE_ERROR);
            response.setData(Utils.toJson(e.getMessage()));
        }
        logs.info(Const.ML_FIN + Utils.toJson(response));
        return response;
    }
    /**
     * Actualiza una persona en la entidad TPERPERSONA
     * @param  dto: Objeto DTO de registro
     * @return Response<?>>
     * */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Response updateInformation(ActualizarPersonaDTO dto){
        logs.info(Const.ML_INI + Utils.toJson(dto));
        Response response = Utils.generateBadResponseDefault();
        try {
            if(existePersona(dto.identificacion()) || usuarioService.obtenerUsuarioByUser(dto.usuario()).getCode().equals(Const.COD_OK)){
                TPERPERSONA personaUpdate = repository.save(PersonaMappers.toDtoEntityUpdate(dto));
                Response user = usuarioService.updateUser(dto, personaUpdate);
                if(user.getCode().equals(Const.COD_OK) ){
                    response = Utils.generateOKResponse(personaUpdate);
                }
            } else {
                response.setMessage("ESTE USUARIO NO SE ENCUENTRA REGISTRADO");
            }
        }catch (Exception e){
            logs.error(Const.COD_ERROR ,e);
            response.setData(Utils.toJson(e.getMessage()));
        }
        return  response;
    }

    public  Response getUsers(String estado){
        return  usuarioService.getAllUsers(estado);
    }

    public boolean existePersona (String identificacion){
        Optional<TPERPERSONA> tperpersona= repository.findByIdentificacion(identificacion);
        return tperpersona.isPresent();
    }
}
