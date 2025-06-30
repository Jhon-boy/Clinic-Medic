package com.appmedic.medic_app.aplication.service;

import com.appmedic.medic_app.aplication.ports.in.dto.ActualizarFormaPago;
import com.appmedic.medic_app.aplication.ports.in.dto.RegistrarFormaPagoDTO;
import com.appmedic.medic_app.aplication.service.mappers.FormaPagoMappers;
import com.appmedic.medic_app.aplication.service.ports.FormaPago;
import com.appmedic.medic_app.config.logger.Loggers;
import com.appmedic.medic_app.domain.entity.pagos.TPAGFORMAPAGO;
import com.appmedic.medic_app.domain.entity.pagos.persistence.TformaPagoRepository;
import com.appmedic.medic_app.domain.enums.Errores;
import com.appmedic.medic_app.infra.out.Response;
import com.appmedic.medic_app.util.Utils;
import com.appmedic.medic_app.util.Const;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Optional;

/**
 * La logica de Negocio para la entidad TFORMAPAGO
 * @author  John C
 * @version 1.0
 * */
@Service
public class FormaPagoService implements FormaPago {
    private final Loggers logs = new Loggers();
    private final TformaPagoRepository repository;

    public FormaPagoService(TformaPagoRepository repository){
        this.repository = repository;
        logs.setLogger(FormaPagoService.class);
    }

    /**
     * Metodo que registrar una forma de Pago
     * @param dto
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Response registrarFormaPago(RegistrarFormaPagoDTO dto) {
        logs.info(Const.ML_INI + Utils.toJson(dto));
        Response response = Utils.generateBadResponseDefault();
        try {
            TPAGFORMAPAGO pago = repository.save(FormaPagoMappers.dtoToEntity(dto));
            response = Utils.generateOKResponse(pago);
        }catch (Exception ex){
            logs.error(Const.COD_ERROR ,ex);
        }
        logs.info(Const.ML_FIN + Utils.toJson(response));
        return response;
    }

    /**
     * Metodo que actualiza la informacion de una forma de Pago
     * @param dto
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Response actualizarFormaPago(ActualizarFormaPago dto) {
        logs.info(Const.ML_INI + Utils.toJson(dto));
        Response response = Utils.generateBadResponseDefault();

        try {
            Optional<TPAGFORMAPAGO> pago = repository.findById(dto.idFormaPago());
            if(pago.isEmpty()){
                logs.info(Const.ML_FIN + "NO EXISTE FORMA DE PAGO PARA EL ID: " + dto.idFormaPago());
                response.setMessage(Errores.DATA_WRONG.getMessage());
                response.setData("NO EXISTE INFORMACION");
                return  response;
            }
            TPAGFORMAPAGO newPago = repository.save(FormaPagoMappers.updateEntity(dto, pago.get()));
            response = Utils.generateOKResponse(newPago);
        }catch (Exception ex){
            logs.error(Const.COD_ERROR ,ex);
        }
        logs.info(Const.ML_FIN + Utils.toJson(response));
        return response;
    }

    /**
     * Metod que lista los formas de pagos segun su estado
     * @return
     */
    @Override
    public Response listarFormasPagos() {
        logs.info(Const.ML_INI + "LISTA DE FORMAS DE PAGOS");
        Response response = Utils.generateBadResponseDefault();
        try {
            List<TPAGFORMAPAGO> pagos = repository.findAll();
            List<LinkedHashMap<String, Object>> listaPagos = new ArrayList<>();

            LinkedHashMap<String, Object> listaActivos = new LinkedHashMap<>();
            listaActivos.put("estado", "ACTIVOS");
            listaActivos.put("lista", pagos.stream().filter(TPAGFORMAPAGO::isActivo).toList());
            LinkedHashMap<String, Object> listaNoActivos = new LinkedHashMap<>();
            listaNoActivos.put("estado", "NO ACTIVOS");
            listaNoActivos.put("lista", pagos.stream().filter(e -> !e.isActivo()).toList());

            listaPagos.add(listaActivos);
            listaPagos.add(listaNoActivos);
            response = Utils.generateOKResponse(listaPagos);

        }catch (Exception ex){
            logs.error(Const.COD_ERROR ,ex);
        }
        logs.info(Const.ML_FIN + Utils.toJson(response));
        return response;
    }
}
