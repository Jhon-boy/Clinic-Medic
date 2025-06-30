package com.appmedic.medic_app.aplication.service.ports;

import com.appmedic.medic_app.aplication.ports.in.dto.RegistrarProfecionDTO;
import com.appmedic.medic_app.infra.out.Response;

public interface ProfecionServicePort {
    Response registrarProfecion(RegistrarProfecionDTO dto);
}
