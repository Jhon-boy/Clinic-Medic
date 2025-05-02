package com.appmedic.medic_app.infra.in;
import com.appmedic.medic_app.aplication.ports.in.dto.logOutDTO;
import com.appmedic.medic_app.aplication.ports.in.dto.loginDTO;
import com.appmedic.medic_app.aplication.service.loginService;
import com.appmedic.medic_app.config.logger.Loggers;
import com.appmedic.medic_app.domain.entity.seguridad.persistence.tUsuarioRepository;
import com.appmedic.medic_app.infra.out.Response;
import com.appmedic.medic_app.util.Utils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controlador del la Autentificacion del usuario
 * @author Jhon
 * @version 1.0
 * */
@RestController
@RequestMapping("${spring.mediapp.url_endpoint}/usuario")
public class usuarioController {

    private static final Loggers log = new Loggers();
    private final tUsuarioRepository repository;
    private final loginService service;


    public  usuarioController(tUsuarioRepository _repository, loginService _service) {
        this.repository = _repository;
        this.service = _service;
        log.setLogger(usuarioController.class);
    }

    @PostMapping("/login")
    public Response login(@Valid @RequestBody loginDTO dto , BindingResult bindingResult, HttpServletRequest request){
        log.info(Utils.getClientIP(request));
        if (bindingResult.hasErrors()) {
            return Utils.getAllErros(bindingResult, rolController.class);
        }
        return service.logeoUsuario(dto);
    }

    @PostMapping("/loginout")
    public Response loginOut(@Valid @RequestBody logOutDTO dto, BindingResult bindingResult, HttpServletRequest request){
        log.info(Utils.getClientIP(request));
        if (bindingResult.hasErrors()) {
            return Utils.getAllErros(bindingResult, rolController.class);
        }
        return service.logOut(dto);
    }

}
