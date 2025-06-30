package com.appmedic.medic_app.aplication.ports.in.dto;

public record ListarPagos(
        String fechaIncio,
        String fechsFin,
        Integer page,
        Integer size
) {
}
