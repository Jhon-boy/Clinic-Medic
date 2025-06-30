package com.appmedic.medic_app.infra.in;

import com.appmedic.medic_app.aplication.ports.in.dto.ActualizarPersonaDTO;
import com.appmedic.medic_app.aplication.ports.in.dto.RegistrarPersonaDTO;
import com.appmedic.medic_app.aplication.service.PersonaService;
import com.appmedic.medic_app.config.cache.CacheConfigDB;
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
@RequestMapping("${spring.mediapp.config.url}/persona")
public class PersonaController {

    private static final Loggers log = new Loggers();
    private final PersonaService service;

    public PersonaController(PersonaService service){
        log.setLogger(PersonaController.class);
        this.service = service;
    }

    /**
     * ENDPOINT se encarga de registrar un nuevo persona
     * @param dto Objeto de transferencia de datos
     * @return Un objeto de respuesta que indica el resultado del proceso de registro.
     */
    @PostMapping("/registrar")
    public Response insertarPersona(@RequestBody @Valid RegistrarPersonaDTO dto, BindingResult bindingResult, HttpServletRequest request){
        log.info(Utils.getClientIP(request));
        if (bindingResult.hasErrors()) {
            return Utils.getAllErros(bindingResult, PersonaController.class);
        }
        return service.registrarPersona(dto);
    }

    /**
     * ENDPOINT se encarga de actualizat la informacion de persona
     * @param dto Objeto de transferencia de datos
     * @return Un objeto de respuesta que indica el resultado del proceso de registro.
     */
    @PostMapping("/actualizar")
    public Response update(@RequestBody @Valid ActualizarPersonaDTO dto, BindingResult bindingResult, HttpServletRequest request){
        log.info(Utils.getClientIP(request));
        if(bindingResult.hasErrors()){
            return  Utils.getAllErros(bindingResult, PersonaController.class);
        }
         return  service.updateInformation(dto);
    }
    /**
     * ENDPOINT que lista las personas acorde a su estado
     * @apiNote CACHE TEST
     * */
    @PostMapping("/listar/{estado}")
    @Cacheable(value = CacheConfigDB.CACHE_NAME, key = "#estado")
    public  Response getUsers(@PathVariable String estado, HttpServletRequest request) throws InterruptedException {
        log.info(Utils.getClientIP(request));
        Thread.sleep(5L * 1000);
        return  service.getUsers(estado);
    }
}
