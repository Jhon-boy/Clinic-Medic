package com.appmedic.medic_app.domain.entity.seguridad;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name="TSEGROL",  schema = "dbo", catalog = "mediapp")
public class TSEGROL {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "ID", nullable = false)
    private int id;

    @Column(name = "NOMBRE", nullable = false, length = 30)
    private String nombre;

    @Column(name = "DESCRIPCION", nullable = false, length = 250)
    private String descripcion;

    @Column(name = "ACTIVO")
    private boolean activo;

}
