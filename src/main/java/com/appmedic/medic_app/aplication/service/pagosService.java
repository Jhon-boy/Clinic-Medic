package com.appmedic.medic_app.aplication.service;

import com.appmedic.medic_app.aplication.ports.in.dto.listarPagos;
import com.appmedic.medic_app.aplication.ports.in.dto.registrarPagoDTO;
import com.appmedic.medic_app.aplication.service.mappers.pagoMapper;
import com.appmedic.medic_app.aplication.service.ports.pagoPort;
import com.appmedic.medic_app.config.logger.Loggers;
import com.appmedic.medic_app.domain.entity.pagos.TPAGFACTURA;
import com.appmedic.medic_app.domain.entity.pagos.TPAGFORMAPAGO;
import com.appmedic.medic_app.domain.entity.pagos.TPAGPAGO;
import com.appmedic.medic_app.domain.entity.pagos.persistence.tFacturaRepository;
import com.appmedic.medic_app.domain.entity.pagos.persistence.tFormaPagoRepository;
import com.appmedic.medic_app.domain.entity.pagos.persistence.tPagoRepository;
import com.appmedic.medic_app.domain.entity.personas.TPERPERSONA;
import com.appmedic.medic_app.domain.entity.personas.persistence.tPersonaRepository;
import com.appmedic.medic_app.domain.entity.seguridad.TSEGUSUARIO;
import com.appmedic.medic_app.domain.entity.seguridad.persistence.tUsuarioRepository;
import com.appmedic.medic_app.infra.out.Response;
import com.appmedic.medic_app.util.Utils;
import com.appmedic.medic_app.util._CONST;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.stream.Collectors;

/**
 * La logica de Negocio para la entidad TPAGPAGO
 * @author  John C
 * @version 1.0
 * */
@Service
public class pagosService implements pagoPort {

    private static final Loggers logs = new Loggers();
    private final tPagoRepository repository;
    private final tFormaPagoRepository formaPagoRepository;
    private final tFacturaRepository facturaRepository;
    private final tPersonaRepository userRepository;


    public pagosService(tPagoRepository _repository, tFormaPagoRepository _formaPagoRepository ,tPersonaRepository userRepository, tFacturaRepository facturaRepository){
        logs.setLogger(pagosService.class);
        this.repository = _repository;
        this.formaPagoRepository = _formaPagoRepository;
        this.facturaRepository = facturaRepository;
        this.userRepository = userRepository;
    }


    /** Registra el Pago
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public Response registrarPago(registrarPagoDTO dto) {
        logs.info(_CONST.ML_INI + Utils.toJson(dto));
        Response response = Utils.generateBadResponseDefault();
        try {
            Optional<TPAGFACTURA> factura = facturaRepository.findById(dto.idFactura());
            Optional<TPAGFORMAPAGO> forma = formaPagoRepository.findById(dto.idFormaPago());
            Optional<TPERPERSONA> usuario = userRepository.findByIdentificacion(dto.beneficario());
            if(factura.isEmpty() || forma.isEmpty() || usuario.isEmpty()){
                logs.info("NO EXISTE INFORMACION NECESARIA");
                response.setMessage(_CONST.MENSAJE_ERROR);
            } else {
                TPAGPAGO newPay = repository.save(pagoMapper.toDtoToEntity(factura.get(), forma.get(), dto, usuario.get()));
                response = Utils.generateOKResponse(newPay);
            }
        }catch (Exception e){
            logs.error(_CONST.COD_ERROR ,e);
            response.setMessage(_CONST.MENSAJE_ERROR);
            response.setData(Utils.toJson(e.getMessage()));
        }
        logs.info(_CONST.ML_FIN + response);
        return response;
    }

    /** Devuelve el listado de Pagos acorde a una fecha, tamanio y numero de elementos
     * @return
     */
    @Override
    public Response informacionPago(listarPagos dto) {
        logs.info(_CONST.ML_INI + Utils.toJson(dto));
        Response response = Utils.generateBadResponseDefault();
        try {
            int pageNumer = (dto.page() == null || dto.page() < 0) ? 0 : dto.page();
            int size = (dto.size() == null || dto.size() <= 0) ? 10 : dto.size();
            Pageable page = PageRequest.of(pageNumer, size);
            Page<TPAGPAGO> pagosList = repository.findAll(page);
            response = Utils.generateOKResponse(pagosList.stream().filter(
                    x -> x.getFechaCreacion().after(Utils.getDateNow(dto.fechaIncio())) && x.getFechaCreacion().before(Utils.getDateNow(dto.fechsFin()))
            ).collect(Collectors.toList()));
        }catch (Exception e){
            logs.error(_CONST.COD_ERROR ,e);
            response.setMessage(_CONST.MENSAJE_ERROR);
            response.setData(Utils.toJson(e.getMessage()));
        }
        return response;
    }
}
