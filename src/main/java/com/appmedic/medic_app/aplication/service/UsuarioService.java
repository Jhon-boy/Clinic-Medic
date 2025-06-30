package com.appmedic.medic_app.aplication.service;

import com.appmedic.medic_app.aplication.ports.in.dto.ActualizarPersonaDTO;
import com.appmedic.medic_app.aplication.ports.in.dto.RegistrarPersonaDTO;
import com.appmedic.medic_app.aplication.service.mappers.UsuarioMappers;
import com.appmedic.medic_app.aplication.service.ports.UsuarioServicePort;
import com.appmedic.medic_app.config.logger.Loggers;
import com.appmedic.medic_app.domain.entity.personas.TPERPERSONA;
import com.appmedic.medic_app.domain.entity.seguridad.TSEGROL;
import com.appmedic.medic_app.domain.entity.seguridad.TSEGUSUARIO;
import com.appmedic.medic_app.domain.entity.seguridad.persistence.TrolRepository;
import com.appmedic.medic_app.domain.entity.seguridad.persistence.TusuarioRepository;
import com.appmedic.medic_app.domain.enums.Estados;
import com.appmedic.medic_app.infra.out.Response;
import com.appmedic.medic_app.util.Utils;
import com.appmedic.medic_app.util.Const;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * La logica de Negocio para la entidad TSEGUSUARIO
 * @author  John C
 * @version 1.0
 * */
@Service
public class UsuarioService implements UsuarioServicePort {

    private final Loggers logs = new Loggers();
    private final TusuarioRepository repository;
    private final TrolRepository rolRepository;

    public UsuarioService(TusuarioRepository repository, TrolRepository rolService) {
        this.repository = repository;
        this.rolRepository = rolService;
        this.logs.setLogger(UsuarioService.class);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Response registrarUsuario(RegistrarPersonaDTO dto, TPERPERSONA persona) {
        logs.info(Const.ML_INI + Utils.toJson(dto));
        Response response =  Utils.generateBadResponseDefault();
        try{
            Optional<TSEGROL> rolOptional = rolRepository.findById(dto.idRol());
            if(rolOptional.isPresent()){
                TSEGUSUARIO usuario = UsuarioMappers.toDTOtoEntity(dto, rolOptional.get(), persona);
                repository.save(usuario);
                response =Utils.generateOKResponse(usuario);
            }
        }catch (Exception e){
            logs.error(Const.COD_ERROR ,e);
            response.setMessage(e.getMessage());
        }
        logs.info(Const.ML_FIN + Utils.toJson(response));
        return response;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Response updateUser (ActualizarPersonaDTO dto, TPERPERSONA persona){
        logs.info(Const.ML_INI + Utils.toJson(dto));
        Response response =  Utils.generateBadResponseDefault();
        try {
            Optional<TSEGROL> rolOptional = rolRepository.findById(dto.idRol());
            if(rolOptional.isPresent()){
                TSEGUSUARIO usuario = UsuarioMappers.toDTOtoEntityUpdate(dto, rolOptional.get(), persona);
                repository.save(usuario);
                response =Utils.generateOKResponse(usuario);
            }
        }catch (Exception e){
            logs.error(Const.COD_ERROR ,e);
            response.setMessage(e.getMessage());
        }
        logs.info(Const.ML_FIN + Utils.toJson(response));
        return  response;
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

    /** Method obtain all Users filter by state
     * @param estado
     * @return
     */
    @Override
    public Response getAllUsers(String estado) {
        logs.info(Const.ML_INI + estado);
        int stateFilter = Estados.getByName(estado).getId();
        List<TSEGUSUARIO> users = repository.findAll();
        switch (stateFilter){
            case 1: //Active
                users =users.stream().filter(TSEGUSUARIO::isActivo).toList();
                break;
            case 0: //In Active
                users =users.stream().filter(user -> !user.isActivo()).toList();
                break;
            case 2: //All users
                break;
                default:
                     return  Utils.generateBadResponseDefault();
        }
        logs.info(Const.ML_FIN + estado);
        return Utils.generateOKResponse(UsuarioMappers.toListUsuarioDto(users));
    }

}
