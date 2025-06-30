package com.appmedic.medic_app.aplication.ports.in.dto;

import jakarta.validation.constraints.Min;

import java.util.Arrays;
import java.util.Objects;


/**
 * Modelo para recibir datos
 * */
public record RegistrarEmpleadoDTO(

        @Min(1)
        int idPersona,

        @Min(1)
        int idProfecion,

        byte[] imagen,

        @Min(1)
        int experiencia,

        String slogan

) {
        @Override
        public boolean equals(Object o) {
                if (this == o) return true;
                if (!(o instanceof RegistrarEmpleadoDTO other)) return false;
                return idPersona == other.idPersona &&
                        idProfecion == other.idProfecion &&
                        experiencia == other.experiencia &&
                        Objects.equals(slogan, other.slogan) &&
                        Arrays.equals(imagen, other.imagen);
        }

        @Override
        public int hashCode() {
                int result = Objects.hash(idPersona, idProfecion, experiencia, slogan);
                result = 31 * result + Arrays.hashCode(imagen);
                return result;
        }

        @Override
        public String toString() {
                return "RegistrarEmpleadoDTO[" +
                        "idPersona=" + idPersona +
                        ", idProfecion=" + idProfecion +
                        ", imagen=" + Arrays.toString(imagen) +
                        ", experiencia=" + experiencia +
                        ", slogan=" + slogan + "]";
        }
}
