package com.appmedic.medic_app.infra.in;

import com.appmedic.medic_app.aplication.ports.in.dto.RegistrarProfecionDTO;
import com.appmedic.medic_app.aplication.service.ProfecionService;
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
 * Controlador de la entidad TPERPROFECION
 * @author Jhon
 * @version 1.0
 * */
@RestController
@RequestMapping("${spring.mediapp.config.url}/profecion")
public class ProfecionController {
    private static final Loggers log = new Loggers();
    private final ProfecionService service;

    public ProfecionController(ProfecionService service){
        log.setLogger(ProfecionController.class);
        this.service = service;
    }
    /**
     * ENDPOINT se encarga de registrar un nuevo PROFECION
     * @param dto Objeto de transferencia de datos
     * @return Un objeto de respuesta que indica el resultado del proceso de registro.
     */
    @PostMapping()
    public Response insertarProfecion(@Valid @RequestBody RegistrarProfecionDTO dto, BindingResult bindingResult, HttpServletRequest request){
        log.info(Utils.getClientIP(request));
        if (bindingResult.hasErrors()) {
            return Utils.getAllErros(bindingResult, RolController.class);
        }
        return service.registrarProfecion(dto);
    }
}
