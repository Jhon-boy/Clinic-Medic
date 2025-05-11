package com.appmedic.medic_app.aplication.service;
import com.appmedic.medic_app.aplication.ports.in.dto.actualizarRolDTO;
import com.appmedic.medic_app.aplication.ports.in.dto.registrarRolDTO;
import com.appmedic.medic_app.aplication.service.mappers.rolMappers;
import com.appmedic.medic_app.aplication.service.ports.rolServicePort;
import com.appmedic.medic_app.config.logger.Loggers;
import com.appmedic.medic_app.domain.entity.seguridad.TSEGROL;
import com.appmedic.medic_app.domain.entity.seguridad.persistence.tRolRepository;
import com.appmedic.medic_app.infra.out.Response;
import com.appmedic.medic_app.util.Utils;
import com.appmedic.medic_app.util._CONST;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * La logica de Negocio para la entidad TSEGROL
 * @author  John C
 * @version  1.0
 * */
@Service
public class rolService implements rolServicePort {


    private final Loggers logs = new Loggers();
    private final tRolRepository repository;


    private rolService(tRolRepository _repository){
        this.logs.setLogger(rolService.class);
        this.repository = _repository;
    }

    @Override
    public Response registrarRol(registrarRolDTO dto) {
        logs.info(_CONST.ML_INI + Utils.toJson(dto));
        Response response = Utils.generateBadResponseDefault();
        try {
            TSEGROL rol = rolMappers.toDTOtoEntity(dto);
            TSEGROL rolCreado = repository.save(rol);
            response = Utils.generateOKResponse(rolCreado);
        }catch (Exception e){
            logs.error(_CONST.CLASS_ERROR ,e);
        }
        logs.info(_CONST.ML_FIN + Utils.toJson(response));
        return response;
    }


    @Override
    public Response findById(int id) {
        logs.info(_CONST.ML_INI + Utils.toJson(id));
        Response response = Utils.generateBadResponseDefault();
        try {
            Optional<TSEGROL> rol = repository.findById(id);
            if(rol.isPresent())
                response = Utils.generateOKResponse(rol);
        }catch (Exception e){
            logs.error(_CONST.COD_ERROR ,e);
        }
        logs.info(_CONST.ML_FIN + Utils.toJson(response));
        return response;
    }

    /**
     * Actualiza la informacion de roles
     * @param dto
     * @return
     */
    @Override
    public Response updateRol(actualizarRolDTO dto) {
        logs.info(_CONST.ML_INI + Utils.toJson(dto));
        Response response = Utils.generateBadResponseDefault();
        Optional<TSEGROL> rolAux = repository.findById(dto.idRol());
        if(rolAux.isPresent()){
            TSEGROL insert = repository.save(rolMappers.updateDTOtoEntity(dto));
            return  Utils.generateOKResponse(insert);
        }
        response.setMessage("RECURSO NO ENCONTRADO");
        return response;
    }

    /** Lista los roles activos
     * @return
     */
    @Override
    public Response listRols() {
        logs.info(_CONST.ML_INI + "Listando Roles");
        List<TSEGROL> roles = repository.findAll();
        return  Utils.generateOKResponse(roles.stream().filter(e -> e.isActivo()).collect(Collectors.toList()));
    }

}
