package com.appmedic.medic_app.domain.entity.personas;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "TPERPERSONA", schema = "dbo", catalog = "mediapp")
public class TPERPERSONA {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "NOMBRE", length = 100)
    private String nombre;

    @Column(name = "APELLIDO", length = 100)
    private String apellido;

    @Column(name = "FNACIMIENTO")
    private Date fnacimiento;

    @Column(name = "FECHAINGRESO", nullable = false)
    private Date fechaIngreso;

    @Column(name = "FECHAACTUALIZACION", nullable = true)
    private Date fechaActualizacion;

    @Column(name = "EMAIL", nullable = true, length = 30)
    private String email;

    @Column(name = "TELEFONO", nullable = true, length = 10)
    private String telefono;

}

