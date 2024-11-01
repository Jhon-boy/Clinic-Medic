package com.appmedic.medic_app.domain.entity.seguridad;

import com.appmedic.medic_app.domain.entity.personas.TPERPERSONA;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name="TSEGUSUARIO", catalog = "mediapp")
public class TSEGUSUARIO {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "IDROL", nullable = false)
    private TSEGROL rol;

    @OneToOne
    @JoinColumn(name = "IDPERSONA", nullable = false)
    private TPERPERSONA persona;

    @Column(name = "USUARIO", nullable = false, length = 20, unique = true)
    private String usuario;

    @Column(name = "CLAVE", nullable = false, length = 100)
    private String clave;

    @Column(name = "ACTIVO")
    private boolean activo;

    @Column(name = "SESSION")
    private boolean sesion;

    @Column(name = "FECHAINGRESO", nullable = false)
    private Date fechaIngreso;

    @Column(name = "FECHAACTUALIZACION", nullable = true)
    private Date fechaActualizacion;
}
