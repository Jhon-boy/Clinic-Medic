package com.appmedic.medic_app.aplication.service;

import com.appmedic.medic_app.aplication.ports.in.dto.RegistrarFacturaDTO;
import com.appmedic.medic_app.aplication.service.mappers.FacturaMapper;
import com.appmedic.medic_app.aplication.service.ports.FacturaPort;
import com.appmedic.medic_app.config.logger.Loggers;
import com.appmedic.medic_app.domain.entity.pagos.TPAGFACTURA;
import com.appmedic.medic_app.domain.entity.pagos.TPAGFORMAPAGO;
import com.appmedic.medic_app.domain.entity.pagos.persistence.TfacturaRepository;
import com.appmedic.medic_app.domain.entity.pagos.persistence.TformaPagoRepository;
import com.appmedic.medic_app.domain.enums.Errores;
import com.appmedic.medic_app.infra.out.Response;
import com.appmedic.medic_app.util.Utils;
import com.appmedic.medic_app.util.Const;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * La logica de Negocio para la entidad TPAGFACTURA
 * @author  John C
 * @version 1.0
 * */
@Service
public class FacturaService implements FacturaPort {
    private static final Loggers logs = new Loggers();
    private final TfacturaRepository repository;
    private final TformaPagoRepository formaPagoRepository;


    public FacturaService(TfacturaRepository repository, TformaPagoRepository formaPagoRepository){
        this.repository = repository;
        this.formaPagoRepository = formaPagoRepository;
        logs.setLogger(FacturaService.class);
    }


    /**
     *
     * Metodo que registra una factura
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Response registrarFactura(RegistrarFacturaDTO dto) {
        logs.info(Const.ML_INI + Utils.toJson(dto));
        Response response = Utils.generateBadResponseDefault();
        try {
            Optional<TPAGFORMAPAGO> tpagformapago = formaPagoRepository.findById(dto.idFormaPago());
            if(tpagformapago.isPresent()){
                TPAGFACTURA factura =repository.save( FacturaMapper.generateFactura(dto, tpagformapago.get()));
                response = Utils.generateOKResponse(factura);
            } else {
                response.setMessage(Errores.RESOURCE_NOTFOUND.getMessage());
            }
        }catch (Exception e){
            logs.error(Const.COD_ERROR ,e);
            response.setMessage(Const.MENSAJE_ERROR);
            response.setData(Utils.toJson(e.getMessage()));
        }
        logs.info(Const.ML_FIN + response);
        return response;
    }


    /** Lista todas las facturas de cierto tipo
     * @return
     */
    @Override
    public Response listarFacturas(Integer tipo) {
        logs.info(Const.ML_INI);
        Response response = Utils.generateBadResponseDefault();
        try {
            List<TPAGFACTURA> facturas = repository.findAll();
            facturas = facturas.stream().filter(
                   fac -> {
                       if(tipo.equals(1)){
                           return fac.getIva()== Const.IVA;
                       } else {
                           return  fac.getIva() != Const.IVA;
                       }
                   }
            ).toList();
            response = Utils.generateOKResponse(facturas);
        }catch (Exception e){
            logs.error(Const.COD_ERROR ,e);
            response.setMessage(Const.MENSAJE_ERROR);
            response.setData(Utils.toJson(e.getMessage()));

        }
        logs.info(Const.ML_FIN + response);
        return response;
    }

    /**Metodo que recibe como parametro
     * @return
     */
    @Override
    public Response informacionFactura(Integer idFactura) {
        logs.info(Const.ML_INI);
        Response response = Utils.generateBadResponseDefault();
        try {
            Optional<TPAGFACTURA> facturas = repository.findById(idFactura);
            if(facturas.isPresent()){
                response = Utils.generateOKResponse(facturas.get());
            } else {
                logs.info("INFORMACION NO ENCONTRADA");
                response.setMessage(Errores.RESOURCE_NOTFOUND.getMessage());
            }
        }catch (Exception e){
            logs.error(Const.COD_ERROR ,e);
            response.setMessage(Const.MENSAJE_ERROR);
            response.setData(Utils.toJson(e.getMessage()));

        }
        logs.info(Const.ML_FIN + response);
        return response;
    }
}
