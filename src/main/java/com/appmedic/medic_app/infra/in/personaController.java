package com.appmedic.medic_app.infra.in;

import com.appmedic.medic_app.aplication.ports.in.dto.registrarPersonaDTO;
import com.appmedic.medic_app.aplication.service.personaService;
import com.appmedic.medic_app.config.logger.Loggers;
import com.appmedic.medic_app.infra.out.Response;
import com.appmedic.medic_app.util.Utils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;


/**
 * Controlador de la entidad TPERPERSONA
 * @author Jhon
 * @version 1.0
 * */
@RestController
@RequestMapping("${spring.mediapp.url_endpoint}/persona")
public class personaController {

    private static final Loggers log = new Loggers();
    private final personaService service;

    public  personaController(personaService _service){
        log.setLogger(personaController.class);
        this.service = _service;
    }

    /**
     * ENDPOINT se encarga de registrar un nuevo persona
     * @param dto Objeto de transferencia de datos
     * @return Un objeto de respuesta que indica el resultado del proceso de registro.
     */
    @PostMapping("/registrar")
    public Response insertarPersona(@RequestBody @Valid registrarPersonaDTO dto, BindingResult bindingResult, HttpServletRequest request){
        log.info(Utils.getClientIP(request));
        if (bindingResult.hasErrors()) {
            return Utils.getAllErros(bindingResult, rolController.class);
        }
        return service.registrarPersona(dto);
    }
    @PostMapping("/listar/{estado}")
    @Cacheable(value = "track", key = "#estado")
    public  Response getUsers(@PathVariable String estado, HttpServletRequest request) throws Exception {
        log.info(Utils.getClientIP(request));
        Thread.sleep(5 * 1000);
        return  service.getUsers(estado);
    }
}
