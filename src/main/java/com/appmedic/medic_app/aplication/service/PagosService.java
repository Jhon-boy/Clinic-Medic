package com.appmedic.medic_app.aplication.service;

import com.appmedic.medic_app.aplication.ports.in.dto.ListarPagos;
import com.appmedic.medic_app.aplication.ports.in.dto.RegistrarPagoDTO;
import com.appmedic.medic_app.aplication.service.mappers.PagoMapper;
import com.appmedic.medic_app.aplication.service.ports.PagoPort;
import com.appmedic.medic_app.config.logger.Loggers;
import com.appmedic.medic_app.domain.entity.pagos.TPAGFACTURA;
import com.appmedic.medic_app.domain.entity.pagos.TPAGFORMAPAGO;
import com.appmedic.medic_app.domain.entity.pagos.TPAGPAGO;
import com.appmedic.medic_app.domain.entity.pagos.persistence.TfacturaRepository;
import com.appmedic.medic_app.domain.entity.pagos.persistence.TformaPagoRepository;
import com.appmedic.medic_app.domain.entity.pagos.persistence.TpagoRepository;
import com.appmedic.medic_app.domain.entity.personas.TPERPERSONA;
import com.appmedic.medic_app.domain.entity.personas.persistence.TpersonaRepository;
import com.appmedic.medic_app.infra.out.Response;
import com.appmedic.medic_app.util.Utils;
import com.appmedic.medic_app.util.Const;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * La logica de Negocio para la entidad TPAGPAGO
 * @author  John C
 * @version 1.0
 * */
@Service
public class PagosService implements PagoPort {

    private static final Loggers logs = new Loggers();
    private final TpagoRepository repository;
    private final TformaPagoRepository formaPagoRepository;
    private final TfacturaRepository facturaRepository;
    private final TpersonaRepository userRepository;


    public PagosService(TpagoRepository repository, TformaPagoRepository formaPagoRepository , TpersonaRepository userRepository, TfacturaRepository facturaRepository){
        logs.setLogger(PagosService.class);
        this.repository = repository;
        this.formaPagoRepository = formaPagoRepository;
        this.facturaRepository = facturaRepository;
        this.userRepository = userRepository;
    }


    /** Registra el Pago
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public Response registrarPago(RegistrarPagoDTO dto) {
        logs.info(Const.ML_INI + Utils.toJson(dto));
        Response response = Utils.generateBadResponseDefault();
        try {
            Optional<TPAGFACTURA> factura = facturaRepository.findById(dto.idFactura());
            Optional<TPAGFORMAPAGO> forma = formaPagoRepository.findById(dto.idFormaPago());
            Optional<TPERPERSONA> usuario = userRepository.findByIdentificacion(dto.beneficario());
            if(factura.isEmpty() || forma.isEmpty() || usuario.isEmpty()){
                logs.info("NO EXISTE INFORMACION NECESARIA");
                response.setMessage(Const.MENSAJE_ERROR);
            } else {
                TPAGPAGO newPay = repository.save(PagoMapper.toDtoToEntity(factura.get(), forma.get(), dto, usuario.get()));
                response = Utils.generateOKResponse(newPay);
            }
        }catch (Exception e){
            logs.error(Const.COD_ERROR ,e);
            response.setMessage(Const.MENSAJE_ERROR);
            response.setData(Utils.toJson(e.getMessage()));
        }
        logs.info(Const.ML_FIN + response);
        return response;
    }

    /** Devuelve el listado de Pagos acorde a una fecha, tamanio y numero de elementos
     * @return
     */
    @Override
    public Response informacionPago(ListarPagos dto) {
        logs.info(Const.ML_INI + Utils.toJson(dto));
        Response response = Utils.generateBadResponseDefault();
        try {
            int pageNumer = (dto.page() == null || dto.page() < 0) ? 0 : dto.page();
            int size = (dto.size() == null || dto.size() <= 0) ? 10 : dto.size();
            Pageable page = PageRequest.of(pageNumer, size);
            Page<TPAGPAGO> pagosList = repository.findAll(page);
            response = Utils.generateOKResponse(pagosList.stream().filter(
                    x -> x.getFechaCreacion().after(Utils.getDateNow(dto.fechaIncio())) && x.getFechaCreacion().before(Utils.getDateNow(dto.fechsFin()))
            ).toList());
        }catch (Exception e){
            logs.error(Const.COD_ERROR ,e);
            response.setMessage(Const.MENSAJE_ERROR);
            response.setData(Utils.toJson(e.getMessage()));
        }
        return response;
    }
}
