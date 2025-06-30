package com.appmedic.medic_app.infra.in;

import com.appmedic.medic_app.aplication.ports.in.dto.ActualizarRolDTO;
import com.appmedic.medic_app.aplication.ports.in.dto.RegistrarRolDTO;
import com.appmedic.medic_app.aplication.service.RolService;
import com.appmedic.medic_app.config.logger.Loggers;
import com.appmedic.medic_app.infra.out.Response;
import com.appmedic.medic_app.util.Utils;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
/**
 * Controlador de la entidad TSEROL
 * @author Jhon
 * @version 1.0
 * */
@RestController
@RequestMapping("${spring.mediapp.config.url}/rol")
public class RolController {

    private final RolService service;
    private static final Loggers log = new Loggers();
    public RolController(RolService service) {
        this.service = service;
        log.setLogger(RolController.class);
    }

    /**
     * ENDPOINT se encarga de registrar un nuevo rol
     * @param dto Objeto de transferencia de datos
     * @return Un objeto de respuesta que indica el resultado del proceso de registro.
     */
    @PostMapping()
    public Response insertarRol(@RequestBody @Valid RegistrarRolDTO dto, BindingResult bindingResult, HttpServletRequest request){
        log.info(Utils.getClientIP(request));
        if (bindingResult.hasErrors()) {
         return Utils.getAllErros(bindingResult, RolController.class);
        }
        return service.registrarRol(dto);
    }

    /**
     * ENDPOINT se encarga de actualizar un  rol
     * @param dto Objeto de transferencia de datos
     * @return Un objeto de respuesta que indica el resultado del proceso de registro.
     */
    @PostMapping("/actualizar")
    public Response actualizarRol(@RequestBody @Valid ActualizarRolDTO dto, BindingResult bindingResult, HttpServletRequest request){
        log.info(Utils.getClientIP(request));
        if (bindingResult.hasErrors()) {
            return Utils.getAllErros(bindingResult, RolController.class);
        }
        return service.updateRol(dto);
    }
    /**
     * ENDPOINT se encarga de listar los roles
     * @return Un objeto de respuesta que indica el resultado del proceso de registro.
     */
    @GetMapping
    public Response listarRoles(HttpServletRequest request){
        log.info(Utils.getClientIP(request));
        return  service.listRols();
    }
}
