package com.appmedic.medic_app.domain.entity.empresa;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "TEMPEMPRESA",  catalog = "mediapp")
public class TEMPEMPRESA {

    @Id
    @Column(name = "RUC", length = 15)
    private String ruc;

    @Column(name = "NOMBRE", length = 50, unique = true)
    private String nombre;

    @Column(name = "DESCRIPCION", length = 255)
    private String descripcion;

    @Column(name = "ACTIVIDADECONOMICA", length = 100)
    private String actividadEconomica;


}
