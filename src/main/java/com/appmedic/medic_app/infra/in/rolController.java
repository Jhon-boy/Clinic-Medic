package com.appmedic.medic_app.infra.in;

import com.appmedic.medic_app.aplication.ports.in.dto.actualizarRolDTO;
import com.appmedic.medic_app.aplication.ports.in.dto.registrarRolDTO;
import com.appmedic.medic_app.aplication.service.rolService;
import com.appmedic.medic_app.config.cache.CacheConfigDB;
import com.appmedic.medic_app.config.logger.Loggers;
import com.appmedic.medic_app.infra.out.Response;
import com.appmedic.medic_app.util.Utils;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
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
public class rolController {

    private final rolService service;
    private static final Loggers log = new Loggers();

    public rolController(rolService _service) {
        this.service = _service;
        log.setLogger(rolController.class);
    }

    /**
     * ENDPOINT se encarga de registrar un nuevo rol
     *
     * @param dto Objeto de transferencia de datos
     * @return Un objeto de respuesta que indica el resultado del proceso de registro.
     */
    @PostMapping()
    public Response insertarRol(@RequestBody @Valid registrarRolDTO dto, BindingResult bindingResult, HttpServletRequest request) {
        log.info(Utils.getClientIP(request));
        if (bindingResult.hasErrors()) {
            return Utils.getAllErros(bindingResult, rolController.class);
        }
        return service.registrarRol(dto);
    }

    /**
     * ENDPOINT se encarga de actualizar un ROL
     *
     * @param dto Objeto de transferencia de datos
     * @return Un objeto de respuesta que indica el resultado del proceso de registro.
     */
    @PostMapping("/actualizar")
    @CacheEvict(value = CacheConfigDB.CACHE_NAME, key ="'allRoles'")
    public Response updateRol(@RequestBody @Valid actualizarRolDTO dto, BindingResult bindingResult, HttpServletRequest request) {
        log.info(Utils.getClientIP(request));
        if (bindingResult.hasErrors()) {
            return Utils.getAllErros(bindingResult, rolController.class);
        }
        return service.updateRol(dto);
    }

    /**
     * ENDPOINT se encarga de actualizar un ROL
     *
     * @param dto Objeto de transferencia de datos
     * @return Un objeto de respuesta que indica el resultado del proceso de registro.
     * @apiNote CACHE REDIS - TEST INCLUDE
     */
    @GetMapping("/listar")
    @Cacheable(value = CacheConfigDB.CACHE_NAME, key = "'allRoles'")
    public Response listarRol(HttpServletRequest request) throws  Exception{
        log.info(Utils.getClientIP(request));
        Thread.sleep(5 * 1000);
        return  service.listRols();
    }
}
