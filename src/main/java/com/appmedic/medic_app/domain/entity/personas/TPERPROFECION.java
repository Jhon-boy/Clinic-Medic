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
@Table(name = "TPERPROFECION", catalog = "mediapp")
public class TPERPROFECION {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "NOMBRE", nullable = false, length = 100, unique = true)
    private String nombre;

    @Column(name = "DESCRIPCION", nullable = false, length =250)
    private String descripcion;

    @Column(name = "SLOGAN", nullable = false, length = 250)
    private String slogan;

    @Column(name = "EXPERIENCIA", nullable = false, length = 250)
    private float experiencia;
}
