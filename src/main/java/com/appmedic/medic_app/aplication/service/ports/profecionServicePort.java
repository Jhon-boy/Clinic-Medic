package com.appmedic.medic_app.aplication.service.ports;

import com.appmedic.medic_app.aplication.ports.in.dto.registrarProfecionDTO;
import com.appmedic.medic_app.infra.out.Response;

public interface profecionServicePort {
    Response<?> registrarProfecion(registrarProfecionDTO dto);
}
