package com.appmedic.medic_app.domain.entity.pagos;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "TPAGPAGO", schema = "dbo", catalog = "mediapp")
public class TPAGPAGO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "IDFORMAPAGO")
    private TPAGFORMAPAGO formaPago;

    @ManyToOne
    @JoinColumn(name = "IDFACTURA")
    private TPAGFACTURA factura;

    @Column(name = "MONTO", precision = 3)
    private BigDecimal monto;

    @Column(name = "FCREACION")
    private Date fechaCreacion;


}
