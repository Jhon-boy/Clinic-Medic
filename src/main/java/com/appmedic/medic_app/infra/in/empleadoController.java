package com.appmedic.medic_app.infra.in;

import com.appmedic.medic_app.aplication.ports.in.dto.registrarEmpleadoDTO;
import com.appmedic.medic_app.aplication.service.empleadoService;
import com.appmedic.medic_app.config.logger.Loggers;
import com.appmedic.medic_app.infra.out.Response;
import com.appmedic.medic_app.util.Utils;
import com.appmedic.medic_app.util._CONST;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
/**
 * Controlador de la entidad TPEREMPLEADO
 * @author Jhon
 * @version 1.0
 * */
@RestController
@RequestMapping("${spring.mediapp.url_endpoint}/empleado")
public class empleadoController {

    private static final Loggers log = new Loggers();
    private final  empleadoService service;

    public empleadoController(empleadoService _service){
        this.service = _service;
        log.setLogger(empleadoController.class);
    }
    /**
     * ENDPOINT se encarga de registrar un nuevo EMPLEADO
     * @param dto Objeto de transferencia de datos
     * @return Un objeto de respuesta que indica el resultado del proceso de registro.
     */
    @PostMapping
    public Response<?> insertarEmpleado(@Valid @RequestBody registrarEmpleadoDTO dto, BindingResult bindingResul, HttpServletRequest request){
        log.info(_CONST.DIR_IP+ Utils.getClientIP(request) );
        if(bindingResul.hasErrors()){
            return Utils.getAllErros(bindingResul, empleadoController.class);
        }
        return service.registrarEmpleado(dto);
    }
}
