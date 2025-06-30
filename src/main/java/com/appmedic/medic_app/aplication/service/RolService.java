package com.appmedic.medic_app.aplication.service;
import com.appmedic.medic_app.aplication.ports.in.dto.ActualizarRolDTO;
import com.appmedic.medic_app.aplication.ports.in.dto.RegistrarRolDTO;
import com.appmedic.medic_app.aplication.service.mappers.RolMappers;
import com.appmedic.medic_app.aplication.service.ports.RolServicePort;
import com.appmedic.medic_app.config.logger.Loggers;
import com.appmedic.medic_app.domain.entity.seguridad.TSEGROL;
import com.appmedic.medic_app.domain.entity.seguridad.persistence.TrolRepository;
import com.appmedic.medic_app.infra.out.Response;
import com.appmedic.medic_app.util.Utils;
import com.appmedic.medic_app.util.Const;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * La logica de Negocio para la entidad TSEGROL
 * @author  John C
 * @version  1.0
 * */
@Service
public class RolService implements RolServicePort {


    private final Loggers logs = new Loggers();
    private final TrolRepository repository;


    public RolService(TrolRepository repository){
        this.logs.setLogger(RolService.class);
        this.repository = repository;
    }

    @Override
    public Response registrarRol(RegistrarRolDTO dto) {
        logs.info(Const.ML_INI + Utils.toJson(dto));
        Response response = Utils.generateBadResponseDefault();
        try {
            TSEGROL rol = RolMappers.toDTOtoEntity(dto);
            TSEGROL rolCreado = repository.save(rol);
            response = Utils.generateOKResponse(rolCreado);
        }catch (Exception e){

            logs.error(Const.CLASS_ERROR ,e);
        }
        logs.info(Const.ML_FIN + Utils.toJson(response));
        return response;
    }


    @Override
    public Response findById(int id) {
        logs.info(Const.ML_INI + Utils.toJson(id));
        Response response = Utils.generateBadResponseDefault();
        try {
            Optional<TSEGROL> rol = repository.findById(id);
            if(rol.isPresent())
                response = Utils.generateOKResponse(rol);
        }catch (Exception e){
            logs.error(Const.COD_ERROR ,e);
        }
        logs.info(Const.ML_FIN + Utils.toJson(response));
        return response;
    }

    /**
     * Actualiza la informacion de roles
     * @param dto
     * @return
     */
    @Override
    public Response updateRol(ActualizarRolDTO dto) {
        logs.info(Const.ML_INI + Utils.toJson(dto));
        Response response = Utils.generateBadResponseDefault();
        Optional<TSEGROL> rolAux = repository.findById(dto.idRol());
        if(rolAux.isPresent()){
            TSEGROL insert = repository.save(RolMappers.updateDTOtoEntity(dto));
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
        logs.info(Const.ML_INI + "Listando Roles");
        List<TSEGROL> roles = repository.findAll();
        return  Utils.generateOKResponse(roles.stream().filter(TSEGROL::isActivo).toList());
    }

}
