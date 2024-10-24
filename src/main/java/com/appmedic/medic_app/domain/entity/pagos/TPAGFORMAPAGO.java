package com.appmedic.medic_app.domain.entity.pagos;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "TPAGFORMAPAGO",  catalog = "mediapp")
public class TPAGFORMAPAGO {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  int id;

    @Column(name = "NOMBRE", length = 30, unique = true)
    private String nombre;

    @Column(name = "DESCRIPCION", length = 250)
    private String descripcion;

    @Column(name = "FCREACION")
    private Date fcreacion;

    @Column(name = "FACTUALIZACION")
    private Date factualizacion;

    @Column(name = "ACTIVO")
    private boolean activo;

}
