package com.agropatriaapp.agropatriaapi.dto;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

public class PublicacionDto {

    @Getter
    @Setter
    private int productoId;

    @Getter
    @Setter
    private int vendedorCuentasId;


    @Getter
    @Setter
    private boolean vendido;

    @Getter
    @Setter
    private String nombrePublicacion;

    @Getter
    @Setter
    private String descripcionPublic;

    @Getter
    @Setter
    private String condicion;

    @Getter
    @Setter
    private String ciudad;

    @Getter
    @Setter
    private String modelo;

    @Getter
    @Setter
    private Integer precio;

    @Getter
    @Setter
    private Integer categoria;

    @Getter
    @Setter
    private List<String> imagenes;


    public PublicacionDto(boolean vendido, String nombrePublicacion, String descripcionPublic,
     String condicion, String ciudad, Integer precio){
        this.vendido = vendido;
        this.nombrePublicacion = nombrePublicacion;
        this.descripcionPublic = descripcionPublic;
        this.condicion = condicion;
        this.ciudad = ciudad;
        this.precio = precio;
     }

}
