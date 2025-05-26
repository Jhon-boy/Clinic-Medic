package com.appmedic.medic_app.aplication.service.mappers;

import com.appmedic.medic_app.aplication.ports.in.dto.registrarFacturaDTO;
import com.appmedic.medic_app.domain.entity.pagos.TPAGFACTURA;
import com.appmedic.medic_app.domain.entity.pagos.TPAGFORMAPAGO;
import com.appmedic.medic_app.util.Utils;
import com.appmedic.medic_app.util._CONST;

import java.math.BigDecimal;

public class facturaMapper {


    public  static TPAGFACTURA generateFactura (registrarFacturaDTO dto, TPAGFORMAPAGO forma){
        TPAGFACTURA entity = new TPAGFACTURA();
        entity.setDescripcion(Utils.safeString(dto.descripcion()));
        entity.setFechaCreacion(Utils.getDateNow());
        entity.setFormaPago(forma);
        if(!dto.montoOriginal().isEmpty()) {
            throw new  IllegalArgumentException("EL MONTO DEBE SER UN VALOR VALIDO");
        }
        entity.setMontoOriginal(new BigDecimal(dto.montoOriginal()));
        if(!dto.aplicaIva().equals(1)){
            entity.setIva(_CONST.IVA);
            entity.setMontototal(facturaConIva(BigDecimal.valueOf(_CONST.IVA), new BigDecimal(dto.montoOriginal())));
        } else {
            entity.setIva(0);
            entity.setMontototal(new BigDecimal(dto.montoTotal()));
        }

        return  entity;

    }

    static private BigDecimal facturaConIva(BigDecimal iva, BigDecimal montoOriginal){
        BigDecimal totalIva = iva.multiply(montoOriginal).divide(BigDecimal.valueOf(100));

        return montoOriginal.add(totalIva);
    }
}
