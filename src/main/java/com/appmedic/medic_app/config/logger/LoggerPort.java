package com.appmedic.medic_app.config.logger;

public interface LoggerPort {

    void info(String message);

    void error(String message, Throwable error);
}
