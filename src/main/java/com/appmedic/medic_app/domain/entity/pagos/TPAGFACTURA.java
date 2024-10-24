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
@Table(name = "TPAGFACTURA",  catalog = "mediapp")
public class TPAGFACTURA {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "IVA", nullable = false)
    private int iva;

    @ManyToOne
    @JoinColumn(name = "IDFORMAGO")
    private TPAGFORMAPAGO formaPago;

    @Column(name = "MONTOORIGINAL", nullable = false, precision = 3)
    private BigDecimal montoOriginal;

    @Column(name = "MONTOTOTAL", nullable = false)
    private BigDecimal montototal;

    @Column(name = "DESCUENTO", nullable = false)
    private int descuento;

    @Column(name = "DESCRIPCION", length = 250)
    private String descripcion;

    @Column(name = "FCREACION", nullable = false)
    private Date fechaCreacion;

}
