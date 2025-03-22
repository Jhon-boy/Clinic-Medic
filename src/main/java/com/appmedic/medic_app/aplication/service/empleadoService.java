package com.appmedic.medic_app.aplication.service;

import com.appmedic.medic_app.aplication.ports.in.dto.registrarEmpleadoDTO;
import com.appmedic.medic_app.aplication.service.mappers.empleadoMappers;
import com.appmedic.medic_app.aplication.service.ports.empeladoServicePort;
import com.appmedic.medic_app.config.logger.Loggers;
import com.appmedic.medic_app.domain.entity.personas.TPERPERSONA;
import com.appmedic.medic_app.domain.entity.personas.TPERPROFECION;
import com.appmedic.medic_app.domain.entity.personas.persistence.tEmpleadoRepository;
import com.appmedic.medic_app.domain.entity.personas.persistence.tPersonaRepository;
import com.appmedic.medic_app.domain.entity.personas.persistence.tProfecionRepository;
import com.appmedic.medic_app.infra.out.Response;
import com.appmedic.medic_app.util.Utils;
import com.appmedic.medic_app.util._CONST;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * La logica de Negocio para la entidad TPEREMPLEADO
 * @author  John C
 * @version 1.0
 * */
@Service
public class empleadoService  implements empeladoServicePort {
    private final Loggers logs = new Loggers();
    private  final tEmpleadoRepository repository;
    private final tPersonaRepository personaRepository;
    private final tProfecionRepository profecionalRepository;

    public empleadoService(tEmpleadoRepository _repository, tProfecionRepository _profecionalRepository,
     tPersonaRepository _personaRepository){
        this.repository = _repository;
        this.personaRepository = _personaRepository;
        this.profecionalRepository = _profecionalRepository;
    }

    @Override
    public Response registrarEmpleado(registrarEmpleadoDTO dto) {
        logs.info(_CONST.ML_INI + Utils.toJson(dto));
        Response response = Utils.generateBadResponseDefault();
        try {
            Optional<TPERPERSONA> persona = personaRepository.findById(dto.idPersona());
            Optional<TPERPROFECION> profecion = profecionalRepository.findById(dto.idProfecion());
            if(persona.isPresent() && profecion.isPresent()){
                var empleado = empleadoMappers.toDtoToEntity(dto, persona.get(), profecion.get());
                response = Utils.generateOKResponse(empleado.getPersona());
            }
        }catch (Exception e){
            logs.error(_CONST.COD_ERROR ,e);
        }
        return  response;
    }
}
