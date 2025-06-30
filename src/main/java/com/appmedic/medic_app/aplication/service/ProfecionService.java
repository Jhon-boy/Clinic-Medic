package com.appmedic.medic_app.aplication.service;
import com.appmedic.medic_app.aplication.ports.in.dto.RegistrarProfecionDTO;
import com.appmedic.medic_app.aplication.service.mappers.ProfecionMappers;
import com.appmedic.medic_app.aplication.service.ports.ProfecionServicePort;
import com.appmedic.medic_app.config.logger.Loggers;
import com.appmedic.medic_app.domain.entity.personas.TPERPROFECION;
import com.appmedic.medic_app.domain.entity.personas.persistence.TprofecionRepository;
import com.appmedic.medic_app.infra.out.Response;
import com.appmedic.medic_app.util.Utils;
import com.appmedic.medic_app.util.Const;
import org.springframework.stereotype.Service;

/**
 * La logica de Negocio para la entidad TPERPROFECION
 * @author  John C
 * @version 1.0
 * */
@Service
public class ProfecionService implements ProfecionServicePort {
    private final Loggers logs = new Loggers();
    private final TprofecionRepository repository;

    public ProfecionService(TprofecionRepository repository){
        this.repository = repository;
        logs.setLogger(PersonaService.class);
    }

    @Override
    public Response registrarProfecion(RegistrarProfecionDTO dto) {
      logs.info(Const.ML_INI + Utils.toJson(dto));
      Response response = Utils.generateBadResponseDefault();
      try {
          TPERPROFECION profecion = ProfecionMappers.toDTOtoEntity(dto);
          var result = repository.save(profecion);
          response = Utils.generateOKResponse(result);
      }catch (Exception e){
          logs.error(Const.CLASS_ERROR, e);
      }
      logs.info(Const.ML_FIN + Utils.toJson(response));
      return  response;
    }
}
