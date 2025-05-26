package com.appmedic.medic_app.aplication.service.mappers;

import com.appmedic.medic_app.aplication.ports.in.dto.actualizarFormaPago;
import com.appmedic.medic_app.aplication.ports.in.dto.registrarFormaPagoDTO;
import com.appmedic.medic_app.domain.entity.pagos.TPAGFORMAPAGO;
import com.appmedic.medic_app.util.Utils;

public class formaPagoMappers {

    public  static TPAGFORMAPAGO dtoToEntity(registrarFormaPagoDTO dto){
        TPAGFORMAPAGO entity = new TPAGFORMAPAGO();

        entity.setNombre(Utils.safeString(dto.nombre()));
        entity.setDescripcion(Utils.safeString(dto.descripcion()));
        entity.setFcreacion(Utils.getDateNow());
        entity.setActivo(true);
        return  entity;
    }

    public  static TPAGFORMAPAGO updateEntity(actualizarFormaPago dto, TPAGFORMAPAGO entity){
        entity.setNombre(Utils.safeString(dto.nombre()));
        entity.setDescripcion(Utils.safeString(dto.descripcion()));
        entity.setFactualizacion(Utils.getDateNow());
        entity.setActivo(dto.estado() == 1);
        return  entity;
    }
}
