package com.appmedic.medic_app.infra.in;

import com.appmedic.medic_app.aplication.ports.in.dto.listarPagos;
import com.appmedic.medic_app.aplication.ports.in.dto.registrarPagoDTO;
import com.appmedic.medic_app.aplication.service.pagosService;
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
 * Controlador de la entidad TPAGPAGO
 * @author Jhon
 * @version 1.0
 * */
@RestController
@RequestMapping("${spring.mediapp.config.url}/pay")
public class pagosController {

    private static final Loggers log  = new Loggers();
    private final pagosService service;


    public pagosController(pagosService service){
        this.service = service;
        log.setLogger(pagosController.class);
    }

    /**
     * ENDPOINT se encarga de registrar un nuevo pago
     * @param dto Objeto de transferencia de datos
     * @return Un objeto de respuesta que indica el resultado del proceso de registro.
     */

    @PostMapping
    public Response insert(@Valid @RequestBody registrarPagoDTO dto, HttpServletRequest request, BindingResult bindingResult){
        log.info(Utils.getClientIP(request));
        if(bindingResult.hasErrors()){
            return  Utils.getAllErros(bindingResult,personaController.class);
        }
        return service.registrarPago(dto);
    }

    /**
     * ENDPOINT se encarga de listar los pagos
     * @param dto Objeto de transferencia de datos
     * @return Un objeto de respuesta que indica el resultado del proceso de registro.
     */

    @PostMapping("/info")
    public Response list(@Valid @RequestBody listarPagos dto, HttpServletRequest request, BindingResult bindingResult){
        log.info(Utils.getClientIP(request));
        if(bindingResult.hasErrors()){
            return  Utils.getAllErros(bindingResult,personaController.class);
        }
        return service.informacionPago(dto);
    }
}
