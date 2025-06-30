package com.appmedic.medic_app.aplication.ports.in.dto;

public record ActualizarCitas(
        Integer idSolicitud,
        String idPersona,
        String idProfecional,
        String motivo,
        String estado,
        String fechaDesde,
        String fechaHasta,
        String fechaReservas
) {
}
