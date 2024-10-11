package com.appmedic.medic_app.domain.entity.citas;

import com.appmedic.medic_app.domain.entity.personas.TPERPERSONA;
import com.appmedic.medic_app.domain.entity.personas.TPEREMPLEADO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "TCITCITAS", schema = "dbo", catalog = "mediapp")
public class TCITCITAS {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private int id;

    @ManyToOne
    @JoinColumn(name = "IDPERSONA", nullable = false)
    private TPERPERSONA idPersona;

    @ManyToOne
    @JoinColumn(name = "IDPROFECIONAL", nullable = false)
    private TPEREMPLEADO idProfecional;

    @Column(name = "MOTIVO", length = 100)
    private  String motivo;

    @Column(name = "FECHARESERVA")
    private Date fechaReserva;
    @Column(name = "FECHAACTUALIZACION")
    private Date fechaActualizacion;

    @Column(name = "FECHADESDE")
    private Date fechaDesde;

    @Column(name = "FECHAHASTA")
    private Date fechaHasta;

    @Column(name = "ESTADO")
    private String estado;

}
