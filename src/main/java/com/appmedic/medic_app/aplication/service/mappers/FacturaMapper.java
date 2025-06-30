package com.appmedic.medic_app.aplication.service.mappers;

import com.appmedic.medic_app.aplication.ports.in.dto.RegistrarFacturaDTO;
import com.appmedic.medic_app.domain.Exceptions.ExceptionGeneric;
import com.appmedic.medic_app.domain.entity.pagos.TPAGFACTURA;
import com.appmedic.medic_app.domain.entity.pagos.TPAGFORMAPAGO;
import com.appmedic.medic_app.util.Utils;
import com.appmedic.medic_app.util.Const;

import java.math.BigDecimal;

public class FacturaMapper {

    private  FacturaMapper(){
        throw new ExceptionGeneric(Const.CLASE_NO_INSTANCIADA);
    }
    public  static TPAGFACTURA generateFactura (RegistrarFacturaDTO dto, TPAGFORMAPAGO forma){
        TPAGFACTURA entity = new TPAGFACTURA();
        entity.setDescripcion(Utils.safeString(dto.descripcion()));
        entity.setFechaCreacion(Utils.getDateNow());
        entity.setFormaPago(forma);
        if(!dto.montoOriginal().isEmpty()) {
            throw new  IllegalArgumentException("EL MONTO DEBE SER UN VALOR VALIDO");
        }
        entity.setMontoOriginal(new BigDecimal(dto.montoOriginal()));
        if(!dto.aplicaIva().equals(1)){
            entity.setIva(Const.IVA);
            entity.setMontototal(facturaConIva(BigDecimal.valueOf(Const.IVA), new BigDecimal(dto.montoOriginal())));
        } else {
            entity.setIva(0);
            entity.setMontototal(new BigDecimal(dto.montoTotal()));
        }

        return  entity;

    }

    static  BigDecimal facturaConIva(BigDecimal iva, BigDecimal montoOriginal){
        BigDecimal totalIva = iva.multiply(montoOriginal).divide(BigDecimal.valueOf(100));

        return montoOriginal.add(totalIva);
    }
}
