package com.appmedic.medic_app.infra.in;
import com.appmedic.medic_app.aplication.ports.in.dto.registrarPersonaDTO;
import com.appmedic.medic_app.config.logger.Loggers;
import com.appmedic.medic_app.domain.entity.seguridad.persistence.tUsuarioRepository;
import com.appmedic.medic_app.infra.out.Response;
import org.springframework.stereotype.Controller;

/**
 * Controlador de la entidad TSEGUSUARIO
 * @author Jhon
 * @version 1.0
 * */

@Controller
public class usuarioController {


    private static final Loggers log = new Loggers();
    private final tUsuarioRepository repository;

    public  usuarioController(tUsuarioRepository _repository) {
        this.repository = _repository;
        log.setLogger(usuarioController.class);
    }

}
