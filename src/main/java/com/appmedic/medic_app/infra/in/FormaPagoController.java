package com.appmedic.medic_app.infra.in;

import com.appmedic.medic_app.aplication.ports.in.dto.ActualizarFormaPago;
import com.appmedic.medic_app.aplication.ports.in.dto.RegistrarFormaPagoDTO;
import com.appmedic.medic_app.aplication.service.FormaPagoService;
import com.appmedic.medic_app.config.cache.CacheConfigDB;
import com.appmedic.medic_app.config.logger.Loggers;
import com.appmedic.medic_app.infra.out.Response;
import com.appmedic.medic_app.util.Utils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

/**
 * Controlador de la entidad TPAGFORMAPAGO
 * @author Jhon
 * @version 1.0
 * */
@RestController
@RequestMapping("${spring.mediapp.config.url}/payments")
public class FormaPagoController {

    private static final Loggers log = new Loggers();
    private final FormaPagoService service;


    public FormaPagoController(FormaPagoService service){
        this.service = service;
        log.setLogger(FormaPagoController.class);
    }
    /**
     * ENDPOINT se encarga de registrar un nuevo persona
     * @return Un objeto de respuesta que indica el resultado del proceso.
     * @apiNote CACHE TEST
     */
    @GetMapping()
    @Cacheable(value = CacheConfigDB.CACHE_PAYS)
    public Response getList() throws InterruptedException {
        Thread.sleep(5L * 100);
        return service.listarFormasPagos();
    }

    /**
     * ENDPOINT se encarga de registrar un nuevas formas de Pago
     * @return Un objeto de respuesta que indica el resultado del proceso.
     */
    @PostMapping ()
    @CacheEvict(value = CacheConfigDB.CACHE_PAYS, allEntries = true)
    public Response insert(@Valid  @RequestBody RegistrarFormaPagoDTO dto, BindingResult bindingResult, HttpServletRequest request){
        log.info(Utils.getClientIP(request));
        if(bindingResult.hasErrors()){
            return  Utils.getAllErros(bindingResult, PersonaController.class);
        }
        return  service.registrarFormaPago(dto);
    }

    /**
     * ENDPOINT se encarga de registrar un nuevas formas de Pago
     * @return Un objeto de respuesta que indica el resultado del proceso.
     */
    @PostMapping ("actualizar")
    @CacheEvict(value = CacheConfigDB.CACHE_PAYS, allEntries = true)
    public Response update(@RequestBody @Valid ActualizarFormaPago dto, BindingResult bindingResult, HttpServletRequest request){
        log.info(Utils.getClientIP(request));
        if(bindingResult.hasErrors()){
            return  Utils.getAllErros(bindingResult, PersonaController.class);
        }
        return  service.actualizarFormaPago(dto);
    }
}
