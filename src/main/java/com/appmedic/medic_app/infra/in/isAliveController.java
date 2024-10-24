package com.appmedic.medic_app.infra.in;

import com.appmedic.medic_app.config.logger.Loggers;
import com.appmedic.medic_app.infra.out.Response;
import com.appmedic.medic_app.util.Utils;
import com.appmedic.medic_app.util._CONST;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controlador de is Alive
 * @author Jhon
 * @version 1.0
 * */
@RestController
@RequestMapping("${spring.mediapp.url_endpoint}/isAlive")
public class isAliveController {

    private static final Loggers logger = new Loggers();

    public  isAliveController(Utils utils){
        logger.setLogger(isAliveController.class);
    }

    @GetMapping()
    public Response<?> isAlive(HttpServletRequest request){
        logger.info(_CONST.DIR_IP+ Utils.getClientIP(request) );
        return Utils.generateOKResponse(null);
    }
}
