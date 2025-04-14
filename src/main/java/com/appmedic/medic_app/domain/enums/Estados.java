package com.appmedic.medic_app.domain.enums;

import java.util.Locale;

public enum Estados {
    ACTIVO(1, "Activo"),
    INACTIVO(0, "Inactivo"),
    TODOS(2, "Todos");
    private final int id;
    private final String nombre;
    Estados(int id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }
    public int getId() {
        return  id;
    }

    public String getNombre() {
        return nombre;
    }
    public static Estados getByName( String estado){
        for ( Estados state : Estados.values()){
            if(state.getNombre().toUpperCase(Locale.ROOT).equals(estado.toUpperCase(Locale.ROOT)))
                return  state;
        }
        throw  new IllegalArgumentException("State not found" + estado);
    }
}
