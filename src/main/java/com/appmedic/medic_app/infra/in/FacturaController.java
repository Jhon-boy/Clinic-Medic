package com.appmedic.medic_app.infra.in;

import com.appmedic.medic_app.aplication.ports.in.dto.RegistrarFacturaDTO;
import com.appmedic.medic_app.aplication.service.FacturaService;
import com.appmedic.medic_app.config.logger.Loggers;
import com.appmedic.medic_app.infra.out.Response;
import com.appmedic.medic_app.util.Utils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

/**
 * Controlador de la entidad TPAGPAGO
 * @author Jhon
 * @version 1.0
 * */
@RestController
@RequestMapping("${spring.mediapp.config.url}/bills")
public class FacturaController {

    private static final Loggers log = new Loggers();
    private final FacturaService service;

    public FacturaController(FacturaService service) {
        this.service = service;
        log.setLogger(FacturaController.class);
    }

    /**
     * ENDPOINT se encarga de registrar un nueva factura
     *
     * @param dto Objeto de transferencia de datos
     * @return Un objeto de respuesta que indica el resultado del proceso de registro.
     */

    @PostMapping
    public Response insert(@Valid @RequestBody RegistrarFacturaDTO dto, HttpServletRequest request, BindingResult bindingResult) {
        log.info(Utils.getClientIP(request));
        if (bindingResult.hasErrors()) {
            return Utils.getAllErros(bindingResult, PersonaController.class);
        }
        return service.registrarFactura(dto);
    }

    /**
     * ENDPOINT se encarga de registrar un nueva factura
     * @param request Numero entero que indica el tipo
     * @return Un objeto de respuesta que indica el resultado del proceso de registro.
     */
    @GetMapping("/info/{tipo}")
    public Response list(@PathVariable Integer tipo, HttpServletRequest request){
        log.info(Utils.getClientIP(request));
        return service.informacionFactura(tipo);
    }
}
