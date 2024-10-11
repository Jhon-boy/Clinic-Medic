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
@Table(name = "TEMPEMPRESADETALLE",schema = "dbo", catalog = "mediapp")
public class TEMPEMPRESADETALLE {

    @EmbeddedId
    @Column(name = "IDEMPRESA", nullable = false)
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
