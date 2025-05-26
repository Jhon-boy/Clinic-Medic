package com.appmedic.medic_app.aplication.ports.in.dto;

import org.springframework.util.StringUtils;

public record listarPagos(
        String fechaIncio,
        String fechsFin,
        Integer page,
        Integer size
) {
}
