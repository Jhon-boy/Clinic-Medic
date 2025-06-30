package com.appmedic.medic_app.aplication.service.mappers;

import com.appmedic.medic_app.aplication.ports.in.dto.ActualizarFormaPago;
import com.appmedic.medic_app.aplication.ports.in.dto.RegistrarFormaPagoDTO;
import com.appmedic.medic_app.domain.Exceptions.ExceptionGeneric;
import com.appmedic.medic_app.domain.entity.pagos.TPAGFORMAPAGO;
import com.appmedic.medic_app.util.Utils;
import com.appmedic.medic_app.util.Const;

public class FormaPagoMappers {

    private FormaPagoMappers(){
        throw new ExceptionGeneric(Const.CLASE_NO_INSTANCIADA);
    }

    public  static TPAGFORMAPAGO dtoToEntity(RegistrarFormaPagoDTO dto){
        TPAGFORMAPAGO entity = new TPAGFORMAPAGO();

        entity.setNombre(Utils.safeString(dto.nombre()));
        entity.setDescripcion(Utils.safeString(dto.descripcion()));
        entity.setFcreacion(Utils.getDateNow());
        entity.setActivo(true);
        return  entity;
    }

    public  static TPAGFORMAPAGO updateEntity(ActualizarFormaPago dto, TPAGFORMAPAGO entity){
        entity.setNombre(Utils.safeString(dto.nombre()));
        entity.setDescripcion(Utils.safeString(dto.descripcion()));
        entity.setFactualizacion(Utils.getDateNow());
        entity.setActivo(dto.estado() == 1);
        return  entity;
    }
}
