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
@Table(name="TSEGUSUARIOROL", catalog = "mediapp")
public class TSEGUSUARIOROL {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne
    @JoinColumn(name = "IDROL", nullable = false)
    private TSEGROL rol;

    @ManyToOne
    @JoinColumn(name = "IDUSUARIO", nullable = false)
    private TSEGUSUARIO idUsuario;

}
