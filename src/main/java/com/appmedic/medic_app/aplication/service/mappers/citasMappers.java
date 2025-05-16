package com.appmedic.medic_app.aplication.service.mappers;

import com.appmedic.medic_app.aplication.ports.in.dto.actualizarCitas;
import com.appmedic.medic_app.aplication.ports.in.dto.registrarCitasDTO;
import com.appmedic.medic_app.domain.entity.citas.TCITCITAS;
import com.appmedic.medic_app.domain.entity.personas.TPEREMPLEADO;
import com.appmedic.medic_app.domain.entity.personas.TPERPERSONA;
import com.appmedic.medic_app.domain.enums.EstadosCitas;
import com.appmedic.medic_app.util.Utils;

public class citasMappers {

    public static TCITCITAS dtoToEntity(registrarCitasDTO dto, TPERPERSONA persona, TPEREMPLEADO empleado){
        TCITCITAS citas = new TCITCITAS();
        citas.setEstado(EstadosCitas.RESERVADO.getNombre());
        citas.setIdPersona(persona);
        citas.setIdProfecional(empleado);
        citas.setFechaReserva(Utils.getDateNow());
        citas.setFechaDesde(Utils.getDateNow(dto.fechaDesde()));
        citas.setFechaHasta(Utils.getDateNow(dto.fechaHasta()));
        citas.setMotivo( Utils.safeString(dto.motivo()));
        return  citas;
    }
    public static TCITCITAS updateToEntity(actualizarCitas dto, TPERPERSONA persona, TPEREMPLEADO empleado){
        TCITCITAS citas = new TCITCITAS();
        citas.setId(dto.idSolicitud());
        citas.setEstado(EstadosCitas.RESERVADO.getNombre());
        citas.setIdPersona(persona);
        citas.setIdProfecional(empleado);
        citas.setFechaDesde(Utils.getDateNow(dto.fechaDesde()));
        citas.setFechaHasta(Utils.getDateNow(dto.fechaHasta()));
        citas.setMotivo(Utils.safeString(dto.motivo()));
        return  citas;
    }
    public static  TCITCITAS cancelCitas(TCITCITAS cita){
        TCITCITAS citas  = new TCITCITAS();
        cita.setMotivo(EstadosCitas.CANCELADO.getNombre());
        cita.setFechaActualizacion(Utils.getDateNow());
        return citas;
    }
}
