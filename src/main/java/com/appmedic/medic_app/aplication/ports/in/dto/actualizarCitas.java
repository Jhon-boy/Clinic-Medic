package com.appmedic.medic_app.aplication.ports.in.dto;

public record actualizarCitas(
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
