package com.appmedic.medic_app.aplication.ports.out.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioDTO {
    private int id;
    private String nombre;
    private String apellido;
    private Date fnacimiento;
    private Date fechaIngreso;
    private Date fechaActualizacion;
    private String email;
    private String telefono;
    private String identificacion;
    private int idRol;
    private String nombreRol;

    // Patrón Builder
    public static class Builder {
        private int id;
        private String nombre;
        private String apellido;
        private Date fnacimiento;
        private Date fechaIngreso;
        private Date fechaActualizacion;
        private String email;
        private String telefono;
        private String identificacion;
        private int idRol;
        private String nombreRol;

        public Builder id(int id) {
            this.id = id;
            return this;
        }

        public Builder nombre(String nombre) {
            this.nombre = nombre;
            return this;
        }

        public Builder apellido(String apellido) {
            this.apellido = apellido;
            return this;
        }

        public Builder fnacimiento(Date fnacimiento) {
            this.fnacimiento = fnacimiento;
            return this;
        }

        public Builder fechaIngreso(Date fechaIngreso) {
            this.fechaIngreso = fechaIngreso;
            return this;
        }

        public Builder fechaActualizacion(Date fechaActualizacion) {
            this.fechaActualizacion = fechaActualizacion;
            return this;
        }

        public Builder email(String email) {
            this.email = email;
            return this;
        }

        public Builder telefono(String telefono) {
            this.telefono = telefono;
            return this;
        }

        public Builder identificacion(String identificacion) {
            this.identificacion = identificacion;
            return this;
        }

        public Builder idRol(int idRol) {
            this.idRol = idRol;
            return this;
        }

        public Builder nombreRol(String nombreRol) {
            this.nombreRol = nombreRol;
            return this;
        }

        public UsuarioDTO build() {
            return new UsuarioDTO(id, nombre, apellido, fnacimiento, fechaIngreso,
                    fechaActualizacion, email, telefono, identificacion,
                    idRol, nombreRol);
        }
    }

    public static Builder builder() {
        return new Builder();
    }
}