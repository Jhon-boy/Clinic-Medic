package com.appmedic.medic_app.infra.in;

import com.appmedic.medic_app.aplication.ports.in.dto.registrarRolDTO;
import com.appmedic.medic_app.aplication.service.rolService;
import com.appmedic.medic_app.config.logger.Loggers;
import com.appmedic.medic_app.infra.out.Response;
import com.appmedic.medic_app.util.Utils;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import jakarta.validation.Valid;
/**
 * Controlador de la entidad TSEROL
 * @author Jhon
 * @version 1.0
 * */
@RestController
@RequestMapping("api/v1/mediapp/rol")
public class rolController {

    private final rolService service;
    private static final Loggers log = new Loggers();
    public  rolController(rolService _service) {
        this.service = _service;
        log.setLogger(rolController.class);
    }

    /**
     * ENDPOINT se encarga de registrar un nuevo rol
     * @param dto Objeto de transferencia de datos
     * @return Un objeto de respuesta que indica el resultado del proceso de registro.
     */
    @PostMapping()
    public Response<?> insertarRol(@RequestBody @Valid registrarRolDTO dto, BindingResult bindingResult, HttpServletRequest request){
        log.info(Utils.getClientIP(request));
        if (bindingResult.hasErrors()) {
         return Utils.getAllErros(bindingResult, rolController.class);
        }
        return service.registrarRol(dto);
    }

}
