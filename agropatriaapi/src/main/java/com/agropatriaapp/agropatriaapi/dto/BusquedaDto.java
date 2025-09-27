package com.agropatriaapp.agropatriaapi.dto;

import lombok.Getter;
import lombok.Setter;

public class BusquedaDto {
    @Getter
    @Setter
    private String nombre;
    @Getter
    @Setter
    private String descripcion;


    public BusquedaDto(String nombre, String descripcion){
        this.nombre = nombre;
        this.descripcion = descripcion;
    }
}
