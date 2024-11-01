package com.appmedic.medic_app.aplication.service;
import com.appmedic.medic_app.aplication.ports.in.dto.registrarProfecionDTO;
import com.appmedic.medic_app.aplication.service.mappers.profecionMappers;
import com.appmedic.medic_app.aplication.service.ports.profecionServicePort;
import com.appmedic.medic_app.config.logger.Loggers;
import com.appmedic.medic_app.domain.entity.personas.TPERPROFECION;
import com.appmedic.medic_app.domain.entity.personas.persistence.tProfecionRepository;
import com.appmedic.medic_app.infra.out.Response;
import com.appmedic.medic_app.util.Utils;
import com.appmedic.medic_app.util._CONST;
import org.springframework.stereotype.Service;

/**
 * La logica de Negocio para la entidad TPERPROFECION
 * @author  John C
 * @version 1.0
 * */
@Service
public class profecionService implements profecionServicePort {
    private final Loggers logs = new Loggers();
    private final tProfecionRepository repository;

    public  profecionService( tProfecionRepository _repository){
        this.repository = _repository;
        logs.setLogger(personaService.class);
    }

    @Override
    public Response<?> registrarProfecion(registrarProfecionDTO dto) {
      logs.info(_CONST.ML_INI + Utils.toJson(dto));
      Response<?> response = Utils.generateBadResponseDefault();
      try {
          TPERPROFECION profecion = profecionMappers.toDTOtoEntity(dto);
          var result = repository.save(profecion);
          response = Utils.generateOKResponse(result);
      }catch (Exception e){
          logs.error(_CONST.CLASS_ERROR, e);
      }
      logs.info(_CONST.ML_FIN + Utils.toJson(response));
      return  response;
    }
}
