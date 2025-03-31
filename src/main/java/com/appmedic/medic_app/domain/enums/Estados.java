package com.appmedic.medic_app.domain.enums;

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
}
