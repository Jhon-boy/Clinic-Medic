package com.appmedic.medic_app.aplication.service;

import com.appmedic.medic_app.aplication.ports.in.dto.actualizarCitas;
import com.appmedic.medic_app.aplication.ports.in.dto.registrarCitasDTO;
import com.appmedic.medic_app.aplication.service.mappers.citasMappers;
import com.appmedic.medic_app.aplication.service.ports.citaPort;
import com.appmedic.medic_app.config.logger.Loggers;
import com.appmedic.medic_app.domain.entity.citas.TCITCITAS;
import com.appmedic.medic_app.domain.entity.citas.persistence.tCitasRepository;
import com.appmedic.medic_app.domain.entity.personas.TPEREMPLEADO;
import com.appmedic.medic_app.domain.entity.personas.TPERPERSONA;
import com.appmedic.medic_app.domain.entity.personas.persistence.tEmpleadoRepository;
import com.appmedic.medic_app.domain.entity.personas.persistence.tPersonaRepository;
import com.appmedic.medic_app.domain.enums.EstadosCitas;
import com.appmedic.medic_app.infra.out.Response;
import com.appmedic.medic_app.domain.enums.Errores;
import com.appmedic.medic_app.util.Utils;
import com.appmedic.medic_app.util._CONST;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * La logica de Negocio para la entidad TCICITAS
 * @author  John C
 * @version 1.0
 * */
@Service
public class citaService implements citaPort {
    private final Loggers logs = new Loggers();
    private final tCitasRepository citasRepository;
    private final tPersonaRepository personaRepository;
    private final tEmpleadoRepository empleadoRepository;

    public citaService(tCitasRepository citasRepository, tPersonaRepository personaRepository,  tEmpleadoRepository empleadoRepository){
        this.citasRepository = citasRepository;
        this.personaRepository = personaRepository;
        this.empleadoRepository = empleadoRepository;
    }

    /** Inserta citas
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Response registrarCita(registrarCitasDTO dto){
        logs.info(_CONST.ML_INI + Utils.toJson(dto));
        Response response = Utils.generateBadResponseDefault();
        try {
            if(!StringUtils.hasText(dto.idPersona()) || !StringUtils.hasText(dto.idProfecional())){
                logs.info("INFORMACION ERRONEA, FALLA EN VALIDACIONES");
                response.setMessage(Errores.RESOURCE_NOTFOUND.getMessage());
                return  response;
            }
            Optional<TPERPERSONA> persona = personaRepository.findByIdentificacion(dto.idPersona());
            Optional<TPEREMPLEADO> empleado = empleadoRepository.findByPersonaIdentificacion(dto.idProfecional());
            if(!(persona.isPresent() && empleado.isPresent()) || dto.idPersona() == dto.idProfecional()){
                logs.info("INFORMACION ERRONEA, FALLA EN VALIDACIONES, NO EXISTEN DATOS NECESARIOA");
                response.setMessage(Errores.DATA_WRONG.getMessage());
                return response;
            }
            TCITCITAS citas = citasRepository.save(citasMappers.dtoToEntity(dto, persona.get(), empleado.get()));
            response =   Utils.generateOKResponse(citas);

        }catch (Exception ex){
            logs.error(_CONST.COD_ERROR ,ex);
        }
        logs.info(_CONST.ML_FIN + Utils.toJson(response));
        return response;
    }

    /** Actualiza la informacion de una Cita
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Response actualizarCita(actualizarCitas dto) {
        logs.info(_CONST.ML_INI + Utils.toJson(dto));
        Response response = Utils.generateBadResponseDefault();

        try {
            Optional<TCITCITAS> citaOpt = citasRepository.findById(dto.idSolicitud());
            if (citaOpt.isEmpty()) {
                logs.info("NO EXISTE CITA BUSCADA CON ID:" + dto.idSolicitud());
                response.setMessage(Errores.DATA_WRONG.getMessage());
                return response;
            }

            TCITCITAS cita = citaOpt.get();
            Response validacion = validarCitas(cita);
            if (!validacion.getCode().equals(_CONST.COD_OK)) {
                return validacion;
            }

            Optional<TPERPERSONA> persona = personaRepository.findByIdentificacion(dto.idPersona());
            Optional<TPEREMPLEADO> empleado = empleadoRepository.findByPersonaIdentificacion(dto.idProfecional());

            if (persona.isEmpty() || empleado.isEmpty() || dto.idPersona().equals(dto.idProfecional())) {
                logs.info("INFORMACION ERRONEA, FALLA EN VALIDACIONES");
                response.setMessage(Errores.DATA_WRONG.getMessage());
                return response;
            }
            TCITCITAS citaActualizada = citasRepository.save( citasMappers.updateToEntity(dto, persona.get(), empleado.get()));
            response = Utils.generateOKResponse(citaActualizada);

        } catch (Exception ex) {
            logs.error(_CONST.COD_ERROR, ex);
        }

        logs.info(_CONST.ML_FIN + Utils.toJson(response));
        return response;
    }

    /** Cancela la cita
     * @return
     */
    @Override
    public Response cancelarCita(Integer idCita) {
        logs.info(_CONST.ML_INI + "CANCENALDO CITA" + idCita);
        Response response = Utils.generateBadResponseDefault();
        try {
            Optional<TCITCITAS> citaOpt = citasRepository.findById(idCita);
            if (citaOpt.isEmpty()) {
                logs.info("NO EXISTE LA CITA CON ID:" + idCita);
                response.setMessage("NO EXISTE LA CITA CON ID:" + idCita);
                return response;
            }
            TCITCITAS cites = citasRepository.save(citasMappers.cancelCitas(citaOpt.get()));
            response = Utils.generateOKResponse(cites);
        } catch (Exception ex) {
            logs.error(_CONST.COD_ERROR, ex);
        }
        logs.info(_CONST.ML_FIN + Utils.toJson(response));
        return response;
    }

    /**
     * Metodo que lista las citas de acuerdo al tipo que se identifique
     * @return
     */
    @Override
    public Response listarCitas(Integer tipo) {
        List<TCITCITAS> citsList= citasRepository.findAll();
        citsList = citsList.stream().filter(
                e -> e.getEstado().equals(EstadosCitas.getNombreByID(tipo).getNombre())
        ).collect(Collectors.toList());

        return Utils.generateOKResponse(citsList);
    }
    /**
     * Metodo que valida los estados de la citas
     * */
    private Response validarCitas(TCITCITAS citas){
        if(citas.getEstado().toUpperCase(Locale.ROOT).equals(EstadosCitas.CANCELADO.getNombre())){
           return Utils.generateResponse(Errores.GENERAL.getCod(),"CITA YA CANCELADO", null );
        }
        if(Utils.getDateNow().after(citas.getFechaHasta())){
            return Utils.generateResponse(Errores.GENERAL.getCod(),"CITA CON FECHA YA CADUCADA", null );
        }
        return Utils.generateOKResponse(citas);
    }
}
