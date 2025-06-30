package com.appmedic.medic_app.infra.in;

import com.appmedic.medic_app.config.logger.Loggers;
import com.appmedic.medic_app.infra.out.Response;
import com.appmedic.medic_app.util.Utils;
import com.appmedic.medic_app.util.Const;
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
@RequestMapping("${spring.mediapp.config.url}/isAlive")
public class IsAliveController {

    private static final Loggers logger = new Loggers();

    public IsAliveController(){
        logger.setLogger(IsAliveController.class);
    }

    @GetMapping()
    public Response isAlive(HttpServletRequest request){
        logger.info(Const.DIR_IP+ Utils.getClientIP(request) );
        return Utils.generateOKResponse(null);
    }
}
