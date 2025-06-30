package com.appmedic.medic_app.infra.in;

import com.appmedic.medic_app.aplication.ports.in.dto.ActualizarCitas;
import com.appmedic.medic_app.aplication.ports.in.dto.RegistrarCitasDTO;
import com.appmedic.medic_app.aplication.service.CitaService;
import com.appmedic.medic_app.config.logger.Loggers;
import com.appmedic.medic_app.infra.out.Response;
import com.appmedic.medic_app.util.Utils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

/**
 * Controlador de la entidad TCITCITAS
 * @author Jhon
 * @version 1.0
 * */
@RestController
@RequestMapping("${spring.mediapp.config.url}/citas")
public class CitasController {
    private static final Loggers log = new Loggers();
    private final CitaService service;

    public CitasController(CitaService service){
        this.service = service;
        log.setLogger(CitasController.class);
    }
    /**
     * ENDPOINT se encarga de registrar una nuevo CITA
     * @param dto Objeto de transferencia de datos
     * @return Un objeto de respuesta que indica el resultado del proceso de registro.
     */
    @PostMapping()
    public Response create(@RequestBody RegistrarCitasDTO dto, HttpServletRequest request, BindingResult bindingResult){
        log.info(Utils.getClientIP(request));
        if(bindingResult.hasErrors()){
            return  Utils.getAllErros(bindingResult, CitasController.class);
        }
        return  service.registrarCita(dto);
    }

    /**
     * ENDPOINT se encarga de registrar una nuevo CITA
     * @param dto Objeto de transferencia de datos
     * @return Un objeto de respuesta que indica el resultado del proceso de registro.
     */
    @PostMapping("/actualizar")
    public Response update(@RequestBody @Valid ActualizarCitas dto, HttpServletRequest request, BindingResult bindingResult){
        log.info(Utils.getClientIP(request));
        if(bindingResult.hasErrors()){
            return  Utils.getAllErros(bindingResult, CitasController.class);
        }
        return  service.actualizarCita(dto);
    }

    /**
     * ENDPOINT se encarga de canceñar una CITA
     * @param id Id de la CITA
     * @return Un objeto de respuesta que indica el resultado del proceso de registro.
     */
    @GetMapping("/cancelar/{id}")
    public Response cancel(@PathVariable  Integer id, HttpServletRequest request){
        log.info(Utils.getClientIP(request));
        return  service.cancelarCita(id);
    }
    /**
     * ENDPOINT se encarga de listar las citas acorde al estado
     * @param tipo tipo  de estados de las CITA
     * @return Un objeto de respuesta que indica el resultado del proceso de registro.
     */
    @GetMapping("/listar/{tipo}")
    public Response list(@PathVariable  Integer tipo, HttpServletRequest request){
        log.info(Utils.getClientIP(request));
        return  service.cancelarCita(tipo);
    }
}
