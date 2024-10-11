package com.appmedic.medic_app.domain.Exceptions;

import com.appmedic.medic_app.config.logger.Loggers;
import org.springframework.web.bind.annotation.ControllerAdvice;

@ControllerAdvice
public class ExceptionsHandler {

    private final Loggers loggers = new Loggers();
}
