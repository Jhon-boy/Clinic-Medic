package com.appmedic.medic_app.domain.entity.personas;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "TPERESPECIALIDAD", schema = "dbo", catalog = "mediapp")
public class TPERESPECIALIDAD {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "NOMBRE", nullable = false, length = 100)
    private String nombre;

    @Column(name = "DESCRIPCION", nullable = false, length =250)
    private String descripcion;

    @Column(name = "SLOGAN", nullable = false, length = 250)
    private String slogan;

    @Column(name = "EXPERIENCIA", nullable = false, length = 250)
    private float experiencia;
}
