package com.appmedic.medic_app.aplication.service;

import com.appmedic.medic_app.aplication.ports.in.dto.registrarPersonaDTO;
import com.appmedic.medic_app.aplication.service.mappers.personaMappers;
import com.appmedic.medic_app.aplication.service.ports.personaServicePort;
import com.appmedic.medic_app.config.logger.Loggers;
import com.appmedic.medic_app.domain.entity.personas.TPERPERSONA;
import com.appmedic.medic_app.domain.entity.personas.persistence.tPersonaRepository;
import com.appmedic.medic_app.infra.out.Response;
import com.appmedic.medic_app.util.Utils;
import com.appmedic.medic_app.util._CONST;
import org.springframework.stereotype.Service;

/**
 * La logica de Negocio para la entidad TPERPERSONA
 * @author  John C
 * @version 1.0
 * */
@Service
public class personaService implements personaServicePort {
    private final Loggers logs = new Loggers();
    private  final tPersonaRepository repository;
    private final usuarioService usuarioService;

    public personaService(tPersonaRepository _repository,usuarioService _usuarioService){
        this.repository = _repository;
        this.usuarioService = _usuarioService;
        logs.setLogger(personaService.class);
    }

    /**
     * Registra una persona en la entidad TPERPERSONA
     * @param  dto: Objeto DTO de registro
     * @return Response<?>>
     * */
    @Override
    public Response<?> registrarPersona(registrarPersonaDTO dto) {
        logs.info(_CONST.ML_INI + Utils.toJson(dto));
        Response<?> response = Utils.generateBadResponseDefault();
        try {
            TPERPERSONA persona = personaMappers.toDTOtoEntity(dto);
            TPERPERSONA personaCreada = repository.save(persona);
            Response<?> responseRegisterUser = usuarioService.registrarUsuario(dto, personaCreada);
            if(responseRegisterUser.getCode().equals(_CONST.COD_OK) )
                response = Utils.generateOKResponse(personaCreada);
        }catch (Exception e){
            logs.error(_CONST.COD_ERROR ,e);
            response = Utils.generateBadResponseDefault();
        }
        logs.info(_CONST.ML_FIN + Utils.toJson(response));
        return response;
    }
}
