package com.appmedic.medic_app.aplication.service;

import com.appmedic.medic_app.aplication.ports.in.dto.RegistrarEmpleadoDTO;
import com.appmedic.medic_app.aplication.service.mappers.EmpleadoMappers;
import com.appmedic.medic_app.aplication.service.ports.EmpeladoServicePort;
import com.appmedic.medic_app.config.logger.Loggers;
import com.appmedic.medic_app.domain.entity.personas.TPERPERSONA;
import com.appmedic.medic_app.domain.entity.personas.TPERPROFECION;
import com.appmedic.medic_app.domain.entity.personas.persistence.TpersonaRepository;
import com.appmedic.medic_app.domain.entity.personas.persistence.TprofecionRepository;
import com.appmedic.medic_app.infra.out.Response;
import com.appmedic.medic_app.util.Utils;
import com.appmedic.medic_app.util.Const;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * La logica de Negocio para la entidad TPEREMPLEADO
 * @author  John C
 * @version 1.0
 * */
@Service
public class EmpleadoService implements EmpeladoServicePort {
    private final Loggers logs = new Loggers();
    private final TpersonaRepository personaRepository;
    private final TprofecionRepository profecionalRepository;

    public EmpleadoService(TprofecionRepository profecionalRepository,
                           TpersonaRepository personaRepository){

        this.personaRepository = personaRepository;
        this.profecionalRepository = profecionalRepository;
    }

    @Override
    public Response registrarEmpleado(RegistrarEmpleadoDTO dto) {
        logs.info(Const.ML_INI + Utils.toJson(dto));
        Response response = Utils.generateBadResponseDefault();
        try {
            Optional<TPERPERSONA> persona = personaRepository.findById(dto.idPersona());
            Optional<TPERPROFECION> profecion = profecionalRepository.findById(dto.idProfecion());
            if(persona.isPresent() && profecion.isPresent()){
                var empleado = EmpleadoMappers.toDtoToEntity(dto, persona.get(), profecion.get());
                response = Utils.generateOKResponse(empleado.getPersona());
            }
        }catch (Exception e){
            logs.error(Const.COD_ERROR ,e);
        }
        return  response;
    }

    /**Metodo que devuelve listado de empleados
     */
    @Override
    public Response listaEmpleados() {
        return null;
    }
}
