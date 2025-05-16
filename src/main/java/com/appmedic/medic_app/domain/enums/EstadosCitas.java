package com.appmedic.medic_app.domain.enums;

import java.util.Locale;

public enum EstadosCitas {
    RESERVADO(1, "RESERVADO"),
    EN_CURSO(2, "CURSANDO"),
    FINALIZADO(3, "FINALIZADO"),
    CANCELADO(4, "CANCELADO");
    private final int id;
    private final String nombre;
    EstadosCitas(int id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }
    public int getId() {
        return  id;
    }

    public String getNombre() {
        return nombre;
    }
    public static EstadosCitas getByName( String estado){
        for ( EstadosCitas state : EstadosCitas.values()){
            if(state.getNombre().toUpperCase(Locale.ROOT).equals(estado.toUpperCase(Locale.ROOT)))
                return  state;
        }
        throw  new IllegalArgumentException("State not found" + estado);
    }
    public  static  EstadosCitas getNombreByID(Integer id){
        for (EstadosCitas state: EstadosCitas.values()){
            if(state.id == id)
                return  state;
        }
        throw  new IllegalArgumentException("State not found" + id);
    }
}
