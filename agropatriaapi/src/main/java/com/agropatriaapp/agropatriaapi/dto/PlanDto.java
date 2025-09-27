package com.agropatriaapp.agropatriaapi.dto;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

public class PlanDto {

    @Getter
    @Setter
    private int cantidad_public;
    @Getter
    @Setter
    private String descripcion;
    @Getter
    @Setter
    private int duracion_dias;
    @Getter
    @Setter
    private int precio;
    @Getter
    @Setter
    private String titulo;

    @Getter
    @Setter
    private List<Integer> idsCategoria;

    public PlanDto(){

    }





}
