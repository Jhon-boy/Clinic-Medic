package com.appmedic.medic_app.aplication.service.mappers;

import com.appmedic.medic_app.aplication.ports.in.dto.RegistrarPagoDTO;
import com.appmedic.medic_app.domain.Exceptions.ExceptionGeneric;
import com.appmedic.medic_app.domain.entity.pagos.TPAGFACTURA;
import com.appmedic.medic_app.domain.entity.pagos.TPAGFORMAPAGO;
import com.appmedic.medic_app.domain.entity.pagos.TPAGPAGO;
import com.appmedic.medic_app.domain.entity.personas.TPERPERSONA;
import com.appmedic.medic_app.util.Utils;
import com.appmedic.medic_app.util.Const;

import java.math.BigDecimal;

public class PagoMapper {

    private PagoMapper(){
        throw new ExceptionGeneric(Const.CLASE_NO_INSTANCIADA);
    }
    public static TPAGPAGO toDtoToEntity(TPAGFACTURA factura, TPAGFORMAPAGO forma, RegistrarPagoDTO dto, TPERPERSONA usuario){
        TPAGPAGO entity = new TPAGPAGO();
        entity.setFactura(factura);
        entity.setFormaPago(forma);
        entity.setMonto(new BigDecimal(dto.monto()));
        entity.setFechaCreacion(Utils.getDateNow());
        entity.setUsuario(usuario);
        return  entity;
    }
}
