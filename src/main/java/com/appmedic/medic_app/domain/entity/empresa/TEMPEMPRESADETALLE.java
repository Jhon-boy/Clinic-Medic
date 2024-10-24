package com.appmedic.medic_app.domain.entity.empresa;

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
@Table(name = "TEMPEMPRESADETALLE", catalog = "mediapp")
public class TEMPEMPRESADETALLE {

    @Id
    @Column(name = "ID")
    private int id;

    @OneToOne
    @JoinColumn(name = "IDEMPRESA")
    private  TEMPEMPRESA empresa;

    private String provincia;
    private String parroquia;
    private String canton;
    private String cuidad;
    private String calles;
    private String numeroResidencia;
    private double x;
    private double y;

}
