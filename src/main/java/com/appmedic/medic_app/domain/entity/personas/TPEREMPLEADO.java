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
@Table(name = "TPEREMPLEADO", schema = "dbo", catalog = "mediapp")
public class TPEREMPLEADO {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToOne
    @JoinColumn(name = "IDPERSONA", nullable = false)
    private TPERPERSONA persona;

    @ManyToOne
    @JoinColumn(name = "IDESPECIALIDAD", nullable = false)
    private TPERESPECIALIDAD especialidad;

    @Column(name = "IMAGEN", columnDefinition = "LONGBLOB")
    private byte[] imagen;

}