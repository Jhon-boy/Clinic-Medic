package com.appmedic.medic_app.infra.in;
import com.appmedic.medic_app.aplication.ports.in.dto.LogOutDTO;
import com.appmedic.medic_app.aplication.ports.in.dto.LoginDTO;
import com.appmedic.medic_app.aplication.service.LoginService;
import com.appmedic.medic_app.config.logger.Loggers;
import com.appmedic.medic_app.infra.out.Response;
import com.appmedic.medic_app.util.Utils;
import jakarta.servlet.http.HttpServletRequest;
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
@RequestMapping("${spring.mediapp.config.url}/usuario")
public class UsuarioController {

    private static final Loggers log = new Loggers();
    private final LoginService service;


    public UsuarioController(LoginService service) {
        this.service = service;
        log.setLogger(UsuarioController.class);
    }

    @PostMapping("/login")
    public Response login(@Valid @RequestBody LoginDTO dto , BindingResult bindingResult, HttpServletRequest request){
        log.info(Utils.getClientIP(request));
        if (bindingResult.hasErrors()) {
            return Utils.getAllErros(bindingResult, RolController.class);
        }
        return service.logeoUsuario(dto);
    }

    @PostMapping("/loginout")
    public Response loginOut(@Valid @RequestBody LogOutDTO dto, BindingResult bindingResult, HttpServletRequest request){
        log.info(Utils.getClientIP(request));
        if (bindingResult.hasErrors()) {
            return Utils.getAllErros(bindingResult, RolController.class);
        }
        return service.logOut(dto);
    }

}
