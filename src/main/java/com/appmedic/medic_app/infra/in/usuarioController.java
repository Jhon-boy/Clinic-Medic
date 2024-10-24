package com.appmedic.medic_app.infra.in;
import com.appmedic.medic_app.config.logger.Loggers;
import com.appmedic.medic_app.domain.entity.seguridad.persistence.tUsuarioRepository;
import com.appmedic.medic_app.infra.out.Response;
import com.appmedic.medic_app.util._CONST;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controlador de la entidad TSEGUSUARIO
 * @author Jhon
 * @version 1.0
 * */

@RestController
@RequestMapping("${spring.mediapp.url_endpoint}/usuario")
public class usuarioController {

    private static final Loggers log = new Loggers();
    private final tUsuarioRepository repository;

    public  usuarioController(tUsuarioRepository _repository) {
        this.repository = _repository;
        log.setLogger(usuarioController.class);
    }

}
